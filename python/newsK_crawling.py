import os
import urllib.parse as rep
import urllib.request as req
from fake_useragent import UserAgent
import os
import cx_Oracle

from bs4 import BeautifulSoup

opener = req.build_opener()
ua = UserAgent()
opener.addheaders = [('User-agent',ua.ie)]
req.install_opener(opener)

# ====================================================

os.putenv('LNS_LANG', '.UTF8') #오라클 연동전에 데이터 불러오는 한글셋팅을 해주는 것이다
connect = cx_Oracle.connect("pyjava","tiger","localhost:1521/JAVA")
cursor = connect.cursor()

# ====================================================
base = "https://news.daum.net/ranking/kkomkkom"

res = req.urlopen(base)
soup = BeautifulSoup(res,"html.parser")

cursor.execute("truncate table NEWS2")

news_list = soup.select("ul.list_news2 > li > a")
company_list = soup.findAll("span", {"class":"info_news"})

for i, news in enumerate(news_list):
    print()
    i+=1
    print(news,"번호는>>>>>>"+str(i))
    link = news.attrs['href']
    title = news.find('img')['alt']

    title = title.replace("'","")
    title = title.replace('"',"")

    img_src = news.find('img')['src']
    company_name = str(company_list.pop(0).string)

    #id , title, company , imag, date, link
    newSql = "insert into NEWS2 values('"+str(i)+"','"+title+"','"+company_name+"','"+img_src+"',"+'SYSDATE'+",'"+link+"')"
    # print(newSql)
    cursor.execute(newSql)

connect.commit()

