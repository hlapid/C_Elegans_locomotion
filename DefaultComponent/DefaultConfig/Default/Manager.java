/*********************************************************************
	Rhapsody	: 7.6.1
	Login		: hlapid
	Component	: DefaultComponent
	Configuration 	: DefaultConfig
	Model Element	: Manager
//!	Generated Date	: Wed, 15, Jun 2016 
	File Path	: DefaultComponent/DefaultConfig/Default/Manager.java
*********************************************************************/

package Default;

//## dependency io 
import java.io.*;
//## auto_generated
import java.util.*;
//## dependency text 
import java.text.*;
//## dependency util 
import java.util.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.states.*;

//----------------------------------------------------------------------------
// Default/Manager.java                                                                  
//----------------------------------------------------------------------------

//## package Default 


//## class Manager 
public class Manager implements RiJStateConcept {
    
    public Reactive reactive;		//## ignore 
    
    /**
     * same as EJmatrix for chemical synapses
     * used only for construction of CHSynWeights vectors
    */
    protected double[][] CHweightsMatrix;		//## attribute CHweightsMatrix 
    
    /**
     * 60X60 matrix of electric synapses weights
     * used only for construction of EJSynWeights vectors
    */
    protected double[][] EJweightsMatrix;		//## attribute EJweightsMatrix 
    
    protected int[][] NMJDistMatrix;		//## attribute NMJDistMatrix 
    
    protected double[][] NMJweightsMatrix;		//## attribute NMJweightsMatrix 
    
    protected int N_of_muscles;		//## attribute N_of_muscles 
    
    /**
     * maximal number of neurons in simulation
    */
    protected int N_of_neurons;		//## attribute N_of_neurons 
    
    protected MUS[] bodyMuscles;		//## attribute bodyMuscles 
    
    /**
     * clock time is the simulation time step;
    */
    protected int clockTime = 0;		//## attribute clockTime 
    
    /**
     * simulation log - all construction commans, triggers etc.
    */
    protected FileOutputStream cmdFile;		//## attribute cmdFile 
    
    /**
     * Java print object for cmfFIle
    */
    protected PrintStream cmdFileP;		//## attribute cmdFileP 
    
    /**
     * discrete table of distances between neurons for propagation times of triggers between neurons (79X79)
    */
    protected int[][] distMatrix;		//## attribute distMatrix 
    
    /**
     * hitObs guards from infinite looping over hitObs states, once entering hitObs state, hitObs = 1.
     * 
    */
    protected boolean hitObs = false;		//## attribute hitObs 
    
    protected Gen_IN[] interNeurons;		//## attribute interNeurons 
    
    protected ArrayList<String> interNeuronsList;		//## attribute interNeuronsList 
    
    protected Gen_MN[] motorNeurons;		//## attribute motorNeurons 
    
    protected ArrayList<String> motorNeuronsList;		//## attribute motorNeuronsList 
    
    protected Map mus_params_map;		//## attribute mus_params_map 
    
    protected Map muscle_names;		//## attribute muscle_names 
    
    protected ArrayList<String> musclesList;		//## attribute musclesList 
    
    /**
     * hash table of neuron number (1..60) and neuron name
    */
    protected Map neuron_names;		//## attribute neuron_names 
    
    /**
     * lists the activation values of all neurons at each simulation time step:
     * TS NEURON_NAME
     * int double
    */
    protected FileOutputStream outputFile;		//## attribute outputFile 
    
    /**
     * printed version of outputFile (Java object 'printStream')
    */
    protected PrintStream outputFileP;		//## attribute outputFileP 
    
    protected Map params_map;		//## attribute params_map 
    
    protected double propCoeff;		//## attribute propCoeff 
    
    protected Gen_SN[] sensoryNeurons;		//## attribute sensoryNeurons 
    
    protected ArrayList<String> sensoryNeuronsList;		//## attribute sensoryNeuronsList 
    
    /**
     * simRuntime is the final simulation time step, conditioned at manager loop for exiting the simulation
    */
    protected int simRuntime;		//## attribute simRuntime 
    
    protected ArrayList<Gen_IN> itsGen_IN = itsGen_IN = new ArrayList<Gen_IN>();		//## link itsGen_IN 
    
    protected ArrayList<Gen_MN> itsGen_MN = itsGen_MN = new ArrayList<Gen_MN>();		//## link itsGen_MN 
    
    protected ArrayList<Gen_MUS> itsGen_MUS = itsGen_MUS = new ArrayList<Gen_MUS>();		//## link itsGen_MUS 
    
    protected ArrayList<Gen_Neuron> itsGen_Neuron = itsGen_Neuron = new ArrayList<Gen_Neuron>();		//## link itsGen_Neuron 
    
    protected ArrayList<Gen_SN> itsGen_SN = itsGen_SN = new ArrayList<Gen_SN>();		//## link itsGen_SN 
    
    protected ArrayList<MUS> itsMUS = itsMUS = new ArrayList<MUS>();		//## link itsMUS 
    
    //#[ ignore 
    public static final int RiJNonState=0;
    public static final int manager=1;
    public static final int hit_obs=2;
    //#]
    protected int rootState_subState;		//## ignore 
    
    protected int rootState_active;		//## ignore 
    
    public static final int Manager_Timeout_manager_id = 1;		//## ignore 
    
    
    //## statechart_method 
    public RiJThread getThread() {
        return reactive.getThread();
    }
    
    //## statechart_method 
    public void schedTimeout(long delay, long tmID, RiJStateReactive reactive) {
        getThread().schedTimeout(delay, tmID, reactive);
    }
    
    //## statechart_method 
    public void unschedTimeout(long tmID, RiJStateReactive reactive) {
        getThread().unschedTimeout(tmID, reactive);
    }
    
    //## statechart_method 
    public boolean isIn(int state) {
        return reactive.isIn(state);
    }
    
    //## statechart_method 
    public boolean isCompleted(int state) {
        return reactive.isCompleted(state);
    }
    
    //## statechart_method 
    public RiJEventConsumer getEventConsumer() {
        return (RiJEventConsumer)reactive;
    }
    
    //## statechart_method 
    public void gen(RiJEvent event) {
        reactive._gen(event);
    }
    
    //## statechart_method 
    public void queueEvent(RiJEvent event) {
        reactive.queueEvent(event);
    }
    
    //## statechart_method 
    public int takeEvent(RiJEvent event) {
        return reactive.takeEvent(event);
    }
    
    // Constructors
    
