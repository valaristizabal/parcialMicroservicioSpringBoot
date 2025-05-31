@echo off
:menu
cls
echo ============================
echo    MENÚ KUBERNETES – PARCIAL
echo ============================
echo 1. Crear cluster
echo 2. Eliminar cluster
echo 3. Cambiar contexto
echo 4. Start K8s (Despliegue completo)
echo 5. Rollback K8s
echo 6. Salir
echo ============================
set /p option=Selecciona una opción:

if "%option%"=="1" (
    call crear_cluster.bat
    pause
    goto menu
)
if "%option%"=="2" (
    call eliminar_cluster.bat
    pause
    goto menu
)
if "%option%"=="3" (
    call cambiar_contexto.bat
    pause
    goto menu
)
if "%option%"=="4" (
    call start_k8s.bat
    pause
    goto menu
)
if "%option%"=="5" (
    call rollback_k8s.bat
    pause
    goto menu
)
if "%option%"=="6" (
    echo Saliendo...
    exit /B
)
echo Opción inválida.
pause
goto menu
