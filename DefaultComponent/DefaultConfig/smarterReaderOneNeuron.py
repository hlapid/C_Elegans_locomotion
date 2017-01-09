# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

# Creates a dictionary of name:number pairs
import pandas as pd
import numpy as np
import os
import time
import pprint

GLOBAL_NAMESFILE = 'C:\Users\hlapid\Desktop\CSVFiles\\names.csv'
GLOBAL_WEIGHTSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\CHweights_normed.csv'

def createDict():
    # read the 
    namesDF = pd.read_csv(GLOBAL_NAMESFILE, header=None, index_col=0, names=['number','type'])
    return namesDF

# Create matrix of chemical weights
def createMatrix(namesDF):
    weightDF = pd.read_csv(GLOBAL_WEIGHTSFILE, header=None, names = namesDF.index)
    weightDF = weightDF.set_index(namesDF.index)
    return weightDF

# args:
# chDF - chemical DataFrame = the table of the chemical weights (it's exactly
# like weightDF in createMatrix)
def applyCommands(chDF, commandsList, namesDF):
    # command looks like: (preSyn neuron, postSyn neuron, sign = +/-)    
    for command in commandsList:
        # if I have to change all outgoing connections - I need to change the row
        # changing row is done by chDF.loc[row name]
        if (command[1] == 'all'):
            # change all outgoing connections to excitatory
            if command[2] == '+':
                chDF.loc[command[0]] = chDF.loc[command[0]].abs()
            # change all outgoing connections to inhibitory
            if command[2] == '-':
                chDF.loc[command[0]] = -chDF.loc[command[0]].abs()
                        
        # if I have to change all incoming connections - I need to change the column
        # this is done by chDF[col name]                        
        elif (command[0] == 'all'):
            # change all incoming connections to excitatory
            if command[2] == '+':
                chDF[command[1]] = chDF[command[1]].abs()
            # change all incoming connections to inhibitory
            if command[2] == '-':
                chDF[command[1]] = -chDF[command[1]].abs()
        # the case in which none of the commands = 'all'
        # we need to change the sign in a specific place
        else:
            if command[2] == '+':
                chDF.at[command[0],command[1]] = np.absolute(chDF.at[command[0],command[1]])
            else:
                chDF.at[command[0],command[1]] = -np.absolute(chDF.at[command[0],command[1]])
    return chDF

def applyProtected(chDF, protectedDict):
    for pre in protectedDict.keys():
        for post in protectedDict[pre].keys():
            sign = protectedDict[pre][post]
            if sign == '+':
                chDF.at[pre,post] = np.absolute(chDF.at[pre,post])
            else:
                chDF.at[pre,post] = -np.absolute(chDF.at[pre,post])
    return chDF
    
    
# this is the beginning of the main()
if __name__ == "__main__":
    namesDF = createDict()
    chDF = createMatrix(namesDF)
  
    # Dictionary of all protected couples = synaptic protection we have info of
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
    # init condition - all interneurons are inhibitory at preset
    for name, row in namesDF.iterrows():
        if row['type'] == "IN":
            commandsList.append((name, 'all', '-'))
    
    newMatrix = applyCommands(chDF, commandsList, namesDF) 

            

    # trying different AVD configutations:    
    t0 = time.time() 

    #testing block
    commandsList = []
        
    # update weights csv
    for name, row in namesDF.iterrows():
        if row['type'] == "IN":
            commandsList.append((name, 'AVDL', configurationDict[3]["IN"]))
            commandsList.append((name, 'AVDR', configurationDict[3]["IN"]))
        elif row['type'] == "SN":
            commandsList.append((name, 'AVDL', configurationDict[3]["SN"]))
            commandsList.append((name, 'AVDR', configurationDict[3]["SN"]))
        elif row['type'] == "MN":
            commandsList.append((name, 'AVDL', configurationDict[3]["MN"]))
            commandsList.append((name, 'AVDR', configurationDict[3]["MN"]))
    
    newMatrix = applyCommands(newMatrix, commandsList, namesDF) 
    protectedMatrix = applyProtected(newMatrix, protectedDict)
    protectedMatrix.to_csv(GLOBAL_WEIGHTSFILE, header=False, index=False)  
    
    # run the rhapsody simulation
    os.system("DefaultComponent.bat run MainDefaultComponent")
    logDF = pd.read_csv("dis_neuron_log.csv")

    # print the current configuration        
    print configurationDict[input_config], "\n"     
    
    
    # print SN std from the "optimal state", which is 0 until the event
    # and 1 for 6 timesteps after the event.
    print "AVM std from 0 in steps 1-50: ", logDF['AVM'][:51].std()
    print "AVM std from 1 in steps 51-56: ", logDF['AVM'][51:57].sub(1).std()
    print "AVM summed std: ", logDF['AVM'][:51].std() + logDF['AVM'][51:57].sub(1).std(), "\n"
    
    print "ALML std from 0 in steps 1-50: ", logDF['ALML'][:51].std()
    print "ALML std from 1 in steps 51-56: ", logDF['ALML'][51:57].sub(1).std()
    print "ALML summed std: ", logDF['ALML'][:51].std() + logDF['ALML'][51:57].sub(1).std(), "\n"
    
    print "ALMR std from 0 in steps 1-50: ", logDF['ALMR'][:51].std()
    print "ALMR std from 1 in steps 51-56: ", logDF['ALMR'][51:57].sub(1).std()
    print "ALMR summed std: ", logDF['ALMR'][:51].std() + logDF['ALMR'][51:57].sub(1).std(), "\n"
        
        

    print "total runtime: ", time.time() - t0
        
        
        
    
    
    
    
    