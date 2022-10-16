写在前面的话
====
*记录某次作业（本来要爬自己学校网站，但是被反反反爬了，最后只能换个天气网站爬爬）*

*顺便把作业要求也放在这里：1.在网络上找爬虫引擎和导入ecxel引擎 2.将学校教师信息爬取 3. 将信息导入excel*
（吐槽一点，GitHub上的爬虫引擎大多都不适合初学者，只能自己看视频手搓了）


如何运行
----
配置maven依赖文件：
```java
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>5.2.3</version>
        </dependency>

        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>5.2.3</version>
        </dependency>


        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.10.3</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpcore</artifactId>
            <version>4.4.10</version>
        </dependency>
        <dependency>
            <groupId>org.apache.httpcomponents</groupId>
            <artifactId>httpclient</artifactId>
            <version>4.5.6</version>

        </dependency>
    </dependencies>
```

在idea中clone后，运行pachong即可
预览图：

![](预览.png)

关于代码部分
---
代码中使用了jsoup来爬取网站资源，用poi将爬取的数据导入本地excel表
代码中重复部分较多，刚入门java写的小作业，有待改进～～～
