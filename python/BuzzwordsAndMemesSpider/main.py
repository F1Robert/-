# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

import os

import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait

# 设置全局代理
os.environ['HTTP_PROXY'] = 'http://127.0.0.1:7890'
os.environ['HTTPS_PROXY'] = 'http://127.0.0.1:7890'


# 设置 Chrome 驱动的路径，你也可以使用其他浏览器的驱动
def loadMore():
    driver_path = r'C:\Users\86156\Downloads\chromedriver-win64\chromedriver.exe'
    url = 'https://regengbaike.com/'
    # 创建 Chrome options 对象
    chrome_options = Options()
    # 添加一些配置，例如启用 headless 模式
    chrome_options.add_argument('--headless')
    # 创建 Chrome WebDriver 对象并传入 options
    driver = webdriver.Chrome(options=chrome_options)
    # 创建 Chrome 驱动时传递 ChromeOptions
    # 打开网页
    driver.get(url)
    get_webpage_content(url)
    for _ in range(200):
        try:
            # 等待加载更多按钮出现
            load_more_button = WebDriverWait(driver, 1000).until(
                EC.presence_of_element_located((By.CLASS_NAME, 'btn-loadmore')))
            # 模拟点击加载更多按钮
            load_more_button.click()
            # 使用 JavaScript 模拟点击
            driver.execute_script("arguments[0].click();", load_more_button)
            # 这里可以添加等待加载完成的逻辑，比如等待新内容出现或其他元素改变
            new_page_content = driver.page_source
            exam_webpage_content(new_page_content)
        except Exception as e:
            print(f"发生错误: {e}")


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


def get_webpage_content(url):
    try:
        response = requests.get(url)
        response.raise_for_status()  # 如果请求不成功，会抛出异常
        content = response.text
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")
    html_content = content
    # print(html_content)//
    # 使用 BeautifulSoup 解析 HTML
    # 指定文件路径

    try:
        soup = BeautifulSoup(html_content, 'html.parser')
        selected_title_elements = soup.select(".article-title a")
        # print(selected_title_elements)
        selected_info_elements = soup.select(".article-intro p")

        if len(selected_title_elements) == len(selected_info_elements):
            # 循环输出拼接后的内容
            for title_element, info_element in zip(selected_title_elements, selected_info_elements):
                title = title_element.text.strip()
                info = info_element.text.strip()
                result = f"{title} - {info}"
                print(result)
        else:
            print("数组长度不一致，无法按顺序拼接输出。")
        # for element in selected_title_elements:
        #     print("title"+element.text)
        # for element in selected_info_elements:
        #     print("content"+element.text)

    except Exception as e:
        print(f"Error extracting text: {e}")
        return []
    # 提取标题和内容
    title = soup.select_one('.article-title a').text
    content = soup.select_one('.article-intro p').text


def exam_webpage_content(page_content):
    try:
        content = page_content
    except requests.exceptions.RequestException as e:
        content = None
        print(f"Error: {e}")
    html_content = content
    # print(html_content)//
    # 使用 BeautifulSoup 解析 HTML
    try:
        soup = BeautifulSoup(html_content, 'html.parser')
        selected_title_elements = soup.select(".article-title a")
        selected_info_elements = soup.select(".article-intro p")
        # 提取每个标题标签的文本内容
        title_texts = [tag.get_text() for tag in selected_title_elements]
        # 指定文件路径
        file_path = r"C:\Users\86156\Desktop\output.txt"
        # 打开文件以写入模式
        with open(file_path, "a", encoding="utf-8") as file:
            # 将文本内容写入文件
            for title_text in title_texts:
                try:
                    file.write(title_text + '\n')
                except Exception as e:
                    print(f"Error writing content to file: {e}")
                    print("Problematic item:", title_text)

        # [element.text.strip() for element in selected_elements]
        # 确保两个数组长度相同
        if len(selected_title_elements) == len(selected_info_elements):
            # 循环输出拼接后的内容
            for title_element, info_element in zip(selected_title_elements, selected_info_elements):
                title = title_element.text.strip()
                info = info_element.text.strip()
                result = f"{title} - {info}"
                print(result)
        else:
            print("数组长度不一致，无法按顺序拼接输出。")
        # for element in selected_title_elements:
        #     print("title"+element.text)
        # for element in selected_info_elements:
        #     print("content"+element.text)

    except Exception as e:
        print(f"Error extracting text: {e}")
        return []
    # 提取标题和内容
    title = soup.select_one('.article-title a').text
    content = soup.select_one('.article-intro p').text


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')
    loadMore()
# See PyCharm help at https://www.jetbrains.com/help/pycharm/
