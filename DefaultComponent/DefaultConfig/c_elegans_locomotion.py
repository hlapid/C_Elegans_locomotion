# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

#import os
#import time
import pandas as pd
import numpy as np

GLOBAL_NAMESFILE = 'C:\Users\hlapid\Desktop\CSVFiles\\names.csv'
GLOBAL_CHWEIGHTSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\CHweights_normed.csv'
GLOBAL_NMJWEIGHTSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\NMJweights.csv'
GLOBAL_PARAMSFILE = 'C:\Users\hlapid\Desktop\CSVFiles\params.csv'
GLOVAL_LOGFILE = 'dis_neuron_log.csv'

def createDict(namesfile):
    # read the 
    namesDF = pd.read_csv(namesfile, header=None, index_col=0, names=['number','type'])
    return namesDF

# Create matrix of chemical weights
def createMatrix(namesDF, chweightsfile,nmjweightsfile):
# t for type - the instancces' type required - N for neuron, M for muscle
    neuronNamesDF = namesDF[namesDF['type']!="BS"]
#    print(neuronNamesDF)
    muscleNamesDF = namesDF[namesDF['type']=="BS"]
    muscleNamesDF.loc[:,'number'] = muscleNamesDF.loc[:,'number']-79

    nmjweightDF = pd.read_csv(nmjweightsfile, header=None, names = muscleNamesDF.index)
    nmjweightDF = nmjweightDF.set_index(neuronNamesDF.index)

    chweightDF = pd.read_csv(chweightsfile, header=None, names = neuronNamesDF.index)
    chweightDF = chweightDF.set_index(neuronNamesDF.index)
    return chweightDF,nmjweightDF,muscleNamesDF

# args:
# chDF - chemical DataFrame = the table of the chemical weights (it's exactly
# like chweightDF in createMatrix)
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
    protectedDF = chDF.copy()    
    for pre in protectedDict.keys():
        for post in protectedDict[pre].keys():
            sign = protectedDict[pre][post]
            if sign == '+':
                protectedDF.at[pre,post] = np.absolute(protectedDF.at[pre,post])
            else:
                protectedDF.at[pre,post] = -np.absolute(protectedDF.at[pre,post])
    return protectedDF
def changeParams(paramsDF, EJCoeff, CHCoeff, NMJCoeff, simRuntime, decayTime, propCoeff,
    LeakyCoeff, EJSynDelay, CHSynDelay, NMJSynDelay, SignalAttenuation, delta_t):
    paramsDF.at['EJCoeff','value'] = EJCoeff
    paramsDF.at['CHCoeff','value'] = CHCoeff
    paramsDF.at['NMJCoeff','value'] = NMJCoeff
    paramsDF.at['simRuntime','value'] = simRuntime
    paramsDF.at['decayTime','value'] = decayTime
    paramsDF.at['propCoeff','value'] = propCoeff
    paramsDF.at['LeakyCoeff','value'] = LeakyCoeff
    paramsDF.at['EJSynDelay','value'] = EJSynDelay
    paramsDF.at['CHSynDelay','value'] = CHSynDelay
    paramsDF.at['NMJSynDelay','value'] = NMJSynDelay
    paramsDF.at['SignalAttenuation','value'] = SignalAttenuation
    paramsDF.at['delta_t','value'] = delta_t
    return paramsDF
    
## this is the beginning of the main()
#if __name__ == "__main__":
    
    # Dictionary of all protected couples = synaptic protection we have info of
    # and we're not interested in changing.
#    protectedDict = {"ALML" : {"AVDL" : "+", "AVDR" : "+"},
#                     "ALMR" : {"AVDR" : "+", "AVDL" : "+"},
#                     "AVDL" : {"AVAL" : "+", "AVAR" : "+"},
#                     "AVDR" : {"AVAL" : "+", "AVAR" : "+"},
#                     "AVM" : {"AVBL" : "-", "AVBR" : "-"},
#                     "RIML" : {"AVBL" : "-", "AVBR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", },
#                     "RIMR" : {"AVBL" : "-", "AVBR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", },
#                     "AIBL" : {"RIML" : "-", "RIMR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", },
#                     "AIBR" : {"RIML" : "-", "RIMR" : "-", "SMDDL" : "-", "SMDVL" : "-", "SMDDR" : "-", "SMDVR" : "-", }}
#                     
#  
#    t0 = time.time() 
#    namesDF = createDict(GLOBAL_NAMESFILE)
#    [chDF,nmjDF,muscleNamesDF] = createMatrix(namesDF, GLOBAL_CHWEIGHTSFILE,GLOBAL_NMJWEIGHTSFILE)
#    
#    # Create list of commands - each command is a tuple:
#    # (preSynaptic (=row in chMatrix), postSynaptic (=column in chMatrix), synapse sign)
#    # AVD inhibiting all example:`
#    # exmaple: commandsList.append(('AVDL', 'all', '+'))
#    # exmaple: commandsList.append(('AVDR', 'all', '+'))    
#
#    commandsList = []
#    for pre_name, pre_row in namesDF.iterrows():
#        # presynaptic cell is interneuron
#        if pre_row['type'] == "IN":
#            for post_name, post_row in namesDF.iterrows():   
#                if post_row['type'] == "IN":
#                    commandsList.append((pre_name, post_name, '-'))
#                elif post_row['type'] == "SN":
#                    commandsList.append((pre_name, post_name, '-'))
#                elif post_row['type'] == "MN":
#                    commandsList.append((pre_name, post_name, '+'))
#        # presynaptic cell is sensory
#        elif pre_row['type'] == "SN":
#            for post_name, post_row in namesDF.iterrows():   
#                if post_row['type'] == "IN":
#                    commandsList.append((pre_name, post_name, '+'))
#                elif post_row['type'] == "SN":
#                    commandsList.append((pre_name, post_name, '+'))
#                elif post_row['type'] == "MN":
#                    commandsList.append((pre_name, post_name, '+'))
#        # presynaptic cell is motor
#        elif pre_row['type'] == "MN":
#            for post_name, post_row in namesDF.iterrows():   
#                if post_row['type'] == "IN":
#                    commandsList.append((pre_name, post_name, '-'))
#                elif post_row['type'] == "SN":
#                    commandsList.append((pre_name, post_name, '-'))
#                elif post_row['type'] == "MN":
#                    commandsList.append((pre_name, post_name, '+'))
#            
#    newMatrix = applyCommands(chDF, commandsList, namesDF) 
#    protectedMatrix = applyProtected(newMatrix, protectedDict)
#    protectedMatrix.to_csv(GLOBAL_WEIGHTSFILE, header=False, index=False)  
#    
#    # run the rhapsody simulation
#    os.system("DefaultComponent.bat run MainDefaultComponent")
#    logDF = pd.read_csv(GLOVAL_LOGFILE)
#

        
        
        
    
    
    
    
    