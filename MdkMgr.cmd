@echo off

if not exist "build.gradle" (
echo 请将本脚本放置在MDK文件夹的同级目录再启动
echo 按任意键退出......
pause > nul
goto :EOF
)

echo.
echo==================
echo MDK配置实用工具
echo.
echo (C) 2017 HV0905 Studio
echo 兼容1.8-1.12+
echo==================
echo.


:start
echo.
echo #1.编译项目
echo #2.离线编译项目
echo #3.环境配置
echo #4.退出
echo.
set /p choice=请选择操作:

if "%choice%" == "1" goto cp
if "%choice%" == "2" goto offcp
if "%choice%" == "3" goto evsetup
if "%choice%" == "4" goto :EOF

echo 您的输入有误.
goto start


:cp
echo 开始编译代码
call gradlew.bat build --info
echo 生成进程结束，按下任意键返回
pause > nul
goto start


:offcp
echo 开始编译代码
call gradlew.bat build --info --offline
echo 生成进程结束，按下任意键返回
pause > nul
goto start


:evsetup
echo.
echo #0.返回
echo #1.首次使用配置
echo #2.首次使用配置（配置失败自动重新配置。网炸专用）
echo #3.使用Eclipse作为IDE
echo #4.获取idea运行
echo #5.清除所有配置（您的源代码除外）
echo.
set /p choicea=请选择操作:
if "%choicea%" == "0" goto start
if "%choicea%" == "1" goto first
if "%choicea%" == "2" goto second
if "%choicea%" == "3" goto third
if "%choicea%" == "4" goto fourth
if "%choicea%" == "5" goto fiveth
echo 您的输入有误.
goto evsetup


:first
echo 开始生成 配置：1
call gradlew.bat setupDecompWorkspace --info
echo 生成进程结束，请检查结果,按下任意键返回
pause > nul
goto start
:second
echo 开始生成 配置：2
call gradlew.bat setupDecompWorkspace --info
echo 重新配置
goto second
:third
echo 开始生成 配置：3
call gradlew.bat eclipse --info --offline
echo 生成进程结束，请打开Eclipse，将workspace设置为本目录下的Eclipse文件夹
echo 按下任意键返回
pause > nul
goto start
:fourth
echo 开始生成 配置：4
call gradlew.bat genIntellijRuns --offline --info
echo 生成进程结束，按下任意键返回
pause > nul
goto start
:fiveth
set /p notice=真的要重置吗?（Y/N）
if /I "%notice%"=="Y" (
echo 开始生成 配置：5
call gradlew.bat clean --info
echo 生成进程结束，按下任意键返回
pause > nul 
)
goto start

