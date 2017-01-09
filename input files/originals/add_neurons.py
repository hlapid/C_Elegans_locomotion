# -*- coding: utf-8 -*-
"""
Created on Wed Jan 20 12:40:31 2016

@author: hlapid
"""

import pandas as pd
import numpy as np
import pprint
import csv

#GLOBAL_NAMESFILE = 'C:\Users\hlapid\Desktop\CSVFiles\original_csv_files\names.csv'
#GLOBAL_WEIGHTSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\OriginalCHweights.csv'

def createNamesDict():
    namesDict = {}
    with open('names.csv', 'r') as f:
        for line in f:
             currStr = line.split(',')
             namesDict[currStr[0]] = {"number" : int(currStr[1])-1,
                                      "type" : currStr[2][:2]}
    return namesDict
    
def createConnectivityDict(namesDict):
    EJDict = {}
    CHDict = {}
    NMJDict = {}
    with open('NeuronConnect.csv', 'r') as f:
        next(f)        
        for line in f:
             currStr = line.split(',')
             if currStr[0] in namesDict.keys() and currStr[1] in namesDict.keys():
                 if currStr[2]=='EJ':
                     if not currStr[0] in EJDict.keys():
                         EJDict[currStr[0]] = {}
                     EJDict[currStr[0]][currStr[1]] = currStr[3][0:len(currStr[3])-1]
                    
                 elif currStr[2]=='S' or currStr[2]=='Sp':
                     if not currStr[0] in CHDict.keys():
                         CHDict[currStr[0]] = {}
                     CHDict[currStr[0]][currStr[1]] = currStr[3][0:len(currStr[3])-1]
                 elif currStr[2]=='NMJ':
                     if not currStr[0] in NMJDict.keys():
                         NMJDict[currStr[0]] = {}
                     NMJDict[currStr[0]][currStr[1]] = currStr[3][0:len(currStr[3])-1]
    f.closed    
    with open('NeuronType.csv', 'r') as f:
        next(f)        
        for line in f:
             currStr = line.split(',')
             if currStr[0] in namesDict.keys():
                 namesDict[currStr[0]]["position"] = float(currStr[1])
    return EJDict,CHDict,NMJDict,namesDict
    
def write2CSVfile(insertDict, names,fileName):
    newMatrix = np.zeros((len(names),len(names)))

    for i in range(len(names)):
        for pre_neuron_name in names.keys():
            if names[pre_neuron_name]['number']==i:
                preSynapticName = pre_neuron_name
                break
        for j in range(len(names)):
            for post_neuron_name in names.keys():
                if names[post_neuron_name]['number'] == j:
                    postSynapticName = post_neuron_name
                    break
            if preSynapticName in insertDict.keys():
                if postSynapticName in insertDict[preSynapticName].keys():
#                    print(str(i),str(j),preSynapticName,postSynapticName,insertDict[preSynapticName][postSynapticName])
                    newMatrix[i][j] = int(insertDict[preSynapticName][postSynapticName])

    np.savetxt(fileName+'.csv', newMatrix, delimiter=",",fmt='%1.1d')
    return newMatrix

def writeMotorOutputFile(preMotorNames, motorNames, NMJDict, fileName):
    newMatrix = np.zeros((len(preMotorNames),len(motorNames)))

    for i in range(len(preMotorNames)):
        for pre_neuron_name in preMotorNames.keys():
            if preMotorNames[pre_neuron_name]['number']==i:
                preSynapticName = pre_neuron_name
                break
        for j in range(len(motorNames)):
            for post_neuron_name in motorNames.keys():
                if motorNames[post_neuron_name]['number'] == j+len(preMotorNames):
                    postSynapticName = post_neuron_name
                    break
            if preSynapticName in NMJDict.keys() and postSynapticName in NMJDict[preSynapticName].keys():
#                    print(str(i),str(j),preSynapticName,postSynapticName,insertDict[preSynapticName][postSynapticName])
                    newMatrix[i][j] = round(float(NMJDict[preSynapticName][postSynapticName]),3)

    np.savetxt(fileName+'.csv', newMatrix, delimiter=",",fmt='%1.3f')
    return newMatrix

