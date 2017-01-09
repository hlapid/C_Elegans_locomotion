/*********************************************************************
	Rhapsody	: 7.6.1
	Login		: hlapid
	Component	: DefaultComponent
	Configuration 	: DefaultConfig
	Model Element	: MUS
//!	Generated Date	: Wed, 1, Jun 2016 
	File Path	: DefaultComponent/DefaultConfig/Default/MUS.java
*********************************************************************/

package Default;

//## auto_generated
import java.util.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.states.*;

//----------------------------------------------------------------------------
// Default/MUS.java                                                                  
//----------------------------------------------------------------------------

//## package Default 


//## class MUS 
public class MUS extends Gen_MUS {
    
    public Reactive reactive;		//## ignore 
    
    protected String muscleName;		//## attribute muscleName 
    
    protected int muscleNumber;		//## attribute muscleNumber 
    
    protected String muscleType;		//## attribute muscleType 
    
    protected ArrayList<Gen_MN> itsGen_MN = itsGen_MN = new ArrayList<Gen_MN>();		//## link itsGen_MN 
    
    protected Gen_MUS itsGen_MUS;		//## link itsGen_MUS 
    
    protected ArrayList<MUS> itsMUS = itsMUS = new ArrayList<MUS>();		//## link itsMUS 
    
    protected ArrayList<MUS> itsMUS_1 = itsMUS_1 = new ArrayList<MUS>();		//## link itsMUS_1 
    
    protected Manager itsManager;		//## link itsManager 
    
    
    
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
    
    //## operation MUS() 
    public  MUS(RiJThread p_thread) {
        super(p_thread);
        reactive = new Reactive(p_thread);
        //#[ operation MUS() 
        startBehavior();
        //#]
    }
    
    //## operation updateDeltaActivation() 
    public void updateDeltaActivation() {
        //#[ operation updateDeltaActivation() 
        int num_of_nmj_synapses = 0;
        double nmj_activation = 0.0;
        int dest_neuron_number;
        
        // go through all the motorneurons I'm connected to:
        for (int i = 0; i < itsGen_MN.size(); i++) {  
        	dest_neuron_number = itsGen_MN.get(i).getNeuronNumber();
        	if (NMJWeights[dest_neuron_number-1] != 0) {
        		//add command string to list. the format is: arrivalTime, targetNeuron, type
        		//target neuron is NOT between 1,...,79 but it's the index in itsGen_MN array - easier this way, saves double checking...
        		nmj_activation += NMJWeights[dest_neuron_number-1]*(itsGen_MN.get(i).getActivation() - getActivation()/2);
        		num_of_nmj_synapses += NMJWeights[dest_neuron_number-1]; 
        	}		
        }
        if (num_of_nmj_synapses != 0){   
        	setDeltaActivation(getNMJCoeff() * nmj_activation / num_of_nmj_synapses);  
        }
        //#]
    }
    
    //## auto_generated 
    public String getMuscleName() {
        return muscleName;
    }
    
    //## auto_generated 
    public void setMuscleName(String p_muscleName) {
        muscleName = p_muscleName;
    }
    
    //## auto_generated 
    public int getMuscleNumber() {
        return muscleNumber;
    }
    
    //## auto_generated 
    public void setMuscleNumber(int p_muscleNumber) {
        muscleNumber = p_muscleNumber;
    }
    
    //## auto_generated 
    public String getMuscleType() {
        return muscleType;
    }
    
