echo off

set RHAP_JARS_DIR=C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib
set FRAMEWORK_NONE_JARS=C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\oxf.jar;C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\anim.jar;C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\animcom.jar;C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\oxfInstMock.jar;
set FRAMEWORK_ANIM_JARS=C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\oxf.jar;C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\anim.jar;C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\animcom.jar;C:/Rational/Rhapsody/7.6.1/Share\LangJava\lib\oxfInst.jar;
set SOURCEPATH=%SOURCEPATH%
set CLASSPATH=%CLASSPATH%;.
set PATH=%RHAP_JARS_DIR%;%PATH%;
set INSTRUMENTATION=None   

set BUILDSET=Debug

if %INSTRUMENTATION%==Animation goto anim

:noanim
set CLASSPATH=%CLASSPATH%;%FRAMEWORK_NONE_JARS%
goto setEnv_end

:anim
set CLASSPATH=%CLASSPATH%;%FRAMEWORK_ANIM_JARS%

:setEnv_end

if "%1" == "" goto compile
if "%1" == "build" goto compile
if "%1" == "clean" goto clean
if "%1" == "rebuild" goto clean
if "%1" == "run" goto run

:clean
echo cleaning class files
if exist Default\Gen_IN.class del Default\Gen_IN.class
if exist Default\Gen_MUS.class del Default\Gen_MUS.class
if exist Default\Manager.class del Default\Manager.class
if exist Default\Gen_MN.class del Default\Gen_MN.class
if exist MainDefaultComponent.class del MainDefaultComponent.class
if exist Default\MUS.class del Default\MUS.class
if exist Default\Gen_SN.class del Default\Gen_SN.class
if exist Default\Gen_Neuron.class del Default\Gen_Neuron.class

if "%1" == "clean" goto end

:compile   
if %BUILDSET%==Debug goto compile_debug
echo compiling JAVA source files
javac  @files.lst
goto end

:compile_debug
echo compiling JAVA source files
javac -g  @files.lst
goto end

:run

java %2

:end


