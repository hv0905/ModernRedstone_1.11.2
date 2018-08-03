@echo off

if not exist "build.gradle" (
echo �뽫���ű�������MDK�ļ��е�ͬ��Ŀ¼������
echo ��������˳�......
pause > nul
goto :EOF
)

echo.
echo==================
echo MDK����ʵ�ù���
echo.
echo (C) 2017 HV0905 Studio
echo ����1.8-1.12+
echo==================
echo.


:start
echo.
echo #1.������Ŀ
echo #2.���߱�����Ŀ
echo #3.��������
echo #4.�˳�
echo.
set /p choice=��ѡ�����:

if "%choice%" == "1" goto cp
if "%choice%" == "2" goto offcp
if "%choice%" == "3" goto evsetup
if "%choice%" == "4" goto :EOF

echo ������������.
goto start


:cp
echo ��ʼ�������
call gradlew.bat build --info
echo ���ɽ��̽������������������
pause > nul
goto start


:offcp
echo ��ʼ�������
call gradlew.bat build --info --offline
echo ���ɽ��̽������������������
pause > nul
goto start


:evsetup
echo.
echo #0.����
echo #1.�״�ʹ������
echo #2.�״�ʹ�����ã�����ʧ���Զ��������á���ըר�ã�
echo #3.ʹ��Eclipse��ΪIDE
echo #4.��ȡidea����
echo #5.����������ã�����Դ������⣩
echo.
set /p choicea=��ѡ�����:
if "%choicea%" == "0" goto start
if "%choicea%" == "1" goto first
if "%choicea%" == "2" goto second
if "%choicea%" == "3" goto third
if "%choicea%" == "4" goto fourth
if "%choicea%" == "5" goto fiveth
echo ������������.
goto evsetup


:first
echo ��ʼ���� ���ã�1
call gradlew.bat setupDecompWorkspace --info
echo ���ɽ��̽�����������,�������������
pause > nul
goto start
:second
echo ��ʼ���� ���ã�2
call gradlew.bat setupDecompWorkspace --info
echo ��������
goto second
:third
echo ��ʼ���� ���ã�3
call gradlew.bat eclipse --info --offline
echo ���ɽ��̽��������Eclipse����workspace����Ϊ��Ŀ¼�µ�Eclipse�ļ���
echo �������������
pause > nul
goto start
:fourth
echo ��ʼ���� ���ã�4
call gradlew.bat genIntellijRuns --offline --info
echo ���ɽ��̽������������������
pause > nul
goto start
:fiveth
set /p notice=���Ҫ������?��Y/N��
if /I "%notice%"=="Y" (
echo ��ʼ���� ���ã�5
call gradlew.bat clean --info
echo ���ɽ��̽������������������
pause > nul 
)
goto start

