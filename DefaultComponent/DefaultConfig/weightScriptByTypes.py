# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

# Creates a dictionary of name:number pairs
import pprint
import pandas as pd
import os
import time

def createDict():
    namesDict = {}
    namesFile = 'C:\Users\hlapid\Desktop\CSVFiles\\names.csv'
    with open(namesFile, 'r') as f:
        for line in f:
             currStr = line.split(',')
             namesDict[currStr[0]] = {"number" : int(currStr[1])-1,
                                      "type" : currStr[2][:2]}
    return namesDict

# Create matrix of chemical weights
def createMatrix():
    weightsFile = 'C:\Users\hlapid\Desktop\CSVFiles\\CHweights_normed.csv'
    chMatrix = []
    with open(weightsFile, 'r') as f:
        for line in f:
             currStr = line.split(',')
             chMatrix.append(currStr)
    return chMatrix

def applyCommands(chMatrix, commandsList, namesDict):
    for command in commandsList:
        # illegal input checks    
        if ((command[0] not in namesDict.keys()) and (command[0] != 'all')):
            print("illegal pre-synaptic neuron: " + command[0])
            break
        elif ((command[1] not in namesDict.keys()) and (command[1] != 'all')):
            print("illegal post-synaptic neuron: " + command[1])
            break
        
        # if I have to change all outgoing connections:
        elif (command[1] == 'all'):
            # change all outgoing connections to excitatory
            if command[2] == '+':
                for index in range(len(chMatrix[namesDict[command[0]]['number']])):
                    chMatrix[namesDict[command[0]]['number']][index] = chMatrix[namesDict[command[0]]['number']][index].replace('-','')
            # change all outgoing connections to inhibitory
            if command[2] == '-':
                for index in range(len(chMatrix[namesDict[command[0]]['number']])):
                    if '-' not in chMatrix[namesDict[command[0]]['number']][index]:
                        chMatrix[namesDict[command[0]]['number']][index] = '-' + chMatrix[namesDict[command[0]]['number']][index]
        
        # if I have to change all incoming connections:
        elif (command[0] == 'all'):
            # change all incoming connections to excitatory
            if command[2] == '+':
                for index in range(len(chMatrix[namesDict[command[1]]['number']])):
                    chMatrix[index][namesDict[command[1]]['number']] = chMatrix[index][namesDict[command[1]]['number']].replace('-','')
            # change all incoming connections to inhibitory
            if command[2] == '-':
                for index in range(len(chMatrix[namesDict[command[1]]['number']])):
                    if '-' not in chMatrix[index][namesDict[command[1]]['number']]:
                        chMatrix[index][namesDict[command[1]]['number']] = '-' + chMatrix[index][namesDict[command[1]]['number']]
        else:
            if (command[2] == '+') and ('-' in chMatrix[namesDict[command[0]]['number']][namesDict[command[1]]['number']]):
                chMatrix[namesDict[command[0]]['number']][namesDict[command[1]]['number']] = chMatrix[namesDict[command[0]]['number']][namesDict[command[1]]['number']].replace('-','')
            elif (command[2] == '-') and ('-' not in chMatrix[namesDict[command[0]]['number']][namesDict[command[1]]['number']]):
                chMatrix[namesDict[command[0]]['number']][namesDict[command[1]]['number']] = '-' + chMatrix[namesDict[command[0]]['number']][namesDict[command[1]]['number']]
    return chMatrix

def writeToFile(newMatrix):
    outputFile = open("C:\Users\hlapid\Desktop\CSVFiles\\CHweights_normed.csv", "w+")
    for line in newMatrix:
        outputFile.write(','.join(line))
    outputFile.close()

def applyProtected(newMatrix, protectedDict, namesDict):
    for i in range(len(newMatrix)):
        for pre_neuron_name in namesDict.keys():
            if namesDict[pre_neuron_name]['number'] == i:
                preSynapticName = pre_neuron_name
                break        
        for j in range(len(newMatrix[i])):
            for post_neuron_name in namesDict.keys():
                if namesDict[post_neuron_name]['number'] == j:
                    postSynapticName = post_neuron_name
                    break        
            if preSynapticName in protectedDict.keys():
                if postSynapticName in protectedDict[preSynapticName].keys():
                    sign = protectedDict[preSynapticName][postSynapticName]
                    if (sign == "+") and ('-' in newMatrix[i][j]):
                        newMatrix[i][j] = newMatrix[i][j].replace('-','')
                    elif (sign == "-") and not ('-' in newMatrix[i][j]):
                        newMatrix[i][j] = "-" + newMatrix[i][j]
            
    return newMatrix
    
    