    //## operation Manager() 
    public  Manager(RiJThread p_thread) {
        reactive = new Reactive(p_thread);
        //#[ operation Manager() 
        // the creation of the output files doesn't compile without try & catch
        try{
        	outputFile = new FileOutputStream("dis_component_log.csv");
        	outputFileP = new PrintStream(outputFile);
          	
          	cmdFile = new FileOutputStream("cmd_log.txt");
          	cmdFileP = new PrintStream(cmdFile); 
        	cmdFileP.println("Starting simulation:");
        }
        catch (Exception e){
         	System.err.println ("Error opening file");
        }
        
        /**
         * creating objects
         */
        
        // the map that contains (name, number) pairs
        neuron_names = new HashMap();  
        
        // the map that contains (name, number) pairs
        muscle_names = new HashMap();
        
        // the map that contains neuronal (param, value) pairs
        params_map = new HashMap();  
        
        // the map that contains muscle (param, value) pairs
        mus_params_map = new HashMap();  
        
        interNeuronsList = new ArrayList<String>();
        motorNeuronsList = new ArrayList<String>();
        sensoryNeuronsList = new ArrayList<String>();
        musclesList = new ArrayList<String>();
        
        // now fill in the names HashMap - this is needed here because it defines N_of_neurons according to the input csv file
        cmdFileP.println("Creating (number,name) pairs:");
        createNamesDict();     
        
        // now fill in the params HashMap
        cmdFileP.println("Creating neurons (param,value) pairs:");
        readParamsFile();
        
        cmdFileP.println("Creating muscles (param,value) pairs:");
        read_MUS_params();
        
        // the matrices of the input data - read from the CSV files in readInputFiles()
        distMatrix = new int[N_of_neurons][N_of_neurons];
        EJweightsMatrix = new double[N_of_neurons][N_of_neurons];
        CHweightsMatrix = new double[N_of_neurons][N_of_neurons];
        NMJDistMatrix = new int[N_of_neurons][N_of_muscles];
        NMJweightsMatrix = new double[N_of_neurons][N_of_muscles];
        
        // creates arrays of neurons of different lengths
        interNeurons = new Gen_IN[interNeuronsList.size()];
        sensoryNeurons = new Gen_SN[sensoryNeuronsList.size()];
        motorNeurons = new Gen_MN[motorNeuronsList.size()];
        bodyMuscles = new MUS[musclesList.size()];
        
        // This is incredible ugly - it happens because the csv file saves everything, even ints, as 300.0 (for example),
        // so we take only what is before the dot and parse it to an int.
        setSimRuntime(Integer.parseInt(params_map.get("simRuntime").toString().split("\\.")[0]));
        
        // Read the propogation time coeffiecient:
        setPropCoeff(Double.parseDouble(params_map.get("propCoeff").toString()));
        
        // read the csv files - chemical & electrical weights + distances, NMJ weights and distances
        readInputFiles();        
        
        /**
         * after we created all the object, we move to insert the data into them
         */
         
        String neuron_name;  
        String muscle_name;
        int mn_count = 0;
        int sn_count = 0;
        int in_count = 0;
        int nmj_count = 0;
        
        int defaultActivation = Integer.parseInt(params_map.get("defaultActivation").toString().split("\\.")[0]);
        double decayTime = Double.parseDouble(params_map.get("decayTime").toString());                
        int ejSynDelay = Integer.parseInt(params_map.get("EJSynDelay").toString().split("\\.")[0]); 
        int chSynDelay = Integer.parseInt(params_map.get("CHSynDelay").toString().split("\\.")[0]);  
        int nmjSynDelay = Integer.parseInt(params_map.get("NMJSynDelay").toString().split("\\.")[0]);  
        double ejCoeff = Double.valueOf(params_map.get("EJCoeff").toString());
        double chemCoeff = Double.valueOf(params_map.get("CHCoeff").toString());
        double nmjCoeff = Double.valueOf(params_map.get("NMJCoeff").toString());
        double LeakyCoeff = Double.parseDouble(params_map.get("LeakyCoeff").toString()); 
        double SignalAttenuation = Double.parseDouble(params_map.get("SignalAttenuation").toString());
        double delta_t = Double.parseDouble(params_map.get("delta_t").toString());  
        int decaySSteps = (int) Math.round(decayTime/delta_t); 
        System.out.println(decayTime + " " + delta_t + " " + decaySSteps);
        
        for(int i = 0; i<N_of_neurons; i++){
        	neuron_name = neuron_names.get(i+1).toString();
         	
         	if (motorNeuronsList.contains(neuron_name)) {
         		motorNeurons[mn_count] = new Gen_MN(p_thread);
         		motorNeurons[mn_count].setItsManager(this);
         		motorNeurons[mn_count].EJsynWeights = new double[N_of_neurons];
        		motorNeurons[mn_count].CHsynWeights = new double[N_of_neurons];
        		motorNeurons[mn_count].propTimes = new int[N_of_neurons];
        		motorNeurons[mn_count].NMJsynWeights = new double[N_of_muscles];
        		motorNeurons[mn_count].NMJPropTimes = new int[N_of_muscles];
         		motorNeurons[mn_count].setNeuronNumber(i+1);
         		motorNeurons[mn_count].setNeuronName(neuron_name);
         		motorNeurons[mn_count].setNeuronType("MN");
         		motorNeurons[mn_count].setActivation(defaultActivation);
         		motorNeurons[mn_count].setDecaySSteps(decaySSteps); 
         		motorNeurons[mn_count].setEJSynDelay(ejSynDelay);
         		motorNeurons[mn_count].setCHSynDelay(chSynDelay); 
         		motorNeurons[mn_count].setEJcoeff(ejCoeff);
         		motorNeurons[mn_count].setCHcoeff(chemCoeff); 
         		motorNeurons[mn_count].setLeakyCoeff(LeakyCoeff);
         		motorNeurons[mn_count].setSignalAttenuation(SignalAttenuation);   
         		for(int j=0; j<N_of_neurons; j++){
         			motorNeurons[mn_count].setEJsynWeights(j, EJweightsMatrix[i][j]);
         			motorNeurons[mn_count].setCHsynWeights(j, CHweightsMatrix[i][j]);
         			motorNeurons[mn_count].setPropTimes(j, distMatrix[i][j]);
         		} 
         		for(int j=0; j<N_of_muscles; j++){ 
         			motorNeurons[mn_count].setNMJsynWeights(j,NMJweightsMatrix[i][j]);
         			motorNeurons[mn_count].setNMJPropTimes(j, NMJDistMatrix[i][j]);
         		}
         		mn_count++;
         	}
         	if (sensoryNeuronsList.contains(neuron_name)) {
         		sensoryNeurons[sn_count] = new Gen_SN(p_thread);
         		sensoryNeurons[sn_count].setItsManager(this);
         		sensoryNeurons[sn_count].EJsynWeights = new double[N_of_neurons];
        		sensoryNeurons[sn_count].CHsynWeights = new double[N_of_neurons];
        		sensoryNeurons[sn_count].propTimes = new int[N_of_neurons];
         		sensoryNeurons[sn_count].setNeuronNumber(i+1);
         		sensoryNeurons[sn_count].setNeuronName(neuron_name);
         		sensoryNeurons[sn_count].setNeuronType("SN");
         		sensoryNeurons[sn_count].setActivation(defaultActivation);
         		sensoryNeurons[sn_count].setDecaySSteps(decaySSteps); 
         		sensoryNeurons[sn_count].setEJSynDelay(ejSynDelay);
         		sensoryNeurons[sn_count].setCHSynDelay(chSynDelay); 
         		sensoryNeurons[sn_count].setEJcoeff(ejCoeff);
         		sensoryNeurons[sn_count].setCHcoeff(chemCoeff);
         		sensoryNeurons[sn_count].setLeakyCoeff(LeakyCoeff);
         		sensoryNeurons[sn_count].setSignalAttenuation(SignalAttenuation);  
         		for(int j=0; j<N_of_neurons; j++){
         			sensoryNeurons[sn_count].setEJsynWeights(j, EJweightsMatrix[i][j]);
         			sensoryNeurons[sn_count].setCHsynWeights(j, CHweightsMatrix[i][j]);
         			sensoryNeurons[sn_count].setPropTimes(j, distMatrix[i][j]);
         		} 
         		sn_count++;
         	}
         	if (interNeuronsList.contains(neuron_name)) {
         		interNeurons[in_count] = new Gen_IN(p_thread);
         		interNeurons[in_count].setItsManager(this);
         		interNeurons[in_count].EJsynWeights = new double[N_of_neurons];
        		interNeurons[in_count].CHsynWeights = new double[N_of_neurons];
        		interNeurons[in_count].propTimes = new int[N_of_neurons];
         		interNeurons[in_count].setNeuronNumber(i+1);
         		interNeurons[in_count].setNeuronName(neuron_name);
         		interNeurons[in_count].setNeuronType("IN");
         		interNeurons[in_count].setActivation(defaultActivation);
         		interNeurons[in_count].setDecaySSteps(decaySSteps); 
         		interNeurons[in_count].setEJSynDelay(ejSynDelay);
         		interNeurons[in_count].setCHSynDelay(chSynDelay); 
         		interNeurons[in_count].setEJcoeff(ejCoeff);
         		interNeurons[in_count].setLeakyCoeff(LeakyCoeff);
         		interNeurons[in_count].setSignalAttenuation(SignalAttenuation);      
         		for(int j=0; j<N_of_neurons; j++){
         			interNeurons[in_count].setEJsynWeights(j, EJweightsMatrix[i][j]);
         			interNeurons[in_count].setCHsynWeights(j, CHweightsMatrix[i][j]);
         			interNeurons[in_count].setPropTimes(j, distMatrix[i][j]);
         		} 
         		in_count++;	
         	}
        
        }
        
        for(int i = 0; i<N_of_muscles; i++){
        	muscle_name = muscle_names.get(i+1).toString();
         	
         	if (musclesList.contains(muscle_name)) {
         		bodyMuscles[nmj_count] = new MUS(p_thread);
         		bodyMuscles[nmj_count].setItsManager(this);
        
        		bodyMuscles[nmj_count].NMJWeights = new double[N_of_neurons];
        		bodyMuscles[nmj_count].NMJPropTimes = new int[N_of_neurons];
         		bodyMuscles[nmj_count].setMuscleNumber(i+1);
         		bodyMuscles[nmj_count].setMuscleName(muscle_name);
         		bodyMuscles[nmj_count].setMuscleType("BS");
         		bodyMuscles[nmj_count].setActivation(defaultActivation);
                bodyMuscles[nmj_count].delta_t = delta_t;
        		bodyMuscles[nmj_count].setCa(0);
        		bodyMuscles[nmj_count].setV(-0.070);
        		bodyMuscles[nmj_count].setI_mem(0);
        		bodyMuscles[nmj_count].setNMJCoeff(nmjCoeff);
        		bodyMuscles[nmj_count].setCmem(Double.valueOf(mus_params_map.get("Cmem").toString()));
        		bodyMuscles[nmj_count].setGKS(Double.valueOf(mus_params_map.get("gKS").toString()));
        		bodyMuscles[nmj_count].setGKF(Double.valueOf(mus_params_map.get("gKF").toString()));
        		bodyMuscles[nmj_count].setGCa(Double.valueOf(mus_params_map.get("gCa").toString()));
        		bodyMuscles[nmj_count].setGL(Double.valueOf(mus_params_map.get("gL").toString()));
        		bodyMuscles[nmj_count].setVKS(Double.valueOf(mus_params_map.get("VKS").toString()));
        		bodyMuscles[nmj_count].setVKF(Double.valueOf(mus_params_map.get("VKF").toString()));    
        		bodyMuscles[nmj_count].setVCa(Double.valueOf(mus_params_map.get("Vca").toString()));
        		bodyMuscles[nmj_count].setVL(Double.valueOf(mus_params_map.get("VL").toString()));
        		bodyMuscles[nmj_count].setVhalf_n(Double.valueOf(mus_params_map.get("Vhalf_n").toString()));
        		bodyMuscles[nmj_count].setVhalf_p(Double.valueOf(mus_params_map.get("Vhalf_p").toString()));
        		bodyMuscles[nmj_count].setVhalf_q(Double.valueOf(mus_params_map.get("Vhalf_q").toString()));
        		bodyMuscles[nmj_count].setVhalf_e(Double.valueOf(mus_params_map.get("Vhalf_e").toString()));
        		bodyMuscles[nmj_count].setVhalf_f(Double.valueOf(mus_params_map.get("Vhalf_f").toString()));
        		bodyMuscles[nmj_count].setCahalf_h(Double.valueOf(mus_params_map.get("Cahalf_h").toString()));
        		bodyMuscles[nmj_count].setK_n(Double.valueOf(mus_params_map.get("k_n").toString()));
        		bodyMuscles[nmj_count].setK_p(Double.valueOf(mus_params_map.get("k_p").toString()));
        		bodyMuscles[nmj_count].setK_q(Double.valueOf(mus_params_map.get("k_q").toString()));
        		bodyMuscles[nmj_count].setK_e(Double.valueOf(mus_params_map.get("k_e").toString()));
        		bodyMuscles[nmj_count].setK_f(Double.valueOf(mus_params_map.get("k_f").toString()));
        		bodyMuscles[nmj_count].setK_h(Double.valueOf(mus_params_map.get("k_h").toString()));
        		bodyMuscles[nmj_count].setT_n(Double.valueOf(mus_params_map.get("T_n").toString()));
        		bodyMuscles[nmj_count].setT_p(Double.valueOf(mus_params_map.get("T_p").toString()));
        		bodyMuscles[nmj_count].setT_q(Double.valueOf(mus_params_map.get("T_q").toString()));
        		bodyMuscles[nmj_count].setT_e(Double.valueOf(mus_params_map.get("T_e").toString()));
        		bodyMuscles[nmj_count].setT_f(Double.valueOf(mus_params_map.get("T_f").toString()));
        		bodyMuscles[nmj_count].setT_Ca(Double.valueOf(mus_params_map.get("T_Ca").toString()));
        		bodyMuscles[nmj_count].setAlphaCa(Double.valueOf(mus_params_map.get("alphaCa").toString()));
        		bodyMuscles[nmj_count].setVmin(Double.valueOf(mus_params_map.get("Vmin").toString()));
        		bodyMuscles[nmj_count].setVmax(Double.valueOf(mus_params_map.get("Vmax").toString()));
        
         		for(int j=0; j<N_of_neurons; j++){
         			bodyMuscles[nmj_count].setNMJWeights(j, NMJweightsMatrix[j][i]);
         			bodyMuscles[nmj_count].setNMJPropTimes(j, NMJDistMatrix[j][i]);
         		} 
        		
         		nmj_count++;
         	}
        }   
           
        // link different neurons - sensory:
        for (int i = 0; i<sensoryNeurons.length; i++) {
        	for (int j = 0; j<sensoryNeurons.length; j++) {
        		if ((EJweightsMatrix[sensoryNeurons[i].getNeuronNumber()-1][sensoryNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[sensoryNeurons[i].getNeuronNumber()-1][sensoryNeurons[j].getNeuronNumber()-1] != 0)) {
        			sensoryNeurons[i].addItsGen_SN(sensoryNeurons[j]);
        		}
        	}
        	for (int j = 0; j<interNeurons.length; j++) {
        		if ((EJweightsMatrix[sensoryNeurons[i].getNeuronNumber()-1][interNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[sensoryNeurons[i].getNeuronNumber()-1][interNeurons[j].getNeuronNumber()-1] != 0)) {
        			sensoryNeurons[i].addItsGen_IN(interNeurons[j]);
        		}
        	}
        	for (int j = 0; j<motorNeurons.length; j++) {
        		if ((EJweightsMatrix[sensoryNeurons[i].getNeuronNumber()-1][motorNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[sensoryNeurons[i].getNeuronNumber()-1][motorNeurons[j].getNeuronNumber()-1] != 0)) {
        			sensoryNeurons[i].addItsGen_MN(motorNeurons[j]);
        		}
        	}
        } 
        
        // link different neurons - motor:
        for (int i = 0; i<motorNeurons.length; i++) {
        	for (int j = 0; j<sensoryNeurons.length; j++) {
        		if ((EJweightsMatrix[motorNeurons[i].getNeuronNumber()-1][sensoryNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[motorNeurons[i].getNeuronNumber()-1][sensoryNeurons[j].getNeuronNumber()-1] != 0)) {
        			motorNeurons[i].addItsGen_SN(sensoryNeurons[j]);
        		}
        	}
        	for (int j = 0; j<interNeurons.length; j++) {
        		if ((EJweightsMatrix[motorNeurons[i].getNeuronNumber()-1][interNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[motorNeurons[i].getNeuronNumber()-1][interNeurons[j].getNeuronNumber()-1] != 0)) {
        			motorNeurons[i].addItsGen_IN(interNeurons[j]);
        		}
        	}
        	for (int j = 0; j<motorNeurons.length; j++) {
        		if ((EJweightsMatrix[motorNeurons[i].getNeuronNumber()-1][motorNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[motorNeurons[i].getNeuronNumber()-1][motorNeurons[j].getNeuronNumber()-1] != 0)) {
        			motorNeurons[i].addItsGen_MN(motorNeurons[j]);
        		}
        	} 
        } 
        
        // link different neurons - inter:
        for (int i = 0; i<interNeurons.length; i++) {
        	for (int j = 0; j<sensoryNeurons.length; j++) {
        		if ((EJweightsMatrix[interNeurons[i].getNeuronNumber()-1][sensoryNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[interNeurons[i].getNeuronNumber()-1][sensoryNeurons[j].getNeuronNumber()-1] != 0)) {
        			interNeurons[i].addItsGen_SN(sensoryNeurons[j]);
        		}
        	}
        	for (int j = 0; j<interNeurons.length; j++) {
        		if ((EJweightsMatrix[interNeurons[i].getNeuronNumber()-1][interNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[interNeurons[i].getNeuronNumber()-1][interNeurons[j].getNeuronNumber()-1] != 0)) {
        			interNeurons[i].addItsGen_IN(interNeurons[j]);
        		}
        	}
        	for (int j = 0; j<motorNeurons.length; j++) {
        		if ((EJweightsMatrix[interNeurons[i].getNeuronNumber()-1][motorNeurons[j].getNeuronNumber()-1] != 0)
        		 || (CHweightsMatrix[interNeurons[i].getNeuronNumber()-1][motorNeurons[j].getNeuronNumber()-1] != 0)) {
        			interNeurons[i].addItsGen_MN(motorNeurons[j]);
        		}
        	}
        }    
        
        // link different muscles - to neurons:
        for (int i = 0; i<bodyMuscles.length; i++) {
        //	for (int j = 0; j<sensoryNeurons.length; j++) {
        //		if (NMJweightsMatrix[sensoryNeurons[j].getNeuronNumber()-1][bodyMuscles[i].getMuscleNumber()-1] != 0) {
        //			bodyMuscles[i].addItsGen_SN(sensoryNeurons[j]);
        //		}
        //	}
        	for (int j = 0; j<motorNeurons.length; j++) {
        		if (NMJweightsMatrix[motorNeurons[j].getNeuronNumber()-1][bodyMuscles[i].getMuscleNumber()-1] != 0){
        			bodyMuscles[i].addItsGen_MN(motorNeurons[j]);
        		}
        	}
        //	for (int j = 0; j<interNeurons.length; j++) {
        //		if (NMJweightsMatrix[interNeurons[j].getNeuronNumber()-1][bodyMuscles[i].getMuscleNumber()-1] != 0) {
        // 			bodyMuscles[i].addItsGen_IN(interNeurons[j]);
        //		}
        //	}
        }    
        
        startBehavior();
        
        
        //#]
    }
    
    //## operation createCHMatrix() 
    public void createCHMatrix() {
        //#[ operation createCHMatrix() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\CHweights_normed.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            int row = 0;
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		        // use comma as separator
        		String[] weights = line.split(cvsSplitBy);
        		for(int col=0; col<N_of_neurons; col++) {
        			CHweightsMatrix[row][col] = Double.parseDouble(weights[col]);
        		}
        		row++;
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        // save CHweightsMatrix into cmd_log.txt for debugging:
        for(int i=0; i<N_of_neurons; i++) {
        	for(int j=0; j<N_of_neurons; j++) {
        		cmdFileP.print(CHweightsMatrix[i][j]+",");
        	}
        	cmdFileP.println();
        
        }
        cmdFileP.println("CHMatrix complete.");
        //#]
    }
    
    //## operation createDistMatrix() 
    public void createDistMatrix() {
        //#[ operation createDistMatrix() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\distances_normed.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            int row = 0;
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		        // use comma as separator
        		String[] distances = line.split(cvsSplitBy);
        		for(int col=0; col<N_of_neurons; col++) {
        			distMatrix[row][col] = (int) Math.round((getPropCoeff()*Double.parseDouble(distances[col])));
        		}
        		row++;
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        // save distMatrix into cmd_log.txt for debugging:
        for(int i=0; i<N_of_neurons; i++) {
        	for(int j=0; j<N_of_neurons; j++) {
        		cmdFileP.print(distMatrix[i][j]+",");
        	}
        	cmdFileP.println();
        }
        cmdFileP.println("distMatrix complete.");
        
        //#]
    }
    
    //## operation createEJMatrix() 
    public void createEJMatrix() {
        //#[ operation createEJMatrix() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\EJweights_normed.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            int row = 0;
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		        // use comma as separator
        		String[] weights = line.split(cvsSplitBy); 
        		//System.out.println(N_of_neurons + ", " + weights.length);
        		for(int col=0; col<N_of_neurons; col++) {
        			EJweightsMatrix[row][col] = Double.parseDouble(weights[col]);   
        		}
        		row++;
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        // save EJweightsMatrix into cmd_log.txt for debugging:
        for(int i=0; i<N_of_neurons; i++) {
        	for(int j=0; j<N_of_neurons; j++) {
        		cmdFileP.print(EJweightsMatrix[i][j] + ",");
        	}
        	cmdFileP.println();
        }
        cmdFileP.println("EJMatrix complete.");
        //#]
    }
    
    //## operation createNMJDistMatrix() 
    public void createNMJDistMatrix() {
        //#[ operation createNMJDistMatrix() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\NMJ_distances.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            int row = 0;
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		        // use comma as separator
        		String[] distances = line.split(cvsSplitBy);
        		for(int col=0; col<N_of_muscles; col++) {
        			NMJDistMatrix[row][col] = (int) Math.round((getPropCoeff()*Double.parseDouble(distances[col])));
        		}
        		row++;
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        // save NMJDistMatrix into cmd_log.txt for debugging:
        for(int i=0; i<N_of_neurons; i++) {
        	for(int j=0; j<N_of_muscles; j++) {
        		cmdFileP.print(NMJDistMatrix[i][j]+",");
        	}
        	cmdFileP.println();
        }
        cmdFileP.println("NMJDistMatrix complete.");
        //#]
    }
    
    //## operation createNMJMatrix() 
    public void createNMJMatrix() {
        //#[ operation createNMJMatrix() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\NMJweights.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
            int row = 0;
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		        // use comma as separator
        		String[] weights = line.split(cvsSplitBy);
        		for(int col=0; col<N_of_muscles; col++) {
        			NMJweightsMatrix[row][col] = Double.parseDouble(weights[col]);
        		}
        		row++;
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        // save NMJweightsMatrix into cmd_log.txt for debugging:
        for(int i=0; i<N_of_neurons; i++) {
        	for(int j=0; j<N_of_muscles; j++) {
        		cmdFileP.print(NMJweightsMatrix[i][j] + ",");
        	}
        	cmdFileP.println();
        }
        cmdFileP.println("NMJMatrix complete.");
        //#]
    }
    
    //## operation createNamesDict() 
    public void createNamesDict() {
        //#[ operation createNamesDict() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\names.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int neurons_counter = 0;
        int muscles_counter = 0;
        
        try {
        
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		        // use comma as separator
        		String[] names = line.split(cvsSplitBy);
        		
        		// each line in the file looks like: "neuron_name, neuron_number, neuron_type"
        		String name = names[0];
        		int num = Integer.parseInt(names[1]);
        		String type = names[2];
        		
                if ((type.equals("MN")) || (type.equals("SN")) || (type.equals("IN"))) {
                // count one more neuron in the list
                 	neurons_counter++;
                
                // add the (name, number) to the list
                	neuron_names.put(neurons_counter, name);
                	cmdFileP.println("Adding the pair: " + neurons_counter + ":" + name + " (" + type + ")");	
                
                } 
                else if (type.equals("BS")) {
                // count one more neuron in the list
                 	muscles_counter++;
                 	
                // add the (name, number) to the list
                	muscle_names.put(muscles_counter, name);
                	cmdFileP.println("Adding the pair: " + muscles_counter + ":" + name + " (" + type + ")");	
                
                } 
                
                // add the name to the appropriate list
                if (type.equals("MN")) {motorNeuronsList.add(name);}
                else if (type.equals("SN")) {sensoryNeuronsList.add(name); }
                else if (type.equals("IN")) {interNeuronsList.add(name);}
                else if (type.equals("BS")) {musclesList.add(name);}
        		
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        N_of_neurons = neurons_counter;
        N_of_muscles = muscles_counter;
        cmdFileP.println(motorNeuronsList.size() + ", " + sensoryNeuronsList.size() +  ", " + interNeuronsList.size() + ", " + musclesList.size());
        cmdFileP.println("HashMap complete.");
        
        
        //#]
    }
    
    //## operation createOutputFile() 
    public void createOutputFile() {
        //#[ operation createOutputFile() 
        //creating headers for the output CSV file
        outputFileP.print("Time");
        for(int i=0; i<N_of_neurons; i++) {
        	outputFileP.print("," + neuron_names.get(i+1));
        }
        for(int i=0; i<N_of_muscles; i++) {
        	outputFileP.print("," + muscle_names.get(i+1));
        }
        
        outputFileP.println();
        
        //#]
    }
    
    //## operation outputCurrentStatus() 
    public void outputCurrentStatus() {
        //#[ operation outputCurrentStatus() 
        outputFileP.print(getClockTime());
        
        String neuron_name;
        int mn_count = 0;
        int sn_count = 0;
        int in_count = 0;
        
        for(int i = 0; i<N_of_neurons; i++){
        	neuron_name = neuron_names.get(i+1).toString();
        	
         	if (motorNeuronsList.contains(neuron_name)) {    
         		outputFileP.print("," + getMotorNeurons(mn_count).getActivation());
         		mn_count++;
         	}
         	if (sensoryNeuronsList.contains(neuron_name)) {
         		outputFileP.print("," + getSensoryNeurons(sn_count).getActivation());
         		sn_count++;
         	}
         	if (interNeuronsList.contains(neuron_name)) { 
         		outputFileP.print("," + getInterNeurons(in_count).getActivation());
         		in_count++;	
         	}
         }
        for(int i = 0; i<N_of_muscles; i++){   
         	outputFileP.print("," + getBodyMuscles(i).getActivation());
        }
        outputFileP.println();            
        
        
        
        //#]
    }
    
    //## operation readInputFiles() 
    public void readInputFiles() {
        //#[ operation readInputFiles() 
        cmdFileP.println("Creating EJMatrix:");
        createEJMatrix();
        
        cmdFileP.println("Creating CHMatrix:");
        createCHMatrix();
        
        cmdFileP.println("Creating DistMatrix:");
        createDistMatrix();
        
        cmdFileP.println("Creating NMJMatrix:");
        createNMJMatrix();
        
        cmdFileP.println("Creating NMJDistMatrix:");
        createNMJDistMatrix();
        
        cmdFileP.println("Creating headers in outputFile");
        createOutputFile();
        
        
        //#]
    }
    
    //## operation readParamsFile() 
    public void readParamsFile() {
        //#[ operation readParamsFile() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\params.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
        
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		// use comma as separator
        		String[] params = line.split(cvsSplitBy);
        		
        		// each line in the file looks like: "param_name,param_value"
        		String param = params[0];
        		String value = params[1];
        		
                // add the (name, number) to the list
                params_map.put(param, value);
                cmdFileP.println("Adding the pair: " + param + ":" + value);	
        		
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        
        
        //#]
    }
    
    //## operation read_MUS_params() 
    public void read_MUS_params() {
        //#[ operation read_MUS_params() 
        String csvFile = "c:\\Users\\hlapid\\Desktop\\CSVFiles\\muscle_params.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try {
        
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {
        		// use comma as separator
        		String[] params = line.split(cvsSplitBy);
        		
        		// each line in the file looks like: "param_name,param_value"
        		String param = params[0];
        		String value = params[1];
        		
                // add the (name, number) to the list
                mus_params_map.put(param, value);
                cmdFileP.println("Adding the muscle pair: " + param + ":" + value);	
        		
        	}
        
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        } finally {
        	if (br != null) {
        		try {
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        
        
        //#]
    }
    
    //## operation tick() 
    public void tick() {
        //#[ operation tick() 
        setClockTime(getClockTime() + 1);
        
        for (int i = 0; i < motorNeurons.length; i++){motorNeurons[i].gen(new evTick());}
        for (int i = 0; i < sensoryNeurons.length; i++){sensoryNeurons[i].gen(new evTick());}
        for (int i = 0; i < interNeurons.length; i++){interNeurons[i].gen(new evTick());}
        for (int i = 0; i < bodyMuscles.length; i++){bodyMuscles[i].gen(new evTick());}
        //#]
    }
    
    //## auto_generated 
    public double getCHweightsMatrix(int i2, int i1) {
        return CHweightsMatrix[i2][i1];
    }
    
    //## auto_generated 
    public void setCHweightsMatrix(int i2, int i1, double p_CHweightsMatrix) {
        CHweightsMatrix[i2][i1] = p_CHweightsMatrix;
    }
    
    //## auto_generated 
    public double getEJweightsMatrix(int i2, int i1) {
        return EJweightsMatrix[i2][i1];
    }
    
    //## auto_generated 
    public void setEJweightsMatrix(int i2, int i1, double p_EJweightsMatrix) {
        EJweightsMatrix[i2][i1] = p_EJweightsMatrix;
    }
    
    //## auto_generated 
    public int getNMJDistMatrix(int i2, int i1) {
        return NMJDistMatrix[i2][i1];
    }
    
    //## auto_generated 
    public void setNMJDistMatrix(int i2, int i1, int p_NMJDistMatrix) {
        NMJDistMatrix[i2][i1] = p_NMJDistMatrix;
    }
    
    //## auto_generated 
    public double getNMJweightsMatrix(int i2, int i1) {
        return NMJweightsMatrix[i2][i1];
    }
    
    //## auto_generated 
    public void setNMJweightsMatrix(int i2, int i1, double p_NMJweightsMatrix) {
        NMJweightsMatrix[i2][i1] = p_NMJweightsMatrix;
    }
    
    //## auto_generated 
    public int getN_of_muscles() {
        return N_of_muscles;
    }
    
    //## auto_generated 
    public void setN_of_muscles(int p_N_of_muscles) {
        N_of_muscles = p_N_of_muscles;
    }
    
    //## auto_generated 
    public int getN_of_neurons() {
        return N_of_neurons;
    }
    
    //## auto_generated 
    public void setN_of_neurons(int p_N_of_neurons) {
        N_of_neurons = p_N_of_neurons;
    }
    
    //## auto_generated 
    public MUS getBodyMuscles(int i1) {
        return bodyMuscles[i1];
    }
    
    //## auto_generated 
    public void setBodyMuscles(int i1, MUS p_bodyMuscles) {
        bodyMuscles[i1] = p_bodyMuscles;
    }
    
    //## auto_generated 
    public int getClockTime() {
        return clockTime;
    }
    
    //## auto_generated 
    public void setClockTime(int p_clockTime) {
        clockTime = p_clockTime;
    }
    
    //## auto_generated 
    public FileOutputStream getCmdFile() {
        return cmdFile;
    }
    
    //## auto_generated 
    public void setCmdFile(FileOutputStream p_cmdFile) {
        cmdFile = p_cmdFile;
    }
    
    //## auto_generated 
    public PrintStream getCmdFileP() {
        return cmdFileP;
    }
    
    //## auto_generated 
    public void setCmdFileP(PrintStream p_cmdFileP) {
        cmdFileP = p_cmdFileP;
    }
    
    //## auto_generated 
    public int getDistMatrix(int i2, int i1) {
        return distMatrix[i2][i1];
    }
    
    //## auto_generated 
    public void setDistMatrix(int i2, int i1, int p_distMatrix) {
        distMatrix[i2][i1] = p_distMatrix;
    }
    
    //## auto_generated 
    public boolean getHitObs() {
        return hitObs;
    }
    
    //## auto_generated 
    public void setHitObs(boolean p_hitObs) {
        hitObs = p_hitObs;
    }
    
    //## auto_generated 
    public Gen_IN getInterNeurons(int i1) {
        return interNeurons[i1];
    }
    
    //## auto_generated 
    public void setInterNeurons(int i1, Gen_IN p_interNeurons) {
        interNeurons[i1] = p_interNeurons;
    }
    
    //## auto_generated 
    public ArrayList<String> getInterNeuronsList() {
        return interNeuronsList;
    }
    
    //## auto_generated 
    public void setInterNeuronsList(ArrayList<String> p_interNeuronsList) {
        interNeuronsList = p_interNeuronsList;
    }
    
    //## auto_generated 
    public Gen_MN getMotorNeurons(int i1) {
        return motorNeurons[i1];
    }
    
    //## auto_generated 
    public void setMotorNeurons(int i1, Gen_MN p_motorNeurons) {
        motorNeurons[i1] = p_motorNeurons;
    }
    
    //## auto_generated 
    public ArrayList<String> getMotorNeuronsList() {
        return motorNeuronsList;
    }
    
    //## auto_generated 
    public void setMotorNeuronsList(ArrayList<String> p_motorNeuronsList) {
        motorNeuronsList = p_motorNeuronsList;
    }
    
    //## auto_generated 
    public Map getMus_params_map() {
        return mus_params_map;
    }
    
    //## auto_generated 
    public void setMus_params_map(Map p_mus_params_map) {
        mus_params_map = p_mus_params_map;
    }
    
    //## auto_generated 
    public Map getMuscle_names() {
        return muscle_names;
    }
    
    //## auto_generated 
    public void setMuscle_names(Map p_muscle_names) {
        muscle_names = p_muscle_names;
    }
    
    //## auto_generated 
    public ArrayList<String> getMusclesList() {
        return musclesList;
    }
    
    //## auto_generated 
    public void setMusclesList(ArrayList<String> p_musclesList) {
        musclesList = p_musclesList;
    }
    
    //## auto_generated 
    public Map getNeuron_names() {
        return neuron_names;
    }
    
    //## auto_generated 
    public void setNeuron_names(Map p_neuron_names) {
        neuron_names = p_neuron_names;
    }
    
    //## auto_generated 
    public FileOutputStream getOutputFile() {
        return outputFile;
    }
    
    //## auto_generated 
    public void setOutputFile(FileOutputStream p_outputFile) {
        outputFile = p_outputFile;
    }
    
    //## auto_generated 
    public PrintStream getOutputFileP() {
        return outputFileP;
    }
    
    //## auto_generated 
    public void setOutputFileP(PrintStream p_outputFileP) {
        outputFileP = p_outputFileP;
    }
    
    //## auto_generated 
    public Map getParams_map() {
        return params_map;
    }
    
    //## auto_generated 
    public void setParams_map(Map p_params_map) {
        params_map = p_params_map;
    }
    
    //## auto_generated 
    public double getPropCoeff() {
        return propCoeff;
    }
    
    //## auto_generated 
    public void setPropCoeff(double p_propCoeff) {
        propCoeff = p_propCoeff;
    }
    
    //## auto_generated 
    public Gen_SN getSensoryNeurons(int i1) {
        return sensoryNeurons[i1];
    }
    
    //## auto_generated 
    public void setSensoryNeurons(int i1, Gen_SN p_sensoryNeurons) {
        sensoryNeurons[i1] = p_sensoryNeurons;
    }
    
    //## auto_generated 
    public ArrayList<String> getSensoryNeuronsList() {
        return sensoryNeuronsList;
    }
    
    //## auto_generated 
    public void setSensoryNeuronsList(ArrayList<String> p_sensoryNeuronsList) {
        sensoryNeuronsList = p_sensoryNeuronsList;
    }
    
    //## auto_generated 
    public int getSimRuntime() {
        return simRuntime;
    }
    
    //## auto_generated 
    public void setSimRuntime(int p_simRuntime) {
        simRuntime = p_simRuntime;
    }
    
    //## auto_generated 
    public ListIterator<Gen_IN> getItsGen_IN() {
        ListIterator<Gen_IN> iter = itsGen_IN.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsGen_IN(Gen_IN p_Gen_IN) {
        itsGen_IN.add(0, p_Gen_IN);
    }
    
    //## auto_generated 
    public void addItsGen_IN(Gen_IN p_Gen_IN) {
        if(p_Gen_IN != null)
            {
                p_Gen_IN._setItsManager(this);
            }
        _addItsGen_IN(p_Gen_IN);
    }
    
    //## auto_generated 
    public void _removeItsGen_IN(Gen_IN p_Gen_IN) {
        itsGen_IN.remove(p_Gen_IN);
    }
    
    //## auto_generated 
    public void removeItsGen_IN(Gen_IN p_Gen_IN) {
        if(p_Gen_IN != null)
            {
                p_Gen_IN.__setItsManager(null);
            }
        _removeItsGen_IN(p_Gen_IN);
    }
    
    //## auto_generated 
    public void _clearItsGen_IN() {
        itsGen_IN.clear();
    }
    
    //## auto_generated 
    public void clearItsGen_IN() {
        ListIterator<Gen_IN> iter = itsGen_IN.listIterator();
        while (iter.hasNext()){
            (itsGen_IN.get(iter.nextIndex()))._clearItsManager();
            iter.next();
        }
        _clearItsGen_IN();
    }
    
    //## auto_generated 
    public ListIterator<Gen_MN> getItsGen_MN() {
        ListIterator<Gen_MN> iter = itsGen_MN.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsGen_MN(Gen_MN p_Gen_MN) {
        itsGen_MN.add(0, p_Gen_MN);
    }
    
    //## auto_generated 
    public void addItsGen_MN(Gen_MN p_Gen_MN) {
        if(p_Gen_MN != null)
            {
                p_Gen_MN._setItsManager(this);
            }
        _addItsGen_MN(p_Gen_MN);
    }
    
    //## auto_generated 
    public void _removeItsGen_MN(Gen_MN p_Gen_MN) {
        itsGen_MN.remove(p_Gen_MN);
    }
    
    //## auto_generated 
    public void removeItsGen_MN(Gen_MN p_Gen_MN) {
        if(p_Gen_MN != null)
            {
                p_Gen_MN.__setItsManager(null);
            }
        _removeItsGen_MN(p_Gen_MN);
    }
    
    //## auto_generated 
    public void _clearItsGen_MN() {
        itsGen_MN.clear();
    }
    
    //## auto_generated 
    public void clearItsGen_MN() {
        ListIterator<Gen_MN> iter = itsGen_MN.listIterator();
        while (iter.hasNext()){
            (itsGen_MN.get(iter.nextIndex()))._clearItsManager();
            iter.next();
        }
        _clearItsGen_MN();
    }
    
    //## auto_generated 
    public ListIterator<Gen_MUS> getItsGen_MUS() {
        ListIterator<Gen_MUS> iter = itsGen_MUS.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsGen_MUS(Gen_MUS p_Gen_MUS) {
        itsGen_MUS.add(0, p_Gen_MUS);
    }
    
    //## auto_generated 
    public void addItsGen_MUS(Gen_MUS p_Gen_MUS) {
        if(p_Gen_MUS != null)
            {
                p_Gen_MUS._setItsManager(this);
            }
        _addItsGen_MUS(p_Gen_MUS);
    }
    
    //## auto_generated 
    public void _removeItsGen_MUS(Gen_MUS p_Gen_MUS) {
        itsGen_MUS.remove(p_Gen_MUS);
    }
    
    //## auto_generated 
    public void removeItsGen_MUS(Gen_MUS p_Gen_MUS) {
        if(p_Gen_MUS != null)
            {
                p_Gen_MUS.__setItsManager(null);
            }
        _removeItsGen_MUS(p_Gen_MUS);
    }
    
    //## auto_generated 
    public void _clearItsGen_MUS() {
        itsGen_MUS.clear();
    }
    
    //## auto_generated 
    public void clearItsGen_MUS() {
        ListIterator<Gen_MUS> iter = itsGen_MUS.listIterator();
        while (iter.hasNext()){
            (itsGen_MUS.get(iter.nextIndex()))._clearItsManager();
            iter.next();
        }
        _clearItsGen_MUS();
    }
    
    //## auto_generated 
    public ListIterator<Gen_Neuron> getItsGen_Neuron() {
        ListIterator<Gen_Neuron> iter = itsGen_Neuron.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsGen_Neuron(Gen_Neuron p_Gen_Neuron) {
        itsGen_Neuron.add(0, p_Gen_Neuron);
    }
    
    //## auto_generated 
    public void addItsGen_Neuron(Gen_Neuron p_Gen_Neuron) {
        if(p_Gen_Neuron != null)
            {
                p_Gen_Neuron._setItsManager(this);
            }
        _addItsGen_Neuron(p_Gen_Neuron);
    }
    
    //## auto_generated 
    public void _removeItsGen_Neuron(Gen_Neuron p_Gen_Neuron) {
        itsGen_Neuron.remove(p_Gen_Neuron);
    }
    
    //## auto_generated 
    public void removeItsGen_Neuron(Gen_Neuron p_Gen_Neuron) {
        if(p_Gen_Neuron != null)
            {
                p_Gen_Neuron.__setItsManager(null);
            }
        _removeItsGen_Neuron(p_Gen_Neuron);
    }
    
    //## auto_generated 
    public void _clearItsGen_Neuron() {
        itsGen_Neuron.clear();
    }
    
    //## auto_generated 
    public void clearItsGen_Neuron() {
        ListIterator<Gen_Neuron> iter = itsGen_Neuron.listIterator();
        while (iter.hasNext()){
            (itsGen_Neuron.get(iter.nextIndex()))._clearItsManager();
            iter.next();
        }
        _clearItsGen_Neuron();
    }
    
    //## auto_generated 
    public ListIterator<Gen_SN> getItsGen_SN() {
        ListIterator<Gen_SN> iter = itsGen_SN.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsGen_SN(Gen_SN p_Gen_SN) {
        itsGen_SN.add(0, p_Gen_SN);
    }
    
    //## auto_generated 
    public void addItsGen_SN(Gen_SN p_Gen_SN) {
        if(p_Gen_SN != null)
            {
                p_Gen_SN._setItsManager(this);
            }
        _addItsGen_SN(p_Gen_SN);
    }
    
    //## auto_generated 
    public void _removeItsGen_SN(Gen_SN p_Gen_SN) {
        itsGen_SN.remove(p_Gen_SN);
    }
    
    //## auto_generated 
    public void removeItsGen_SN(Gen_SN p_Gen_SN) {
        if(p_Gen_SN != null)
            {
                p_Gen_SN.__setItsManager(null);
            }
        _removeItsGen_SN(p_Gen_SN);
    }
    
    //## auto_generated 
    public void _clearItsGen_SN() {
        itsGen_SN.clear();
    }
    
    //## auto_generated 
    public void clearItsGen_SN() {
        ListIterator<Gen_SN> iter = itsGen_SN.listIterator();
        while (iter.hasNext()){
            (itsGen_SN.get(iter.nextIndex()))._clearItsManager();
            iter.next();
        }
        _clearItsGen_SN();
    }
    
    //## auto_generated 
    public ListIterator<MUS> getItsMUS() {
        ListIterator<MUS> iter = itsMUS.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsMUS(MUS p_MUS) {
        itsMUS.add(0, p_MUS);
    }
    
    //## auto_generated 
    public void addItsMUS(MUS p_MUS) {
        if(p_MUS != null)
            {
                p_MUS._setItsManager(this);
            }
        _addItsMUS(p_MUS);
    }
    
    //## auto_generated 
    public void _removeItsMUS(MUS p_MUS) {
        itsMUS.remove(p_MUS);
    }
    
    //## auto_generated 
    public void removeItsMUS(MUS p_MUS) {
        if(p_MUS != null)
            {
                p_MUS.__setItsManager(null);
            }
        _removeItsMUS(p_MUS);
    }
    
    //## auto_generated 
    public void _clearItsMUS() {
        itsMUS.clear();
    }
    
    //## auto_generated 
    public void clearItsMUS() {
        ListIterator<MUS> iter = itsMUS.listIterator();
        while (iter.hasNext()){
            (itsMUS.get(iter.nextIndex()))._clearItsManager();
            iter.next();
        }
        _clearItsMUS();
    }
    
    //## auto_generated 
    public boolean startBehavior() {
        boolean done = false;
        done = reactive.startBehavior();
        return done;
    }
    
    //## ignore 
    public class Reactive extends RiJStateReactive {
        
        // Default constructor 
        public Reactive() {
            this(RiJMainThread.instance());
        }
        
        
        // Constructors
        
        public  Reactive(RiJThread p_thread) {
            super(p_thread);
            initStatechart();
        }
        
        //## statechart_method 
        public boolean isIn(int state) {
            if(rootState_subState == state)
                {
                    return true;
                }
            return false;
        }
        
        //## statechart_method 
        public boolean isCompleted(int state) {
            return true;
        }
        
        //## statechart_method 
        public void rootState_entDef() {
            {
                rootState_enter();
                rootStateEntDef();
            }
        }
        
        //## statechart_method 
        public int rootState_dispatchEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            switch (rootState_active) {
                case manager:
                {
                    res = manager_takeEvent(id);
                }
                break;
                case hit_obs:
                {
                    res = hit_obs_takeEvent(id);
                }
                break;
                default:
                    break;
            }
            return res;
        }
        
        //## auto_generated 
        protected void initStatechart() {
            rootState_subState = RiJNonState;
            rootState_active = RiJNonState;
        }
        
        //## statechart_method 
        public int hit_obs_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(RiJEvent.NULL_EVENT_ID))
                {
                    res = hit_obsTakeNull();
                }
            
            return res;
        }
        
        //## statechart_method 
        public void managerExit() {
            itsRiJThread.unschedTimeout(Manager_Timeout_manager_id, this);
            //#[ state ROOT.manager.(Exit) 
            // write activations to log file
            outputCurrentStatus();
            
            // end simulation when we pass simRuntime
            if (getSimRuntime() <= getClockTime())
            {
            	outputFileP.println("End of simulation");
            	System.exit(0);
            	
            }  
            
            System.out.println(getClockTime());
            
            
            //#]
        }
        
        //## statechart_method 
        public void manager_entDef() {
            manager_enter();
        }
        
        //## statechart_method 
        public void hit_obsEnter() {
            //#[ state ROOT.hit_obs.(Entry) 
            hitObs = true;
            sensoryNeurons[0].setActivation(1);
            sensoryNeurons[1].setActivation(1);
            sensoryNeurons[2].setActivation(1);
            
            
            sensoryNeurons[0].gen(new evTrig());
            sensoryNeurons[1].gen(new evTrig());
            sensoryNeurons[2].gen(new evTrig());
            
            
            //#]
        }
        
        //## statechart_method 
        public int manager_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(RiJEvent.NULL_EVENT_ID))
                {
                    res = managerTakeNull();
                }
            else if(event.isTypeOf(RiJEvent.TIMEOUT_EVENT_ID))
                {
                    res = managerTakeRiJTimeout();
                }
            
            return res;
        }
        
        //## statechart_method 
        public void hit_obs_entDef() {
            hit_obs_enter();
        }
        
        //## statechart_method 
        public int managerTakeNull() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //## transition 1 
            if((getClockTime() == 150) && !hitObs)
                {
                    manager_exit();
                    //#[ transition 1 
                    // change the guard to !hitObs when we want to try with external event
                    //#]
                    hit_obs_entDef();
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                }
            return res;
        }
        
        //## statechart_method 
        public int managerTakeRiJTimeout() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.getTimeoutId() == Manager_Timeout_manager_id)
                {
                    manager_exit();
                    //#[ transition 0 
                    // the guard is to keep the simulation from running forever
                    
                    /*
                    * advance the clock
                    */
                    
                    tick();                                         
                    
                    // update electrical coupling deltas
                    for (int i = 0; i<motorNeurons.length; i++) {motorNeurons[i].updateDeltaActivation();}
                    for (int i = 0; i<sensoryNeurons.length; i++) {sensoryNeurons[i].updateDeltaActivation();}
                    for (int i = 0; i<interNeurons.length; i++) {interNeurons[i].updateDeltaActivation();}
                    for (int i = 0; i<bodyMuscles.length; i++) {bodyMuscles[i].updateDeltaActivation();}
                    //#]
                    manager_entDef();
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                }
            return res;
        }
        
        //## statechart_method 
        public void hit_obs_exit() {
            popNullConfig();
            hit_obsExit();
        }
        
        //## statechart_method 
        public void managerEnter() {
            //#[ state ROOT.manager.(Entry) 
              
            //#]
            itsRiJThread.schedTimeout(1, Manager_Timeout_manager_id, this, null);
        }
        
        //## statechart_method 
        public void hit_obs_enter() {
            pushNullConfig();
            rootState_subState = hit_obs;
            rootState_active = hit_obs;
            hit_obsEnter();
        }
        
        //## statechart_method 
        public int rootState_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void manager_exit() {
            popNullConfig();
            managerExit();
        }
        
        //## statechart_method 
        public void rootState_enter() {
            rootStateEnter();
        }
        
        //## statechart_method 
        public void rootStateEnter() {
        }
        
        //## statechart_method 
        public void hit_obsExit() {
        }
        
        //## statechart_method 
        public void rootStateEntDef() {
            //#[ transition 3 
            //triggering AVBL and AVBR at the beggining of the simulation
            interNeurons[4].setActivation(1);
            interNeurons[4].gen(new evTrig());
            
            interNeurons[5].setActivation(1);
            interNeurons[5].gen(new evTrig());
            //#]
            manager_entDef();
        }
        
        //## statechart_method 
        public int hit_obsTakeNull() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //## transition 2 
            if(true)
                {
                    hit_obs_exit();
                    //#[ transition 2 
                    tick();
                    //#]
                    manager_entDef();
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                }
            return res;
        }
        
        //## statechart_method 
        public void rootStateExit() {
        }
        
        //## statechart_method 
        public void manager_enter() {
            pushNullConfig();
            rootState_subState = manager;
            rootState_active = manager;
            managerEnter();
        }
        
    }
}
/*********************************************************************
	File Path	: DefaultComponent/DefaultConfig/Default/Manager.java
*********************************************************************/

