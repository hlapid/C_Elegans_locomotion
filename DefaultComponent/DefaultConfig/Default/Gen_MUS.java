/*********************************************************************
	Rhapsody	: 7.6.1
	Login		: hlapid
	Component	: DefaultComponent
	Configuration 	: DefaultConfig
	Model Element	: Gen_MUS
//!	Generated Date	: Wed, 15, Jun 2016 
	File Path	: DefaultComponent/DefaultConfig/Default/Gen_MUS.java
*********************************************************************/

package Default;

//## auto_generated
import com.ibm.rational.rhapsody.oxf.*;
//## auto_generated
import com.ibm.rational.rhapsody.oxf.states.*;

//----------------------------------------------------------------------------
// Default/Gen_MUS.java                                                                  
//----------------------------------------------------------------------------

//## package Default 


//## class Gen_MUS 
public class Gen_MUS implements RiJStateConcept {
    
    public Reactive reactive;		//## ignore 
    
    protected double Ca;		//## attribute Ca 
    
    protected double Ca0 = 0;		//## attribute Ca0 
    
    protected double Cahalf_h;		//## attribute Cahalf_h 
    
    protected double Cmem = 0;		//## attribute Cmem 
    
    protected double I_mem;		//## attribute I_mem 
    
    protected double NMJCoeff;		//## attribute NMJCoeff 
    
    protected int[] NMJPropTimes;		//## attribute NMJPropTimes 
    
    protected double[] NMJWeights;		//## attribute NMJWeights 
    
    protected double T_Ca;		//## attribute T_Ca 
    
    protected double T_e;		//## attribute T_e 
    
    protected double T_f;		//## attribute T_f 
    
    protected double T_n;		//## attribute T_n 
    
    protected double T_p;		//## attribute T_p 
    
    protected double T_q;		//## attribute T_q 
    
    protected double V;		//## attribute V 
    
    protected double VCa = 7.4;		//## attribute VCa 
    
    protected double VKF = 0;		//## attribute VKF 
    
    protected double VKS;		//## attribute VKS 
    
    protected double VL = 5;		//## attribute VL 
    
    protected int V_Coeff;		//## attribute V_Coeff 
    
    protected double Vhalf_e;		//## attribute Vhalf_e 
    
    protected double Vhalf_f;		//## attribute Vhalf_f 
    
    protected double Vhalf_n;		//## attribute Vhalf_n 
    
    protected double Vhalf_p;		//## attribute Vhalf_p 
    
    protected double Vhalf_q;		//## attribute Vhalf_q 
    
    protected double Vmax = 0;		//## attribute Vmax 
    
    protected double Vmin = 0;		//## attribute Vmin 
    
    protected double activation;		//## attribute activation 
    
    protected double alphaCa;		//## attribute alphaCa 
    
    protected double deltaActivation = 0;		//## attribute deltaActivation 
    
    protected double delta_t;		//## attribute delta_t 
    
    protected double e;		//## attribute e 
    
    protected double f;		//## attribute f 
    
    protected double gCa = 0;		//## attribute gCa 
    
    protected double gKF;		//## attribute gKF 
    
    protected double gKS = 10e-07;		//## attribute gKS 
    
    protected double gL = 2.55;		//## attribute gL 
    
    protected double k_e;		//## attribute k_e 
    
    protected double k_f;		//## attribute k_f 
    
    protected double k_h;		//## attribute k_h 
    
    protected double k_n;		//## attribute k_n 
    
    protected double k_p;		//## attribute k_p 
    
    protected double k_q;		//## attribute k_q 
    
    protected double n;		//## attribute n 
    
    protected double p;		//## attribute p 
    
    protected double q;		//## attribute q 
    
    protected double thiCa;		//## attribute thiCa 
    
    protected Manager itsManager;		//## link itsManager 
    
    //#[ ignore 
    public static final int RiJNonState=0;
    public static final int Gen_MUS_SC=1;
    public static final int NMJCoupling=2;
    //#]
    protected int rootState_subState;		//## ignore 
    
