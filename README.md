Word Density Analysis 
A Web Crawler for main topics extraction 
 
Created by Ren He 8/30/2015 

Usage 
java -java Assignment.jar url
e.g. java -jar Assignment.jar "http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/"

Design Pattern
1. Four kinds of information are mainly extracted and analyzed: title, headings, url and body text.
2. First, collect 20 high frequency words in entire html from Body text.
3. Second, text from title, heading and url are extracted since they contain most important messages. 
4. Using these three filters and high density words form body text, main topics can be extracted and combined together so that final result can be obtained.   
5. During the above process, title filter is turned off because most titles are very similar to <h1>headings but they include more useless information.   
6. Final results follows: URL result>title results= headingresults>Top 5 High frequency words (not included in URL,title or heading). 


Testing Resuts


Issue Faced 
1. Heavey server traffic could cause HTTP error fetching URL with JSOUP
2. If ignord words(the, on, you etc.) are keywords during word density computing, they cannot be extracted
3. Strongly depends on word density and URL

Future work 
1. More elements can be extracted from the page and analyzed to builde filters and get more accurate page topics,like link, images, div class. 
2. Optimize structure and algorithm to speed up
3. Improve ignore meaningless word filter for words, like the, on, to. 
4. phrase density calculation
5. weight equation of keywords/keywords weight table can be developed like bellow:
--------------------------------------------------------------------
Keywords: keywords1, keywords2, keywords3, keywords4, keywords5 ....
--------------------------------------------------------------------
filter 1      1          0           1          0        0
filter 2      0          1           1          0        0
filter 3      0          1           1          1        0
...          ...        ...         ...        ...       ...
filter n      1          0           1          1        0
--------------------------------------------------------------------
total         12         1           14         2        0    
--------------------------------------------------------------------
Then,main topics can be determined according to this table or equation.  
6. More filters can be added for above. 

Files Attached Information

Complete code/Jar/Read Me file