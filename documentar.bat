@echo off

REM Crear carpeta doc si no existe
if not exist "doc" mkdir doc

REM Documentar todo el código fuente y los subpaquetes, incluyendo miembros privados y autores
javadoc -cp "lib" -d doc -sourcepath src -subpackages noventagrados -private -author -version 

REM Comprobar el estado de la generación de la documentación
if %errorlevel% equ 0 (
	echo "Documentacion finalizada."
) else (
	echo "Documentacion errónea."
)

pause
