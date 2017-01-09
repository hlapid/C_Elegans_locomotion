# -*- coding: utf-8 -*-
"""
Created on Mon Jan 11 16:17:37 2016

@author: hlapid
"""
import pandas as pd
import numpy as np

GLOBAL_PARAMSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\\params.csv'
paramsDF = pd.read_csv(GLOBAL_PARAMSFILE, header=None, index_col=0, names = ['value', 'description'])
paramsDF.at['simRuntime','value'] = 200
paramsDF.to_csv('C:\Users\hlapid\Desktop\CSVFiles\\params3.csv',header=False)