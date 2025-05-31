@echo off
echo ¿Qué cluster deseas eliminar?
echo 1. kind
echo 2. minikube
set /p opt=Opción [1-2]:

if "%opt%"=="1" (
    set /p CLUSTER_NAME=Nombre del cluster KIND: 
    kind delete cluster --name %CLUSTER_NAME%
) else if "%opt%"=="2" (
    set /p PROFILE=Nombre del perfil MINIKUBE: 
    minikube delete --profile %PROFILE%
) else (
    echo Opción inválida.
)
