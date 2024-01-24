import torch
import torch.nn.functional as F
from train import SimpleRNN
import numpy as np

model_path = r"C:\Users\86156\Desktop\language_model.pth"
output_file_path = r"C:\Users\86156\Desktop\train_output.txt"

# 加载训练好的模型
checkpoint = torch.load(model_path)
vocab = checkpoint['vocab']
vocab_size = checkpoint['vocab_size']
embedding_dim = checkpoint['embedding_dim']
hidden_size = checkpoint['hidden_size']

model = SimpleRNN(vocab_size, embedding_dim, hidden_size)
model.load_state_dict(checkpoint['model_state_dict'])
word_to_index = {word: idx for idx, word in enumerate(vocab)}
index_to_word = {idx: word for idx, word in enumerate(vocab)}
model.eval()


# 打开文件以进行追加写入
def generate_text(start_text, length=100, temperature=1.0):
    current_text = start_text
    target = ""
    endText = ""
    count = 0
    for _ in range(length):
        indexed_input = [word_to_index[word] for word in current_text.split()]
        input_tensor = torch.tensor(indexed_input, dtype=torch.long).view(1, -1)

        with torch.no_grad():
            output = model(input_tensor)

        # 根据输出概率采样下一个词
        probabilities = F.softmax(output[0, -1] / temperature, dim=0).numpy()
        next_word_index = np.random.choice(len(vocab), p=probabilities)

        # 将下一个词添加到当前文本
        next_word = index_to_word[next_word_index]
        current_text += ' ' + next_word
        if count < 2:
            target += ' ' + next_word
        count += 1
        endText = next_word;
    with open(output_file_path, 'a', encoding='utf-8') as output_file:
        output_file.write(f"# Temperature: {temperature}\n")
        output_file.write(current_text + '\n')
        print(target + " 快点去" + endText + '\n')
        output_file.write(current_text + " 快点去" + endText+'\n')
        # 生成文本


# 循环生成文本
start_text = "淘宝说得对"
length = 10
base_temperature = 1000

for i in range(100):
    generate_text(start_text, length=3, temperature=base_temperature + i * 50)
