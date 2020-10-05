@echo off

if not exist build mkdir build
dir /s /B *.java > sources.txt
javac @sources.txt -d build
cd build
start java com.amn.tictactoe.Main
cd ..