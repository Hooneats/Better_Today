import os
import urllib.parse as rep
import urllib.request as req
from fake_useragent import UserAgent

from bs4 import BeautifulSoup
import os
import cx_Oracle


os.putenv('NLS_LANG','.UTF8') #한글설정?
connect = cx_Oracle.connect("pyjava","tiger","localhost:1521/JAVA")
cursor = connect.cursor()

# cursor.execute("") # 확인
# for name in cursor: # 확인
#     print(name) # 확인
# ====================================DB연결
opener = req.build_opener()
ua = UserAgent()
opener.addheaders = [('User-agemt',ua.ie)]
req.install_opener(opener)
# ================================= fake_ user 만듦
base = "https://news.daum.net/ranking/popular"
# ==============================================접속할 URL만듦
res = req.urlopen(base)
# ======================================Request 요청 만듦

# ==================================================================뉴스페이지1 많이본 순
cursor.execute("truncate table NEWS1")

soup = BeautifulSoup(res,"html.parser")
# print(soup.prettify) # 확인
news_list = soup.select("ul.list_news2 > li > a")
# news_title_list = []
# print(type(news_title_list))
company_list = soup.findAll("span",{"class":"info_news"})
# print(company_list) # 확인


for i, news in enumerate(news_list):
    print()
    i+=1
    print(news,"번호는 >>>>>>>>>>",i)
    link = news.attrs['href'] # 속성 href 의 값을 뽑아냄
    title = news.find('img')['alt'] # img 태그에 alt 속성 값을 추출

    title = title.replace("'", "")
    title = title.replace('"', '')
    print(title)

    img_src = news.find('img')['src']
    company_name = str(company_list.pop(0).string)

    # cursor.excute("insert into NEWS1 values (i)")
    #id , title, company , imag, date, link
    print(link)
    print(title)
    print(img_src)
    print(company_name)
    newssql="insert into NEWS1 values("+str(i) + ",'" + title + "','" + company_name + "','" + img_src + "',SYSDATE,'" + link + "')"

    cursor.execute(newssql)

connect.commit()


# =================================================================== 뉴스페이지2 열독률 높은 순
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
# ========================================================================= 뉴스페이지3 댓글 많은 순
base = "https://news.daum.net/ranking/bestreply"

res = req.urlopen(base)
soup = BeautifulSoup(res,"html.parser")

cursor.execute("truncate table NEWS3")

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
    newSql = "insert into NEWS3 values('"+str(i)+"','"+title+"','"+company_name+"','"+img_src+"',"+'SYSDATE'+",'"+link+"')"
    # print(newSql)
    cursor.execute(newSql)

connect.commit()