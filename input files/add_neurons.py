# -*- coding: utf-8 -*-
"""
Created on Wed Jan 20 12:40:31 2016

@author: hlapid
"""

import pandas as pd
import numpy as np
import pprint

GLOBAL_NAMESFILE = 'C:\Users\hlapid\Desktop\CSVFiles\original_csv_files\names.csv'
GLOBAL_WEIGHTSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\OriginalCHweights.csv'

def createDict():
    namesDict = {}
    with open(GLOBAL_NAMESFILE, 'r') as f:
        for line in f:
             currStr = line.split(',')
             namesDict[currStr[0]] = {"number" : int(currStr[1])-1,
                                      "type" : currStr[2][:2]}
    return namesDict

if __name__ == "__main__":
    namesDict = createDict()
    print(namesDict)