    protected int rootState_active;		//## ignore 
    
    protected int Gen_MUS_SC_subState;		//## ignore 
    
    
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
    public  Gen_MUS(RiJThread p_thread) {
        reactive = new Reactive(p_thread);
    }
    
    //## operation activation2V() 
    public void activation2V() {
        //#[ operation activation2V() 
        V = getActivation()*(110e-3)-(70e-3);   
        
        	
        //#]
    }
    
    //## operation adjustTone() 
    public void adjustTone() {
        //#[ operation adjustTone() 
        //#]
    }
    
    //## operation calculate_Ca_I() 
    public void calculate_Ca_I() {
        //#[ operation calculate_Ca_I() 
        double dn;
        double dp;
        double dq;
        double de;
        double df;
        double dCa;    
        double IKS;
        double IKF;
        double ICa;
        double h;
        
        dn = (x_inf(V,Vhalf_n,k_n) - n)/T_n;
        n = n + dn*delta_t;
        dp = (x_inf(V,Vhalf_p,k_p) - p)/T_p;
        p = p + dp*delta_t;
        dq = (x_inf(V,Vhalf_q,k_q) - q)/T_q;
        q = q + dq*delta_t;
        de = (x_inf(V,Vhalf_e,k_e) - e)/T_e;
        e = e + de*delta_t;
        df = (x_inf(V,Vhalf_f,k_f) - f)/T_f;
        f = f + df*delta_t;
        h = x_inf(Ca,Cahalf_h,k_h);
                
        IKS = gKS*n*(V - VKS);
        IKF = gKF*Math.pow(p,4)*q*(V - VKF);
        ICa = gCa*Math.pow(e,2)*f*(1 + (h - 1)*alphaCa)*(V - VCa);
                
        dCa = -(Ca/T_Ca + thiCa*ICa);
        Ca = Ca + dCa*delta_t;
        I_mem = (IKS + IKF + ICa);
        
        //#]
    }
    
    /**
     * @param arg1
     * @param arg2
     * @param arg3
    */
    //## operation x_inf(double,double,double) 
    public double x_inf(double arg1, double arg2, double arg3) {
        //#[ operation x_inf(double,double,double) 
        double output;
        output = 1/(1 + Math.exp((arg2 - arg1)/arg3)); 
        return output;
        //#]
    }
    
    //## auto_generated 
    public double getCa() {
        return Ca;
    }
    
    //## auto_generated 
    public void setCa(double p_Ca) {
        Ca = p_Ca;
    }
    
    //## auto_generated 
    public double getCa0() {
        return Ca0;
    }
    
    //## auto_generated 
    public void setCa0(double p_Ca0) {
        Ca0 = p_Ca0;
    }
    
    //## auto_generated 
    public double getCahalf_h() {
        return Cahalf_h;
    }
    
    //## auto_generated 
    public void setCahalf_h(double p_Cahalf_h) {
        Cahalf_h = p_Cahalf_h;
    }
    
    //## auto_generated 
    public double getCmem() {
        return Cmem;
    }
    
    //## auto_generated 
    public void setCmem(double p_Cmem) {
        Cmem = p_Cmem;
    }
    
    //## auto_generated 
    public double getI_mem() {
        return I_mem;
    }
    
    //## auto_generated 
    public void setI_mem(double p_I_mem) {
        I_mem = p_I_mem;
    }
    
    //## auto_generated 
    public double getNMJCoeff() {
        return NMJCoeff;
    }
    
    //## auto_generated 
    public void setNMJCoeff(double p_NMJCoeff) {
        NMJCoeff = p_NMJCoeff;
    }
    
    //## auto_generated 
    public int getNMJPropTimes(int i1) {
        return NMJPropTimes[i1];
    }
    
    //## auto_generated 
    public void setNMJPropTimes(int i1, int p_NMJPropTimes) {
        NMJPropTimes[i1] = p_NMJPropTimes;
    }
    
