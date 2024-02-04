# selenium爬虫获取boss直聘信息代码分解

#### 1.创建user_agent，user_agent模拟你的使用设备环境（反反爬策略，有些反爬机制会通过你的设备信息来检测你是用户行为还是爬虫行为）

from selenium import webdriver 

driver = webdriver.Chrome() # 打开一个网页 

driver.get("https://www.whatismybrowser.com/detect/what-is-my-user-agent") # 使用 JavaScript 获取 

User-Agent user_agent = driver.execute_script("return navigator.userAgent;") 



#### 2.设置已登录用户的cookie

在网页上打开一个目标网站，登录到目标网站，之后用EditThisCookie进行cookie导出，导出为json格式

然后定义一个cookie变量

```
cookie_dict = [
{
    "domain": ".zhipin.com",
    "expirationDate": 1741419805,
    "hostOnly": "false",
    "name": "__a",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "32803372.1706857261..1706857261.8.1.8.8",
    "id": 1
},
{
    "domain": ".zhipin.com",
    "hostOnly": "false",
    "name": "__c",
    "path": "/",
    "session": "true",
    "storeId": "0",
    "value": "1706857261",
    "id": 2
},
{
    "domain": ".zhipin.com",
    "hostOnly": "false",
    "name": "__g",
    "path": "/",
    "session": "true",
    "storeId": "0",
    "value": "-",
    "id": 3
},
{
    "domain": ".zhipin.com",
    "hostOnly": "false",
    "name": "__l",
    "path": "/",
    "session": "true",
    "storeId": "0",
    "value": "l=%2Fwww.zhipin.com%2Fshenzhen%2F&r=&g=&s=3&friend_source=0&s=3&friend_source=0",
    "id": 4
},
{
    "domain": ".zhipin.com",
    "expirationDate": 1707090206,
    "hostOnly": "false",
    "name": "__zp_stoken__",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "5010fQkHDpsK8YsOBPDUWDg4JFEc1RUEyMTpCNTo7OUJBRDk7QkE8G0Uyw6HDhB7CpsKJXcOTworDhUM0RDs9QkE6O0I%2FJERHxYTDhUBFH8Ozw4IkwqDCkF7DlRIKWcKgw4USIAhuEVzDhEAwJ8OBQzw9RQ7Dh8K8wrw9w4LDg8OFPcOBw4bCujxFRUYpOBJjDWY4RVZXZg5XWlZcZEsXT0pVN0U9RTtuw73Ehi85ExMQFQoVFRYTDA4ODRUKEhIRFAsPDwwJFipCwpzDg8SHccS8w5bDvsSawqRSwpzCtcOCaMKkw4bDhU3DhU%2FEhWnCscOAwqjCqcKxTMKyVcK5wrTCn1zCo1tuwq%2FCpHBxwoXCumthVGxIYMKFTloWEQwQCjgNNk7Dlw%3D%3D",
    "id": 5
},
{
    "domain": ".zhipin.com",
    "hostOnly": "false",
    "name": "boss_login_mode",
    "path": "/",
    "session": "true",
    "storeId": "0",
    "value": "sms",
    "id": 6
},
{
    "domain": ".zhipin.com",
    "hostOnly": "false",
    "name": "Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a",
    "path": "/",
    "session": "true",
    "storeId": "0",
    "value": "1706859806",
    "id": 7
},
{
    "domain": ".zhipin.com",
    "expirationDate": 1738395805,
    "hostOnly": "false",
    "name": "Hm_lvt_194df3105ad7148dcf2b98a91b5e727a",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "1706857261",
    "id": 8
},
{
    "domain": ".zhipin.com",
    "expirationDate": 1738395804,
    "hostOnly": "false",
    "name": "lastCity",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "101280600",
    "id": 9
},
{
    "domain": ".zhipin.com",
    "expirationDate": 1707591599,
    "hostOnly": "false",
    "name": "wbg",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "0",
    "id": 10
},
{
    "domain": ".zhipin.com",
    "expirationDate": 1707591599,
    "hostOnly": "false",
    "name": "wt2",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "DdfUsgNObFFkmjZwAhLvbp6QaRDp4VxwQq3pwj-AX11l8FAzajpVp8mdVsQxhykOjTZQ0pHwmF4cHk7iGBYWPuw~~",
    "id": 11
},
{
    "domain": ".zhipin.com",
    "expirationDate": 1707591599,
    "hostOnly": "false",
    "name": "zp_at",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "Ljo83JDcMa2yRAkLTCGZC_NgyCnaKCVLs9BjWaKBA-A~",
    "id": 12
},
{
    "domain": "www.zhipin.com",
    "expirationDate": 1722411786,
    "hostOnly": "true",
    "name": "_bl_uid",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "ILl80sbO4s7chd8zdvybvRXphsws",
    "id": 13
},
{
    "domain": "www.zhipin.com",
    "expirationDate": 1709452674,
    "hostOnly": "true",
    "name": "gdxidpyhxdE",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "CxsEiQV%2B7YsZYeHY39RM7n%2F6vhenp%2F2JilC%2BDcbESw4m%2BHz2Q7eXmaqJouqJuyXKsX8vP%2FwssLbRoXsEDn%2FuC%5CfwpsQU8U2W0uJ1A%5CWsmSt4QN9vTbcXP%2BSHOmzq9bQ4AxvA9vjlIGJS%2FqUaXw3oAC2QZO%2BXyEafq4dG%5CE2Ht8JgqA2b%3A1706860674112",
    "id": 14
},
{
    "domain": "www.zhipin.com",
    "hostOnly": "true",
    "name": "geek_zp_token",
    "path": "/",
    "session": "true",
    "storeId": "0",
    "value": "V1RNglEeX531ZiVtRuzxsQLyuz6D_TzSU~",
    "id": 15
},
{
    "domain": "www.zhipin.com",
    "expirationDate": 1741419799,
    "hostOnly": "true",
    "name": "historyState",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "state",
    "id": 16
},
{
    "domain": "www.zhipin.com",
    "expirationDate": 1741419767,
    "hostOnly": "true",
    "name": "wd_guid",
    "path": "/",
    "session": "false",
    "storeId": "0",
    "value": "aec5792c-71c6-4769-8380-c28d3668eebb",
    "id": 17
}
]
```

#### 3.将cookie添加到请求信息中

![image-20240202161020417](https://s2.loli.net/2024/02/02/HigJTScBU9aY27n.png)



#### 4.至此，完成了使用selenium添加浏览器信息，配置用户cookie，以模拟正常已登录用户的行为



如果被封印，尝试模拟用户行为，比如模拟用户延迟，模拟用户选择某个图标鼠标等等

![545b8e18f352cdda65683d5630f2c2d](https://s2.loli.net/2024/02/02/Gg1ikhbr2PyJCUs.png)

爬取数据如下

![image-20240202170035122](https://s2.loli.net/2024/02/02/VKQ5TUafOpJ8z2P.png)