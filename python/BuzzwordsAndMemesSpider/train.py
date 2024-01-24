import torch
import torch.nn as nn
from torch.optim import Adam
from torch.utils.data import Dataset, DataLoader

file_path = r"C:\Users\86156\Desktop\output.txt"
model_path = r"C:\Users\86156\Desktop\language_model.pth"
# 读取文本文件
with open(file_path, 'r', encoding='utf-8') as file:
    text = file.read()

# 将文本拆分为句子或标记
sentences = text.split('\n')

# 构建词汇表
vocab = list(set(" ".join(sentences).split()))

# 创建一个映射表，将词转换为索引
word_to_index = {word: idx for idx, word in enumerate(vocab)}
index_to_word = {idx: word for idx, word in enumerate(vocab)}

# 将文本转换为索引序列
indexed_data = [[word_to_index[word] for word in sentence.split()] for sentence in sentences]
import torch.nn.utils.rnn as rnn_utils

# 定义一个简单的RNN模型
class SimpleRNN(nn.Module):
    def __init__(self, vocab_size, embedding_dim, hidden_size):
        super(SimpleRNN, self).__init__()
        self.embedding = nn.Embedding(vocab_size, embedding_dim)
        self.rnn = nn.RNN(embedding_dim, hidden_size, batch_first=True)
        self.fc = nn.Linear(hidden_size, vocab_size)

    def forward(self, x):
        embedded = self.embedding(x)
        output, _ = self.rnn(embedded)
        logits = self.fc(output)
        return logits

# 定义数据集类
class TextDataset(Dataset):
    def __init__(self, data):
        self.data = data

    def __len__(self):
        return len(self.data)

    def __getitem__(self, idx):
        return torch.tensor(self.data[idx], dtype=torch.long)

# 创建数据加载器
dataset = TextDataset(indexed_data)
dataloader = DataLoader(dataset, batch_size=32, collate_fn=lambda x: rnn_utils.pad_sequence(x, batch_first=True, padding_value=0), shuffle=True)

# 初始化模型、损失函数和优化器
vocab_size = len(vocab)
embedding_dim = 50
hidden_size = 128

model = SimpleRNN(vocab_size, embedding_dim, hidden_size)
criterion = nn.CrossEntropyLoss()
optimizer = Adam(model.parameters(), lr=0.001)

# 训练模型
num_epochs = 3

for epoch in range(num_epochs):
    for batch in dataloader:
        optimizer.zero_grad()
        logits = model(batch)
        loss = criterion(logits.view(-1, vocab_size), batch.view(-1))
        loss.backward()
        optimizer.step()

    print(f'Epoch {epoch + 1}/{num_epochs}, Loss: {loss.item()}')

# 在训练时保存模型的时候
torch.save({
    'vocab':vocab,
    'model_state_dict': model.state_dict(),
    'vocab_size': vocab_size,
    'embedding_dim': embedding_dim,
    'hidden_size': hidden_size
}, model_path)