    //## auto_generated 
    public double getNMJWeights(int i1) {
        return NMJWeights[i1];
    }
    
    //## auto_generated 
    public void setNMJWeights(int i1, double p_NMJWeights) {
        NMJWeights[i1] = p_NMJWeights;
    }
    
    //## auto_generated 
    public double getT_Ca() {
        return T_Ca;
    }
    
    //## auto_generated 
    public void setT_Ca(double p_T_Ca) {
        T_Ca = p_T_Ca;
    }
    
    //## auto_generated 
    public double getT_e() {
        return T_e;
    }
    
    //## auto_generated 
    public void setT_e(double p_T_e) {
        T_e = p_T_e;
    }
    
    //## auto_generated 
    public double getT_f() {
        return T_f;
    }
    
    //## auto_generated 
    public void setT_f(double p_T_f) {
        T_f = p_T_f;
    }
    
    //## auto_generated 
    public double getT_n() {
        return T_n;
    }
    
    //## auto_generated 
    public void setT_n(double p_T_n) {
        T_n = p_T_n;
    }
    
    //## auto_generated 
    public double getT_p() {
        return T_p;
    }
    
    //## auto_generated 
    public void setT_p(double p_T_p) {
        T_p = p_T_p;
    }
    
    //## auto_generated 
    public double getT_q() {
        return T_q;
    }
    
    //## auto_generated 
    public void setT_q(double p_T_q) {
        T_q = p_T_q;
    }
    
    //## auto_generated 
    public double getV() {
        return V;
    }
    
    //## auto_generated 
    public void setV(double p_V) {
        V = p_V;
    }
    
    //## auto_generated 
    public double getVCa() {
        return VCa;
    }
    
    //## auto_generated 
    public void setVCa(double p_VCa) {
        VCa = p_VCa;
    }
    
    //## auto_generated 
    public double getVKF() {
        return VKF;
    }
    
    //## auto_generated 
    public void setVKF(double p_VKF) {
        VKF = p_VKF;
    }
    
    //## auto_generated 
    public double getVKS() {
        return VKS;
    }
    
    //## auto_generated 
    public void setVKS(double p_VKS) {
        VKS = p_VKS;
    }
    
    //## auto_generated 
    public double getVL() {
        return VL;
    }
    
    //## auto_generated 
    public void setVL(double p_VL) {
        VL = p_VL;
    }
    
    //## auto_generated 
    public int getV_Coeff() {
        return V_Coeff;
    }
    
    //## auto_generated 
    public void setV_Coeff(int p_V_Coeff) {
        V_Coeff = p_V_Coeff;
    }
    
    //## auto_generated 
    public double getVhalf_e() {
        return Vhalf_e;
    }
    
    //## auto_generated 
    public void setVhalf_e(double p_Vhalf_e) {
        Vhalf_e = p_Vhalf_e;
    }
    
    //## auto_generated 
    public double getVhalf_f() {
        return Vhalf_f;
    }
    
    //## auto_generated 
    public void setVhalf_f(double p_Vhalf_f) {
        Vhalf_f = p_Vhalf_f;
    }
    
    //## auto_generated 
    public double getVhalf_n() {
        return Vhalf_n;
    }
    
    //## auto_generated 
    public void setVhalf_n(double p_Vhalf_n) {
        Vhalf_n = p_Vhalf_n;
    }
    
    //## auto_generated 
    public double getVhalf_p() {
        return Vhalf_p;
    }
    
    //## auto_generated 
    public void setVhalf_p(double p_Vhalf_p) {
        Vhalf_p = p_Vhalf_p;
    }
    
    //## auto_generated 
    public double getVhalf_q() {
        return Vhalf_q;
    }
    
    //## auto_generated 
    public void setVhalf_q(double p_Vhalf_q) {
        Vhalf_q = p_Vhalf_q;
    }
    
    //## auto_generated 
    public double getVmax() {
        return Vmax;
    }
    
