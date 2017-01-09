/*********************************************************************
	Rhapsody	: 7.6.1
	Login		: hlapid
	Component	: DefaultComponent
	Configuration 	: DefaultConfig
	Model Element	: Gen_Neuron
//!	Generated Date	: Wed, 15, Jun 2016 
	File Path	: DefaultComponent/DefaultConfig/Default/Gen_Neuron.java
*********************************************************************/

package Default;

//## dependency util 
import java.util.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.states.*;

//----------------------------------------------------------------------------
// Default/Gen_Neuron.java                                                                  
//----------------------------------------------------------------------------

//## package Default 


//## class Gen_Neuron 
public class Gen_Neuron implements RiJStateConcept {
    
    public Reactive reactive;		//## ignore 
    
    protected int CHSynDelay;		//## attribute CHSynDelay 
    
    protected double CHcoeff;		//## attribute CHcoeff 
    
    protected ArrayList<String> CHsignalArrivalsList;		//## attribute CHsignalArrivalsList 
    
    protected double[] CHsynWeights;		//## attribute CHsynWeights 
    
    protected int EJSynDelay;		//## attribute EJSynDelay 
    
    protected double EJcoeff;		//## attribute EJcoeff 
    
    protected ArrayList<String> EJsignalArrivalsList;		//## attribute EJsignalArrivalsList 
    
    protected double[] EJsynWeights;		//## attribute EJsynWeights 
    
    protected double LeakyCoeff;		//## attribute LeakyCoeff 
    
    protected double SignalAttenuation;		//## attribute SignalAttenuation 
    
    protected double activation;		//## attribute activation 
    
    protected int decaySSteps;		//## attribute decaySSteps 
    
    protected double deltaActivation = 0;		//## attribute deltaActivation 
    
    protected String neuronName;		//## attribute neuronName 
    
    protected int neuronNumber;		//## attribute neuronNumber 
    
    protected String neuronType;		//## attribute neuronType 
    
    protected int[] propTimes;		//## attribute propTimes 
    
    protected ArrayList<String> signalArrivalsList;		//## attribute signalArrivalsList 
    
    protected int transTime;		//## attribute transTime 
    
    protected Manager itsManager;		//## link itsManager 
    
    //#[ ignore 
    public static final int RiJNonState=0;
    public static final int GenNeuronSC=1;
    public static final int state_9=2;
    public static final int cellBodyLeakage=3;
    public static final int state_2=4;
    public static final int transmitTrig=5;
    public static final int noTransmission=6;
    public static final int state_12=7;
    public static final int EJCoupling=8;
    public static final int state_1=9;
    public static final int nonFiringState=10;
    public static final int firingState=11;
    //#]
    protected int rootState_subState;		//## ignore 
    
    protected int rootState_active;		//## ignore 
    
    protected int state_9_subState;		//## ignore 
    
    protected int state_9_active;		//## ignore 
    
    protected int state_2_subState;		//## ignore 
    
    protected int state_2_active;		//## ignore 
    
    protected int state_12_subState;		//## ignore 
    
    protected int state_12_active;		//## ignore 
    
    protected int state_1_subState;		//## ignore 
    
    protected int state_1_active;		//## ignore 
    
    
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
    
    //## auto_generated 
    public  Gen_Neuron(RiJThread p_thread) {
        reactive = new Reactive(p_thread);
    }
    
    //## auto_generated 
    public int getCHSynDelay() {
        return CHSynDelay;
    }
    
    //## auto_generated 
    public void setCHSynDelay(int p_CHSynDelay) {
        CHSynDelay = p_CHSynDelay;
    }
    
    //## auto_generated 
    public double getCHcoeff() {
        return CHcoeff;
    }
    
    //## auto_generated 
    public void setCHcoeff(double p_CHcoeff) {
        CHcoeff = p_CHcoeff;
    }
    
    //## auto_generated 
    public ArrayList<String> getCHsignalArrivalsList() {
        return CHsignalArrivalsList;
    }
    
    //## auto_generated 
    public void setCHsignalArrivalsList(ArrayList<String> p_CHsignalArrivalsList) {
        CHsignalArrivalsList = p_CHsignalArrivalsList;
    }
    
    //## auto_generated 
    public double getCHsynWeights(int i1) {
        return CHsynWeights[i1];
    }
    
    //## auto_generated 
    public void setCHsynWeights(int i1, double p_CHsynWeights) {
        CHsynWeights[i1] = p_CHsynWeights;
    }
    
    //## auto_generated 
    public int getEJSynDelay() {
        return EJSynDelay;
    }
    