    //## auto_generated 
    public void setMuscleType(String p_muscleType) {
        muscleType = p_muscleType;
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
                p_Gen_MN._addItsMUS(this);
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
                p_Gen_MN._removeItsMUS(this);
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
            Gen_MN current = itsGen_MN.get(iter.nextIndex());
            if(current != null)
                {
                    current._removeItsMUS(this);
                }
            iter.next();
        }
        _clearItsGen_MN();
    }
    
    //## auto_generated 
    public Gen_MUS getItsGen_MUS() {
        return itsGen_MUS;
    }
    
    //## auto_generated 
    public void setItsGen_MUS(Gen_MUS p_Gen_MUS) {
        itsGen_MUS = p_Gen_MUS;
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
                p_MUS._addItsMUS_1(this);
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
                p_MUS._removeItsMUS_1(this);
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
            MUS current = itsMUS.get(iter.nextIndex());
            if(current != null)
                {
                    current._removeItsMUS_1(this);
                }
            iter.next();
        }
        _clearItsMUS();
    }
    
    //## auto_generated 
    public ListIterator<MUS> getItsMUS_1() {
        ListIterator<MUS> iter = itsMUS_1.listIterator();
        return iter;
    }
    
    //## auto_generated 
    public void _addItsMUS_1(MUS p_MUS) {
        itsMUS_1.add(0, p_MUS);
    }
    
    //## auto_generated 
    public void addItsMUS_1(MUS p_MUS) {
        if(p_MUS != null)
            {
                p_MUS._addItsMUS(this);
            }
        _addItsMUS_1(p_MUS);
    }
    
    //## auto_generated 
    public void _removeItsMUS_1(MUS p_MUS) {
        itsMUS_1.remove(p_MUS);
    }
    
    //## auto_generated 
    public void removeItsMUS_1(MUS p_MUS) {
        if(p_MUS != null)
            {
                p_MUS._removeItsMUS(this);
            }
        _removeItsMUS_1(p_MUS);
    }
    
    //## auto_generated 
    public void _clearItsMUS_1() {
        itsMUS_1.clear();
    }
    
    //## auto_generated 
    public void clearItsMUS_1() {
        ListIterator<MUS> iter = itsMUS_1.listIterator();
        while (iter.hasNext()){
            MUS current = itsMUS_1.get(iter.nextIndex());
            if(current != null)
                {
                    current._removeItsMUS(this);
                }
            iter.next();
        }
        _clearItsMUS_1();
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
                itsManager._removeItsMUS(this);
            }
        __setItsManager(p_Manager);
    }
    
    //## auto_generated 
    public void setItsManager(Manager p_Manager) {
        if(p_Manager != null)
            {
                p_Manager._addItsMUS(this);
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
            if(Gen_MUS_SC_subState == state)
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
            if(rootState_active == NMJCoupling)
                {
                    res = NMJCoupling_takeEvent(id);
                }
            return res;
        }
        
        //## auto_generated 
        protected void initStatechart() {
            rootState_subState = RiJNonState;
            rootState_active = RiJNonState;
            Gen_MUS_SC_subState = RiJNonState;
        }
        
        //## statechart_method 
        public void Gen_MUS_SC_exit() {
            if(Gen_MUS_SC_subState == NMJCoupling)
                {
                    NMJCoupling_exit();
                }
            Gen_MUS_SC_subState = RiJNonState;
            Gen_MUS_SCExit();
        }
        
        //## statechart_method 
        public void Gen_MUS_SC_enter() {
            rootState_subState = Gen_MUS_SC;
            Gen_MUS_SCEnter();
        }
        
        //## statechart_method 
        public void Gen_MUS_SC_entDef() {
            Gen_MUS_SC_enter();
            
            //#[ transition 0 
            setDeltaActivation(0);
            setCa(Ca0);
            //#]
            NMJCoupling_entDef();
        }
        
        //## statechart_method 
        public void NMJCouplingExit() {
        }
        
        //## statechart_method 
        public int Gen_MUS_SC_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public void NMJCouplingEnter() {
        }
        
        //## statechart_method 
        public int rootState_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            return res;
        }
        
        //## statechart_method 
        public int NMJCoupling_takeEvent(short id) {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            if(event.isTypeOf(evTick.evTick_Default_id))
                {
                    res = NMJCouplingTakeevTick();
                }
            
            if(res == RiJStateReactive.TAKE_EVENT_NOT_CONSUMED)
                {
                    res = Gen_MUS_SC_takeEvent(id);
                }
            return res;
        }
        
        //## statechart_method 
        public void NMJCoupling_exit() {
            NMJCouplingExit();
        }
        
        //## statechart_method 
        public void rootState_enter() {
            rootStateEnter();
        }
        
        //## statechart_method 
        public void rootStateEnter() {
        }
        
        //## statechart_method 
        public int NMJCouplingTakeevTick() {
            int res = RiJStateReactive.TAKE_EVENT_NOT_CONSUMED;
            //#[ transition 1 
            double targetActivation = getActivation() + getDeltaActivation();
            if (targetActivation > 1) {targetActivation = 1;}
            else if (targetActivation < -1) {targetActivation = -1;}
            setActivation(targetActivation);
            activation2V();
            calculate_Ca_I();
            if (Ca<Ca0){
            	Ca = Ca0;
            }   
            adjustTone();
            //#]
            res = RiJStateReactive.TAKE_EVENT_COMPLETE;
            return res;
        }
        
        //## statechart_method 
        public void rootStateEntDef() {
            Gen_MUS_SC_entDef();
        }
        
        //## statechart_method 
        public void NMJCoupling_entDef() {
            NMJCoupling_enter();
        }
        
        //## statechart_method 
        public void Gen_MUS_SCExit() {
        }
        
        //## statechart_method 
        public void Gen_MUS_SCEnter() {
        }
        
        //## statechart_method 
        public void rootStateExit() {
        }
        
        //## statechart_method 
        public void NMJCoupling_enter() {
            Gen_MUS_SC_subState = NMJCoupling;
            rootState_active = NMJCoupling;
            NMJCouplingEnter();
        }
        
    }
}
/*********************************************************************
	File Path	: DefaultComponent/DefaultConfig/Default/MUS.java
*********************************************************************/

