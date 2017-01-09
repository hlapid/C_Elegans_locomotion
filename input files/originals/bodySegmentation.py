# -*- coding: utf-8 -*-
"""
Created on Tue Feb 23 14:40:09 2016

@author: hlapid
"""

import pandas as pd
import numpy as np
import pprint
import csv

#GLOBAL_NAMESFILE = 'C:\Users\hlapid\Desktop\CSVFiles\original_csv_files\names.csv'
#GLOBAL_WEIGHTSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\OriginalCHweights.csv'

def createSegmentDF():
    segmentDF = pd.read_csv('bodySegments.csv', header=0,names=['bodySegment','position'])
    segmentDF = segmentDF.drop_duplicates(subset='bodySegment')
    segmentDF = segmentDF.sort_values(by = 'bodySegment')
    segmentDF = segmentDF.reset_index(drop=True)
    return segmentDF    
#def connectSegmentDF():

if __name__ == "__main__":
    segmentDF = createSegmentDF()
