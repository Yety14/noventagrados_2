@echo off

REM Crear carpeta bin si no existe
if not exist "bin" mkdir bin

REM Compilar todo el código fuente
javac -d bin -sourcepath src src\noventagrados\textui\NoventaGrados.java

REM Comprobar el estado de la compilación
if %errorlevel% equ 0 (
	echo "Compilacion finalizada."
) else (
	echo "Compilacion erronea."
)

pause
