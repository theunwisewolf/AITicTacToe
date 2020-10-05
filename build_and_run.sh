#!/bin/bash

mkdir build
find -name "*.java" > sources.txt
javac @sources.txt -d build
cd build
java com.amn.tictactoe.Main
cd ..