    //## auto_generated 
    public void setEJSynDelay(int p_EJSynDelay) {
        EJSynDelay = p_EJSynDelay;
    }
    
    //## auto_generated 
    public double getEJcoeff() {
        return EJcoeff;
    }
    
    //## auto_generated 
    public void setEJcoeff(double p_EJcoeff) {
        EJcoeff = p_EJcoeff;
    }
    
    //## auto_generated 
    public ArrayList<String> getEJsignalArrivalsList() {
        return EJsignalArrivalsList;
    }
    
    //## auto_generated 
    public void setEJsignalArrivalsList(ArrayList<String> p_EJsignalArrivalsList) {
        EJsignalArrivalsList = p_EJsignalArrivalsList;
    }
    
    //## auto_generated 
    public double getEJsynWeights(int i1) {
        return EJsynWeights[i1];
    }
    
    //## auto_generated 
    public void setEJsynWeights(int i1, double p_EJsynWeights) {
        EJsynWeights[i1] = p_EJsynWeights;
    }
    
    //## auto_generated 
    public double getLeakyCoeff() {
        return LeakyCoeff;
    }
    
    //## auto_generated 
    public void setLeakyCoeff(double p_LeakyCoeff) {
        LeakyCoeff = p_LeakyCoeff;
    }
    
    //## auto_generated 
    public double getSignalAttenuation() {
        return SignalAttenuation;
    }
    
    //## auto_generated 
    public void setSignalAttenuation(double p_SignalAttenuation) {
        SignalAttenuation = p_SignalAttenuation;
    }
    
    //## auto_generated 
    public double getActivation() {
        return activation;
    }
    
    //## auto_generated 
    public void setActivation(double p_activation) {
        activation = p_activation;
    }
    
    //## auto_generated 
    public int getDecaySSteps() {
        return decaySSteps;
    }
    
    //## auto_generated 
    public void setDecaySSteps(int p_decaySSteps) {
        decaySSteps = p_decaySSteps;
    }
    
    //## auto_generated 
    public double getDeltaActivation() {
        return deltaActivation;
    }
    
    //## auto_generated 
    public void setDeltaActivation(double p_deltaActivation) {
        deltaActivation = p_deltaActivation;
    }
    
    //## auto_generated 
    public String getNeuronName() {
        return neuronName;
    }
    
    //## auto_generated 
    public void setNeuronName(String p_neuronName) {
        neuronName = p_neuronName;
    }
    
    //## auto_generated 
    public int getNeuronNumber() {
        return neuronNumber;
    }
    
    //## auto_generated 
    public void setNeuronNumber(int p_neuronNumber) {
        neuronNumber = p_neuronNumber;
    }
    
    //## auto_generated 
    public String getNeuronType() {
        return neuronType;
    }
    
    //## auto_generated 
    public void setNeuronType(String p_neuronType) {
        neuronType = p_neuronType;
    }
    
    //## auto_generated 
    public int getPropTimes(int i1) {
        return propTimes[i1];
    }
    
    //## auto_generated 
    public void setPropTimes(int i1, int p_propTimes) {
        propTimes[i1] = p_propTimes;
    }
    
    //## auto_generated 
    public ArrayList<String> getSignalArrivalsList() {
        return signalArrivalsList;
    }
    
    //## auto_generated 
    public void setSignalArrivalsList(ArrayList<String> p_signalArrivalsList) {
        signalArrivalsList = p_signalArrivalsList;
    }
    
    //## auto_generated 
    public int getTransTime() {
        return transTime;
    }
    
    //## auto_generated 
    public void setTransTime(int p_transTime) {
        transTime = p_transTime;
    }
    
    //## auto_generated 
    public Manager getItsManager() {
        return itsManager;
    }
    
    //## auto_generated 
    public void __setItsManager(Manager p_Manager) {
        itsManager = p_Manager;
    }
    
    //## auto_generated 
    public void _setItsManager(Manager p_Manager) {
        if(itsManager != null)
            {
                itsManager._removeItsGen_Neuron(this);
            }
        __setItsManager(p_Manager);
    }
    
    //## auto_generated 
    public void setItsManager(Manager p_Manager) {
        if(p_Manager != null)
            {
                p_Manager._addItsGen_Neuron(this);
            }
        _setItsManager(p_Manager);
    }
    
