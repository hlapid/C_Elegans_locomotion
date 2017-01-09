%Program for simulating full model with coupling
clear all;
%importing data
data = importdata('data/input.csv'); %Not sure if these are the best paramaters...

% Model paramaters

Cmem = data(1);
gKS = data(2)*Cmem;
gKF = data(3)*Cmem;
gCa = data(4)*Cmem;
gL = data(5)*Cmem;
VKS = data(6);
VKF = data(7);
VCa = data(8);
VL = 10e-3;%data(9);
Vhalf_n = data(10);
Vhalf_p = data(11);
Vhalf_q = data(12);
Vhalf_e = data(13);
Vhalf_f = data(14);
Cahalf_h = data(15)*1e-9;
k_n = data(16);
k_p = data(17);
k_q = data(18);
k_e = data(19);
k_f = data(20);
k_h = data(21)*1e-6;%1e-9;
T_n = data(22);
T_p = data(23);
T_q = data(24);
T_e = data(25);
T_f = data(26);
T_Ca = data(27);
alphaCa = data(28);
thiCa = 6.1e-6/(T_Ca*gCa);%2.39e-6;

%Simulation Paramaters
deltat = 1e-04;%0.01e-3;
duration = 2;%0.03;  %*********************      Duration    Duration   ***************
numpoints = round(duration/deltat);
% numtests = 11; 
xaxis = (deltat:deltat:duration)'*1e3;

%Input paramaters
% onset = round(0.002/deltat);
% offset = round(0.022/deltat);
onset = round(1.1/deltat);
offset = round(1.8/deltat);

%Variable Declaration
V = -70e-3*ones(numpoints,1);
I_mem = zeros(numpoints,1);
Ca = zeros(numpoints,1);
n = 0;
p = 0;
q = 0;
e = 0;
f = 0;

%input initialization

Vstim = 40e-3;
for j = onset:offset
    V(j) = Vstim;
end


%variable initialization
Ca = 0;
n = x_inf(V(1),Vhalf_n,k_n);
p = x_inf(V(1),Vhalf_p,k_p);
q = x_inf(V(1),Vhalf_q,k_q);
e = x_inf(V(1),Vhalf_e,k_e);
f = x_inf(V(1),Vhalf_f,k_f);

%start of simulation
temp(1) = e;
for i = 2:numpoints
    [Ca(i),I_mem(i),n,p,q,e,f] = calc_Ca_I(V(i-1),Ca(i-1),n,p,q,e,f,...
                                             Vhalf_n,Vhalf_p,Vhalf_q,Vhalf_e,Vhalf_f,Cahalf_h,...
                                             k_n,k_p,k_q,k_e,k_f,k_h,...
                                             T_n,T_p,T_q,T_e,T_f,T_Ca,...
                                             VKS,VKF,alphaCa,gKS,gKF,gCa,...
                                             VCa,thiCa,deltat);
                                 temp(i) = e;
end
figure
subplot(2,1,1);
plot(xaxis,I_mem.*1e9,'k')
hold on;
subplot(2,1,2);
plot(xaxis,Ca,'k')

axis tight;
ylabel('I_{mem} (nA)')
xlabel('Time (ms)')
