D1
# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
import requests
import pandas as pd
import numpy as np
from sklearn.impute import SimpleImputer
class printME:
    def __int__(self):
        pass
    def _printme(self):
        q=input('enter name plz:')
        print (tuple(q))
    def _printlist(self,_List):
        self.__reqList=[]
        for l in _List:
            if type(l)==str:
                self.__reqList.append(l)
        return self.__reqList
    def _getObjFromURL(self,url):
        resp=requests.get(url)
        return eval(resp.text)
    def _readCSV(self,file):
        self.__df=pd.read_csv(file)
        return self.__df;
    def _fillMissingData(self,file):
        self.df = self._readCSV(file);
        self.x =  self.df.iloc[:,:-1].values
        self.y =  self.df.iloc[:,3].values
        imp = SimpleImputer(missing_values=np.nan, strategy='most_frequent')
        print(imp.fit_transform(self.df))  
        
        

c=printME()
#c._printme()
dataList = ['aa','bb','cc',123,True,2]
strList = c._printlist(dataList)
#print(strList)
datafromOnline = c._getObjFromURL('https://jsonplaceholder.typicode.com/users/')
#print(datafromOnline)
df=c._readCSV("C:/Users/RPS/Desktop/kasiselvamk/data/diabetes.csv")
#print(df.shape)
#print(df['BMI'])
#print(df.iloc[0:0,0])
#df.iloc[5].plot(kind='pie');
#print(df.cumsum())
c._fillMissingData("C:/Users/RPS/Desktop/kasiselvamk/data/Data.csv")


'''
import cv2 as cv
import numpy as np
img_rgb = cv.imread('C:/Users/RPS/Desktop/kasiselvamk/data/mario.png')
img_gray = cv.cvtColor(img_rgb, cv.COLOR_BGR2GRAY)
template = cv.imread('C:/Users/RPS/Desktop/kasiselvamk/data/mario_coin.png',0)
w, h = template.shape[::-1]
print (w, h)
res = cv.matchTemplate(img_gray,template,cv.TM_CCOEFF_NORMED)
print (res)
threshold = 0.8
thresholdarr = cv.threshold(img_gray,0,255,cv.THRESH_BINARY+cv.THRESH_OTSU)
loc = np.where( res >= threshold)

for pt in zip(*loc[::-1]):
    cv.rectangle(img_rgb, pt, (pt[0] + w, pt[1] + h), (0,0,255), 2)
cv.imwrite('C:/Users/RPS/Desktop/kasiselvamk/data/res.png',img_rgb)
'''