    //## auto_generated 
    public void _clearItsManager() {
        itsManager = null;
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
            if(state_1 == state)
                {
                    return isIn(GenNeuronSC);
                }
            if(state_1_subState == state)
                {
                    return true;
                }
            if(state_12 == state)
                {
                    return isIn(GenNeuronSC);
                }
            if(state_12_subState == state)
                {
                    return true;
                }
            if(state_2 == state)
                {
                    return isIn(GenNeuronSC);
                }
            if(state_2_subState == state)
                {
                    return true;
                }
            if(state_9 == state)
                {
                    return isIn(GenNeuronSC);
                }
            if(state_9_subState == state)
                {
                    return true;
                }
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
            if(rootState_active == GenNeuronSC)
                {
                    res = GenNeuronSC_dispatchEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public int GenNeuronSC_dispatchEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(state_1_dispatchEvent(id) >= 0)
                {
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                    if(!isIn(GenNeuronSC))
                        {
                            return res;
                        }
                }
            if(state_2_dispatchEvent(id) >= 0)
                {
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                    if(!isIn(GenNeuronSC))
                        {
                            return res;
                        }
                }
            if(state_9_dispatchEvent(id) >= 0)
                {
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                    if(!isIn(GenNeuronSC))
                        {
                            return res;
                        }
                }
            if(state_12_dispatchEvent(id) >= 0)
                {
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                    if(!isIn(GenNeuronSC))
                        {
                            return res;
                        }
                }
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = GenNeuronSC_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public int state_9_dispatchEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(state_9_active == cellBodyLeakage)
                {
                    res = cellBodyLeakage_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public int state_2_dispatchEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            switch (state_2_active) {
                case noTransmission:
                {
                    res = noTransmission_takeEvent(id);
                }
                break;
                case transmitTrig:
                {
                    res = transmitTrig_takeEvent(id);
                }
                break;
                default:
                    break;
            }
            return res;
        }
        
        //## statechart_method 
        public int state_12_dispatchEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(state_12_active == EJCoupling)
                {
                    res = EJCoupling_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public int state_1_dispatchEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            switch (state_1_active) {
                case nonFiringState:
                {
                    res = nonFiringState_takeEvent(id);
                }
                break;
                case firingState:
                {
                    res = firingState_takeEvent(id);
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
            state_9_subState = RiJNonState;
            state_9_active = RiJNonState;
            state_2_subState = RiJNonState;
            state_2_active = RiJNonState;
            state_12_subState = RiJNonState;
            state_12_active = RiJNonState;
            state_1_subState = RiJNonState;
            state_1_active = RiJNonState;
        }
        
        //## statechart_method 
        public void firingState_entDef() {
            firingState_enter();
        }
        
        //## statechart_method 
        public int nonFiringStateTakeNull() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //## transition 8 
            if((getActivation() >= 0.75))
                {
                    nonFiringState_exit();
                    firingState_entDef();
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                }
            return res;
        }
        
        //## statechart_method 
        public void nonFiringState_exit() {
            popNullConfig();
            nonFiringStateExit();
        }
        
        //## statechart_method 
        public void nonFiringState_entDef() {
            nonFiringState_enter();
        }
        
        //## statechart_method 
        public int transmitTrig_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(evNoMoreTrigs.evNoMoreTrigs_Default_id))
                {
                    res = transmitTrigTakeevNoMoreTrigs();
                }
            else if(event.isTypeOf(evTick.evTick_Default_id))
                {
                    res = transmitTrigTakeevTick();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = state_2_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public void state_2Enter() {
        }
        
        //## statechart_method 
        public int cellBodyLeakage_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(evTick.evTick_Default_id))
                {
                    res = cellBodyLeakageTakeevTick();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = state_9_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public int firingStateTakeevTick() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //## transition 9 
            if(getTransTime() < itsManager.getClockTime())
                {
                    firingState_exit();
                    nonFiringState_entDef();
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                }
            return res;
        }
        
        //## statechart_method 
        public void state_1Enter() {
        }
        
        //## statechart_method 
        public void cellBodyLeakageExit() {
        }
        
        //## statechart_method 
        public void cellBodyLeakage_entDef() {
            cellBodyLeakage_enter();
        }
        
        //## statechart_method 
        public void state_9_enter() {
            state_9Enter();
        }
        
        //## statechart_method 
        public int state_12_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void state_12_enter() {
            state_12Enter();
        }
        
        //## statechart_method 
        public void state_9_entDef() {
            state_9_enter();
            state_9EntDef();
        }
        
        //## statechart_method 
        public void firingState_enter() {
            state_1_subState = firingState;
            state_1_active = firingState;
            firingStateEnter();
        }
        
        //## statechart_method 
        public int EJCouplingTakeevTick() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //#[ transition 11 
            double targetActivation = getActivation() + getDeltaActivation();
            if (targetActivation > 1) {targetActivation = 1;}
            else if (targetActivation < -1) {targetActivation = -1;}
            setActivation(targetActivation);
            //#]
            res = RiJStateReactive.TAKE_EVENT_COMPLETE;
            return res;
        }
        
        //## statechart_method 
        public void EJCouplingEnter() {
            //#[ state ROOT.GenNeuronSC.state_12.EJCoupling.(Entry) 
            //comment out 2
            //#]
        }
        
        //## statechart_method 
        public void noTransmission_entDef() {
            noTransmission_enter();
        }
        
        //## statechart_method 
        public void state_2_exit() {
            switch (state_2_subState) {
                case noTransmission:
                {
                    noTransmission_exit();
                }
                break;
                case transmitTrig:
                {
                    transmitTrig_exit();
                }
                break;
                default:
                    break;
            }
            state_2_subState = RiJNonState;
            state_2Exit();
        }
        
        //## statechart_method 
        public int cellBodyLeakageTakeevTick() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //#[ transition 7 
            setActivation(getActivation() * getLeakyCoeff());
            //#]
            res = RiJStateReactive.TAKE_EVENT_COMPLETE;
            return res;
        }
        
