@echo off

REM Crear carpeta bin si no existe
if not exist "bin" mkdir bin

REM Compilar todo el código fuente
javac -d bin -sourcepath src src\noventagrados\textui\NoventaGrados.java

echo "Compilacion finalizada"
pause