def writeMotorDistFile(preMotorNames, motorNames, fileName):
    newMatrix = np.zeros((len(preMotorNames),len(motorNames)))

    for i in range(len(preMotorNames)):
        for pre_neuron_name in preMotorNames.keys():
            if preMotorNames[pre_neuron_name]['number']==i:
                preSynapticName = pre_neuron_name
                break
        for j in range(len(motorNames)):
            for post_neuron_name in motorNames.keys():
                if motorNames[post_neuron_name]['number'] == j+len(preMotorNames):
                    postSynapticName = post_neuron_name
                    break
            newMatrix[i][j] = round(abs(preMotorNames[preSynapticName]['position']-motorNames[postSynapticName]['position']),2)

    np.savetxt(fileName+'.csv', newMatrix, delimiter=",",fmt='%1.2f')
    return newMatrix

def write2Distfile(names,fileName):
    newMatrix = np.zeros((len(names),len(names)))
    for i in range(len(names)):
        for pre_neuron_name in names.keys():
            if names[pre_neuron_name]['number']==i:
                preSynapticName = pre_neuron_name
                break
        for j in range(len(names)):
            for post_neuron_name in names.keys():
                if names[post_neuron_name]['number'] == j:
                    postSynapticName = post_neuron_name
                    break
            newMatrix[i][j] = round(abs(names[preSynapticName]['position']-names[postSynapticName]['position']),2)

    np.savetxt(fileName+'.csv', newMatrix, delimiter=",",fmt='%1.2f')
    return newMatrix

def writeHeaderfile(names):
    headers = {}
    for i in range(len(names)):
        for pre_neuron_name in names.keys():
            if names[pre_neuron_name]['number']==i:
                headers[i] = {"name" : pre_neuron_name,
                              "position" : names[pre_neuron_name]["position"],
                              "type" : names[pre_neuron_name]["type"]}
                break

    writer = csv.writer(open('NameHeaders.csv', 'wb'))
    IN_index=0    
    SN_index=0    
    MN_index=0    
    BS_index=0    
    for key in headers.keys():
        if headers[key]['type']=="IN":
            writer.writerow([headers[key]['type'],headers[key]['name'],str(IN_index),str(key+1),str(headers[key]['position'])])
            IN_index = IN_index+1
        elif headers[key]['type']=="SN":
            writer.writerow([headers[key]['type'],headers[key]['name'],str(SN_index),str(key+1),str(headers[key]['position'])])
            SN_index = SN_index+1
        elif headers[key]['type']=="MN":
            writer.writerow([headers[key]['type'],headers[key]['name'],str(MN_index),str(key+1),str(headers[key]['position'])])
            MN_index = MN_index+1
        elif headers[key]['type']=="BS":
            writer.writerow([headers[key]['type'],headers[key]['name'],str(BS_index),str(key+1),str(headers[key]['position'])])
            BS_index = BS_index+1
    return 
def compair2Original(originalMatrixFile, newMatrix):
    oldDF = pd.read_csv(originalMatrixFile, header=None)    
    newDF = pd.DataFrame(newMatrix)

    b = abs(oldDF) - newDF.iloc[range(len(oldDF))][range(len(oldDF))]
    temp = b.sum()
    b = temp.sum()
    return b

if __name__ == "__main__":
    namesDict = createNamesDict()
    [EJDict,CHDict,NMJDict,namesDict] = createConnectivityDict(namesDict)
    writeHeaderfile(namesDict)
    neuronNamesDict = {}
    motorNamesDict = {}
    subkeys = ["IN", "MN", "SN"]
    for key, value in namesDict.items(): 
        if value['type'] in subkeys:
            neuronNamesDict[key] = namesDict[key]
        else:
            motorNamesDict[key] = namesDict[key]
    DistMatrix = write2Distfile(neuronNamesDict,'distances_normed')  
    motoDistMatrix = writeMotorDistFile(neuronNamesDict, motorNamesDict, 'NMJ_distances')   
    NMJMatrix = writeMotorOutputFile(neuronNamesDict, motorNamesDict, NMJDict, 'NMJweights')
    EJMatrix = write2CSVfile(EJDict, neuronNamesDict,'EJ_matrix')
    CHMatrix = write2CSVfile(CHDict, neuronNamesDict,'CH_matrix')
    b_EJ = compair2Original("EJweights_normed.csv", EJMatrix)
    b_CH = compair2Original("CHweights_normed.csv", CHMatrix)
    b_Dist = compair2Original("distances_normed.csv",DistMatrix)
#    print(b_EJ,b_CH,b_Dist)