        //## statechart_method 
        public int state_9_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void state_1_exit() {
            switch (state_1_subState) {
                case nonFiringState:
                {
                    nonFiringState_exit();
                }
                break;
                case firingState:
                {
                    firingState_exit();
                }
                break;
                default:
                    break;
            }
            state_1_subState = RiJNonState;
            state_1Exit();
        }
        
        //## statechart_method 
        public void state_1_entDef() {
            state_1_enter();
            state_1EntDef();
        }
        
        //## statechart_method 
        public void EJCoupling_entDef() {
            EJCoupling_enter();
        }
        
        //## statechart_method 
        public int transmitTrigTakeevTick() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //## transition 4 
            if(!(CHsignalArrivalsList.isEmpty()))
                {
                    res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                }
            else
                {
                    //## transition 5 
                    if(CHsignalArrivalsList.isEmpty())
                        {
                            //#[ transition 5 
                            gen(new evNoMoreTrigs());
                            //#]
                            res = RiJStateReactive.TAKE_EVENT_COMPLETE;
                        }
                }
            return res;
        }
        
        //## statechart_method 
        public void state_9EntDef() {
            cellBodyLeakage_entDef();
        }
        
        //## statechart_method 
        public void state_12EntDef() {
            //#[ transition 10 
            setDeltaActivation(0);
            //#]
            EJCoupling_entDef();
        }
        
        //## statechart_method 
        public void EJCoupling_exit() {
            EJCouplingExit();
        }
        
        //## statechart_method 
        public void transmitTrig_entDef() {
            transmitTrig_enter();
        }
        
        //## statechart_method 
        public int rootState_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void nonFiringState_enter() {
            pushNullConfig();
            state_1_subState = nonFiringState;
            state_1_active = nonFiringState;
            nonFiringStateEnter();
        }
        
        //## statechart_method 
        public void state_1Exit() {
        }
        
        //## statechart_method 
        public void EJCouplingExit() {
        }
        
        //## statechart_method 
        public void state_12Exit() {
        }
        
        //## statechart_method 
        public void state_12Enter() {
        }
        
        //## statechart_method 
        public void noTransmission_enter() {
            state_2_subState = noTransmission;
            state_2_active = noTransmission;
            noTransmissionEnter();
        }
        
        //## statechart_method 
        public void transmitTrigEnter() {
        }
        
        //## statechart_method 
        public int state_2_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void rootState_enter() {
            rootStateEnter();
        }
        
        //## statechart_method 
        public void rootStateEnter() {
        }
        
        //## statechart_method 
        public void state_2Exit() {
        }
        
        //## statechart_method 
        public void state_9Enter() {
        }
        
        //## statechart_method 
        public void GenNeuronSC_entDef() {
            GenNeuronSC_enter();
            state_1_entDef();
            state_2_entDef();
            state_9_entDef();
            state_12_entDef();
        }
        
        //## statechart_method 
        public void firingStateExit() {
        }
        
        //## statechart_method 
        public void noTransmissionEnter() {
        }
        
        //## statechart_method 
        public void state_2_entDef() {
            state_2_enter();
            state_2EntDef();
        }
        
        //## statechart_method 
        public void GenNeuronSCExit() {
        }
        
        //## statechart_method 
        public void state_12_exit() {
            if(state_12_subState == EJCoupling)
                {
                    EJCoupling_exit();
                }
            state_12_subState = RiJNonState;
            state_12Exit();
        }
        
        //## statechart_method 
        public void transmitTrig_exit() {
            transmitTrigExit();
        }
        
        //## statechart_method 
        public void transmitTrig_enter() {
            state_2_subState = transmitTrig;
            state_2_active = transmitTrig;
            transmitTrigEnter();
        }
        
        //## statechart_method 
        public int GenNeuronSC_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void GenNeuronSCEnter() {
        }
        
        //## statechart_method 
        public void rootStateEntDef() {
            GenNeuronSC_entDef();
        }
        
        //## statechart_method 
        public int nonFiringState_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(RiJEvent.NULL_EVENT_ID))
                {
                    res = nonFiringStateTakeNull();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = state_1_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public void state_1_enter() {
            state_1Enter();
        }
        
        //## statechart_method 
        public void EJCoupling_enter() {
            state_12_subState = EJCoupling;
            state_12_active = EJCoupling;
            EJCouplingEnter();
        }
        
        //## statechart_method 
        public void state_12_entDef() {
            state_12_enter();
            state_12EntDef();
        }
        
        //## statechart_method 
        public int transmitTrigTakeevNoMoreTrigs() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            transmitTrig_exit();
            noTransmission_entDef();
            res = RiJStateReactive.TAKE_EVENT_COMPLETE;
            return res;
        }
        
        //## statechart_method 
        public void cellBodyLeakageEnter() {
        }
        
        //## statechart_method 
        public void state_9_exit() {
            if(state_9_subState == cellBodyLeakage)
                {
                    cellBodyLeakage_exit();
                }
            state_9_subState = RiJNonState;
            state_9Exit();
        }
        
        //## statechart_method 
        public void firingStateEnter() {
            //#[ state ROOT.GenNeuronSC.state_1.firingState.(Entry) 
            setTransTime(itsManager.getClockTime() + getDecaySSteps());
            //addEJlist();
            //addCHlist();
            gen(new evSendTrig());
            
            //#]
        }
        
        //## statechart_method 
        public int state_1_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void noTransmission_exit() {
            noTransmissionExit();
        }
        
        //## statechart_method 
        public void state_2_enter() {
            state_2Enter();
        }
        
        //## statechart_method 
        public void GenNeuronSC_enter() {
            rootState_subState = GenNeuronSC;
            rootState_active = GenNeuronSC;
            GenNeuronSCEnter();
        }
        
        //## statechart_method 
        public int firingState_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(evTick.evTick_Default_id))
                {
                    res = firingStateTakeevTick();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = state_1_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public void nonFiringStateEnter() {
        }
        
        //## statechart_method 
        public void noTransmissionExit() {
        }
        
        //## statechart_method 
        public void transmitTrigExit() {
        }
        
        //## statechart_method 
        public void GenNeuronSC_exit() {
            state_1_exit();
            state_2_exit();
            state_9_exit();
            state_12_exit();
            GenNeuronSCExit();
        }
        
        //## statechart_method 
        public void rootStateExit() {
        }
        
        //## statechart_method 
        public void nonFiringStateExit() {
        }
        
        //## statechart_method 
        public void state_1EntDef() {
            nonFiringState_entDef();
        }
        
        //## statechart_method 
        public int noTransmission_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(evSendTrig.evSendTrig_Default_id))
                {
                    res = noTransmissionTakeevSendTrig();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = state_2_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public void cellBodyLeakage_exit() {
            cellBodyLeakageExit();
        }
        
        //## statechart_method 
        public void firingState_exit() {
            firingStateExit();
        }
        
        //## statechart_method 
        public int EJCoupling_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(evTick.evTick_Default_id))
                {
                    res = EJCouplingTakeevTick();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = state_12_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public int noTransmissionTakeevSendTrig() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            noTransmission_exit();
            transmitTrig_entDef();
            res = RiJStateReactive.TAKE_EVENT_COMPLETE;
            return res;
        }
        
        //## statechart_method 
        public void state_2EntDef() {
            //#[ transition 2 
            CHsignalArrivalsList = new ArrayList<String>();
            //#]
            noTransmission_entDef();
        }
        
        //## statechart_method 
        public void cellBodyLeakage_enter() {
            state_9_subState = cellBodyLeakage;
            state_9_active = cellBodyLeakage;
            cellBodyLeakageEnter();
        }
        
        //## statechart_method 
        public void state_9Exit() {
        }
        
    }
}
/*********************************************************************
	File Path	: DefaultComponent/DefaultConfig/Default/Gen_Neuron.java
*********************************************************************/

