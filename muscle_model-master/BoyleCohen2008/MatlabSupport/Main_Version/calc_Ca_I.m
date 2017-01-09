function [Ca,I_mem,n,p,q,e,f] = calc_Ca_I(V,Ca,n,p,q,e,f,...
                   Vhalf_n,Vhalf_p,Vhalf_q,Vhalf_e,Vhalf_f,Cahalf_h,...
                   k_n,k_p,k_q,k_e,k_f,k_h,...
                   T_n,T_p,T_q,T_e,T_f,T_Ca,...
                   VKS,VKF,alphaCa,gKS,gKF,gCa,...
                   VCa,thiCa,deltat)
    dn = (x_inf(V,Vhalf_n,k_n) - n)/T_n;
    n = n + dn*deltat;
    dp = (x_inf(V,Vhalf_p,k_p) - p)/T_p;
    p = p + dp*deltat;
    dq = (x_inf(V,Vhalf_q,k_q) - q)/T_q;
    q = q + dq*deltat;
    de = (x_inf(V,Vhalf_e,k_e) - e)/T_e;
    e = e + de*deltat;
    df = (x_inf(V,Vhalf_f,k_f) - f)/T_f;
    f = f + df*deltat;
    h = x_inf(Ca,Cahalf_h,k_h);
        
    IKS = gKS*n*(V - VKS);
    IKF = gKF*p^4*q*(V - VKF);
    ICa = gCa*e^2*f*(1 + (h - 1)*alphaCa)*(V - VCa);
        
    dCa = -(Ca/T_Ca + thiCa*ICa);
    Ca = Ca + dCa*deltat;
    I_mem = (IKS + IKF + ICa);
end