    //## auto_generated 
    public void setVmax(double p_Vmax) {
        Vmax = p_Vmax;
    }
    
    //## auto_generated 
    public double getVmin() {
        return Vmin;
    }
    
    //## auto_generated 
    public void setVmin(double p_Vmin) {
        Vmin = p_Vmin;
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
    public double getAlphaCa() {
        return alphaCa;
    }
    
    //## auto_generated 
    public void setAlphaCa(double p_alphaCa) {
        alphaCa = p_alphaCa;
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
    public double getDelta_t() {
        return delta_t;
    }
    
    //## auto_generated 
    public void setDelta_t(double p_delta_t) {
        delta_t = p_delta_t;
    }
    
    //## auto_generated 
    public double getE() {
        return e;
    }
    
    //## auto_generated 
    public void setE(double p_e) {
        e = p_e;
    }
    
    //## auto_generated 
    public double getF() {
        return f;
    }
    
    //## auto_generated 
    public void setF(double p_f) {
        f = p_f;
    }
    
    //## auto_generated 
    public double getGCa() {
        return gCa;
    }
    
    //## auto_generated 
    public void setGCa(double p_gCa) {
        gCa = p_gCa;
    }
    
    //## auto_generated 
    public double getGKF() {
        return gKF;
    }
    
    //## auto_generated 
    public void setGKF(double p_gKF) {
        gKF = p_gKF;
    }
    
    //## auto_generated 
    public double getGKS() {
        return gKS;
    }
    
    //## auto_generated 
    public void setGKS(double p_gKS) {
        gKS = p_gKS;
    }
    
    //## auto_generated 
    public double getGL() {
        return gL;
    }
    
    //## auto_generated 
    public void setGL(double p_gL) {
        gL = p_gL;
    }
    
    //## auto_generated 
    public double getK_e() {
        return k_e;
    }
    
    //## auto_generated 
    public void setK_e(double p_k_e) {
        k_e = p_k_e;
    }
    
    //## auto_generated 
    public double getK_f() {
        return k_f;
    }
    
    //## auto_generated 
    public void setK_f(double p_k_f) {
        k_f = p_k_f;
    }
    
    //## auto_generated 
    public double getK_h() {
        return k_h;
    }
    
    //## auto_generated 
    public void setK_h(double p_k_h) {
        k_h = p_k_h;
    }
    
    //## auto_generated 
    public double getK_n() {
        return k_n;
    }
    
    //## auto_generated 
    public void setK_n(double p_k_n) {
        k_n = p_k_n;
    }
    
    //## auto_generated 
    public double getK_p() {
        return k_p;
    }
    
    //## auto_generated 
    public void setK_p(double p_k_p) {
        k_p = p_k_p;
    }
    
    //## auto_generated 
    public double getK_q() {
        return k_q;
    }
    
    //## auto_generated 
    public void setK_q(double p_k_q) {
        k_q = p_k_q;
    }
    
    //## auto_generated 
    public double getN() {
        return n;
    }
    
    //## auto_generated 
    public void setN(double p_n) {
        n = p_n;
    }
    
    //## auto_generated 
    public double getP() {
        return p;
    }
    
    //## auto_generated 
    public void setP(double p_p) {
        p = p_p;
    }
    
    //## auto_generated 
    public double getQ() {
        return q;
    }
    
    //## auto_generated 
    public void setQ(double p_q) {
        q = p_q;
    }
    
    //## auto_generated 
    public double getThiCa() {
        return thiCa;
    }
    
    //## auto_generated 
    public void setThiCa(double p_thiCa) {
        thiCa = p_thiCa;
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
                itsManager._removeItsGen_MUS(this);
            }
        __setItsManager(p_Manager);
    }
    
    //## auto_generated 
    public void setItsManager(Manager p_Manager) {
        if(p_Manager != null)
            {
                p_Manager._addItsGen_MUS(this);
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
	File Path	: DefaultComponent/DefaultConfig/Default/Gen_MUS.java
*********************************************************************/

