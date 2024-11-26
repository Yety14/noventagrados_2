@echo off

REM Crear carpeta doc si no existe
if not exist "doc" mkdir doc

REM Documentar todo el código fuente y los subpaquetes, redirigiendo la salida para ocultar mensajes innecesarios
javadoc -d doc -sourcepath src -subpackages noventagrados >nul 2>&1

REM Comprobar el estado de la compilación
if %errorlevel% equ 0 (
	echo "Documentacion finalizada."
) else (
	echo "Documentacion erronea."
)

pause
