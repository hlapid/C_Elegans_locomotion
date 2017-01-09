# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

# Creates a dictionary of name:number pairs
def createDict():
    namesDict = {}
    namesFile = 'names.csv'
    with open(namesFile, 'r') as f:
        for line in f:
             currStr = line.split(',')
             namesDict[currStr[0]] = int(currStr[1])-1
    return namesDict

# Create matrix of chemical weights
def createMatrix():
    weightsFile = 'CHweights_normed.csv'
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
                for index in range(len(chMatrix[namesDict[command[0]]])):
                    chMatrix[namesDict[command[0]]][index] = chMatrix[namesDict[command[0]]][index].replace('-','')
            # change all outgoing connections to inhibitory
            if command[2] == '-':
                for index in range(len(chMatrix[namesDict[command[0]]])):
                    if '-' not in chMatrix[namesDict[command[0]]][index]:
                        chMatrix[namesDict[command[0]]][index] = '-' + chMatrix[namesDict[command[0]]][index]
        
        # if I have to change all incoming connections:
        elif (command[0] == 'all'):
            # change all incoming connections to excitatory
            if command[2] == '+':
                for index in range(len(chMatrix[namesDict[command[1]]])):
                    chMatrix[index][namesDict[command[1]]] = chMatrix[index][namesDict[command[1]]].replace('-','')
            # change all incoming connections to inhibitory
            if command[2] == '-':
                for index in range(len(chMatrix[namesDict[command[1]]])):
                    if '-' not in chMatrix[index][namesDict[command[1]]]:
                        chMatrix[index][namesDict[command[1]]] = '-' + chMatrix[index][namesDict[command[1]]]
        else:
            if (command[2] == '+') and ('-' in chMatrix[namesDict[command[0]]][namesDict[command[1]]]):
                chMatrix[namesDict[command[0]]][namesDict[command[1]]] = chMatrix[namesDict[command[0]]][namesDict[command[1]]].replace('-','')
            elif (command[2] == '-') and ('-' not in chMatrix[namesDict[command[0]]][namesDict[command[1]]]):
                chMatrix[namesDict[command[0]]][namesDict[command[1]]] = '-' + chMatrix[namesDict[command[0]]][namesDict[command[1]]]
    return chMatrix

def writeToFile(newMatrix):
    outputFile = open("CHweights_normed.csv", "w+")
    for line in newMatrix:
        outputFile.write(','.join(line))
    outputFile.close()

def applyProtected(newMatrix, protectedDict, namesDict):
    for i in range(len(newMatrix)):
        preSynapticName = namesDict.keys()[namesDict.values().index(i)]
        for j in range(len(newMatrix[i])):
            postSynapticName = namesDict.keys()[namesDict.values().index(j)]
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
            
    # Create list of commands - each command is a tuple:
    # (preSynaptic (=row in chMatrix), postSynaptic (=column in chMatrix), synapse sign)
    
    commandsList = []
    
    # AVD inh`
    commandsList.append(('AVDL', 'all', '-'))
    commandsList.append(('AVDR', 'all', '-'))
    
    
    
    newMatrix = applyCommands(chMatrix, commandsList, namesDict)
    
    protectedMatrix = applyProtected(newMatrix, protectedDict, namesDict)
    
    writeToFile(protectedMatrix)