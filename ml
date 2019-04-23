
##D1
import requests
import pandas as pd
import numpy as np
from sklearn.impute import SimpleImputer
from sklearn import preprocessing as _prepro
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
        return imp.fit_transform(self.df);
    def _fillMissingDataBlack(self,file):
        self.dfb = self._readCSV(file);
        self.data =  self.dfb.iloc[:,:].values
      #  self.y =  self.df.iloc[:,3].values
        imp = SimpleImputer(missing_values=np.nan, strategy='most_frequent')
        self.data[:,:]  = imp.fit_transform( self.data[:,:] )
        return  self.data


c=printME()
#c._printme()
#dataList = ['aa','bb','cc',123,True,2]
#strList = c._printlist(dataList)
#print(strList)
#datafromOnline = c._getObjFromURL('https://jsonplaceholder.typicode.com/users/')
#print(datafromOnline)
#df=c._readCSV("C:/Users/RPS/Desktop/kasiselvamk/data/diabetes.csv")
#print(df.shape)
#print(df['BMI'])
#print(df.iloc[0:0,0])
#df.iloc[5].plot(kind='pie');
#print(df.cumsum())
#print (c._fillMissingData("C:/Users/RPS/Desktop/kasiselvamk/data/Data.csv"))
#blData = c._fillMissingDataBlack("C:/Users/RPS/Desktop/kasiselvamk/data/BlackFriday.csv")
raData = c._fillMissingData("C:/Users/RPS/Desktop/kasiselvamk/data/Data.csv");
print (raData)

minmaxscal = _prepro.MinMaxScaler()
raData_scalled = minmaxscal.transform(raData[:,1:3])
print(raData_scalled)
 
 
 #Machine Learning --> supervised,un-suprovised , semi-supervised, re-enforsed learning ,deap learning.
 #https://archive.ics.uci.edu/ml/datasets.php?format=&task=&att=&area=&numAtt=&numIns=&type=&sort=instDown&view=table
 
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
_df=pd.read_csv("C:/Users/RPS/Desktop/kasiselvamk/data/MSFT_Stocks.csv")
X,Y = _df.iloc[:,:1:2].values, _df.iloc[:,:4:5].values
X_train, X_test, y_train, y_test = train_test_split(X, Y, test_size=0.20, random_state=42)


#simple leaner -- >  has one x attributes to get y.
#multiple leaener --> more x's to get y
#polinomial regression --> where data grow exponential.


#D2

import pandas as pd
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.linear_model import  LinearRegression
from sklearn.metrics import mean_squared_error,r2_score

_df=pd.read_csv("C:/Users/RPS/Desktop/kasiselvamk/data/Salary_Data.csv")
X,Y =  _df.iloc[ :, :-1 ].values, _df.iloc[:, 1].values
 
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.20, random_state=0)
lr=LinearRegression()
_result = lr.fit(X_train,Y_train)
 
Y_test_pred = lr.predict( X_test  )
 
 print('Coefficients: \n', lr.coef_)
# The mean squared error
 
 
mse = mean_squared_error(Y_test, Y_test_pred , multioutput='raw_values')

# Explained variance score: 1 is perfect prediction
print('Variance score: %.2f' % r2_score(Y_test, Y_test_pred)) #To show accurucy of module.
# Plot outputs
plt.scatter(X_test, Y_test,  color='red')
plt.plot(X_test, Y_test_pred, color='blue', linewidth=1)
plt.xticks(())
plt.yticks(())
plt.show()