if __name__ == "__main__":
    namesDict = createDict()

    chMatrix = createMatrix()
  
    # List of all protected couples = synaptic protection we have info of
    # and we're not interested in changing.
    protectedDict = {"ALML" : {"AVDL" : "+", "AVDR" : "+"},
                     "ALMR" : {"AVDR" : "+", "AVDL" : "+"},
                     "AVDL" : {"AVAL" : "+", "AVAR" : "+"},
                     "AVDR" : {"AVAL" : "+", "AVAR" : "+"},
                     "AVM" : {"AVBL" : "-", "AVBR" : "-"},
                     "RIML" : {"AVBL" : "-", "AVBR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", },
                     "RIMR" : {"AVBL" : "-", "AVBR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", },
                     "AIBL" : {"RIML" : "-", "RIMR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", },
                     "AIBR" : {"RIML" : "-", "RIMR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", }}
    configurationDict = {1 : {"IN" : "+", "MN" : "+", "SN" : "+"},
                         2 : {"IN" : "+", "MN" : "+", "SN" : "-"},
                         3 : {"IN" : "+", "MN" : "-", "SN" : "+"},
                         4 : {"IN" : "+", "MN" : "-", "SN" : "-"},
                         5 : {"IN" : "-", "MN" : "+", "SN" : "+"},
                         6 : {"IN" : "-", "MN" : "+", "SN" : "-"},
                         7 : {"IN" : "-", "MN" : "-", "SN" : "+"},
                         8 : {"IN" : "-", "MN" : "-", "SN" : "-"}}
                     
            
   
    # Create list of commands - each command is a tuple:
    # (preSynaptic (=row in chMatrix), postSynaptic (=column in chMatrix), synapse sign)
    # AVD inhibiting all example:`
    # exmaple: commandsList.append(('AVDL', 'all', '+'))
    # exmaple: commandsList.append(('AVDR', 'all', '+'))    
    
    commandsList = []
    
    # init condition - all interneurons are inhibitory as preset
    for neuron_name in namesDict.keys():
        if namesDict[neuron_name]['type'] == "IN":
            commandsList.append((neuron_name, 'all', '-'))
    
    
    # trying different AVD configutations:    
    t0 = time.time()        
    for input_config in configurationDict:
            
        # update weights csv
        commandsList.append(('all', 'AVDL', configurationDict[input_config]["IN"]))
        commandsList.append(('all', 'AVDL', configurationDict[input_config]["MN"]))
        commandsList.append(('all', 'AVDL', configurationDict[input_config]["SN"]))
        commandsList.append(('all', 'AVDR', configurationDict[input_config]["IN"]))
        commandsList.append(('all', 'AVDR', configurationDict[input_config]["MN"]))
        commandsList.append(('all', 'AVDR', configurationDict[input_config]["SN"]))
        newMatrix = applyCommands(chMatrix, commandsList, namesDict) 
        protectedMatrix = applyProtected(newMatrix, protectedDict, namesDict)
        writeToFile(protectedMatrix)
        
        # run the rhapsody simulation
        os.system("DefaultComponent.bat run MainDefaultComponent")
        df = pd.read_csv("dis_neuron_log.csv")

        # print the current configuration        
        print configurationDict[input_config], "\n"        
        
        # print AVM deviation from the "optimal state":
        print "AVM std from 0 in steps 1-50: ", df['AVM'][:51].std()
        print "AVM std from 1 in steps 51-56: ", df['AVM'][51:57].sub(1).std()
        print "AVM summed std: ", df['AVM'][:51].std() + df['AVM'][51:57].sub(1).std(), "\n"
        
        print "ALML std from 0 in steps 1-50: ", df['ALML'][:51].std()
        print "ALML std from 1 in steps 51-56: ", df['ALML'][51:57].sub(1).std()
        print "ALML summed std: ", df['ALML'][:51].std() + df['ALML'][51:57].sub(1).std(), "\n"
        
        print "ALMR std from 0 in steps 1-50: ", df['ALMR'][:51].std()
        print "ALMR std from 1 in steps 51-56: ", df['ALMR'][51:57].sub(1).std()
        print "ALMR summed std: ", df['ALMR'][:51].std() + df['ALMR'][51:57].sub(1).std(), "\n"
        
        
    
    print "total runtime: ", time.time() - t0
        
        
        
    
    
    
    
    