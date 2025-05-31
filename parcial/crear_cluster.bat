@echo off
setlocal enabledelayedexpansion

echo ========================================
echo 🧠 ¿Qué cluster deseas crear?
echo 1. kind
echo 2. minikube
echo ========================================
set /p opt=Opción [1-2]:

REM Verifica docker
where docker >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker no está instalado o no está en el PATH.
    echo 👉 Instálalo desde: https://www.docker.com/products/docker-desktop/
    goto end
)

REM Verifica kubectl
where kubectl >nul 2>&1
if errorlevel 1 (
    echo ❌ kubectl no está instalado o no está en el PATH.
    echo 👉 Descárgalo desde: https://kubernetes.io/docs/tasks/tools/install-kubectl-windows/
    goto end
)

if "%opt%"=="1" (
    where kind >nul 2>&1
    if errorlevel 1 (
        echo ❌ KIND no está instalado.
        echo 👉 Descárgalo desde: https://kind.sigs.k8s.io/
        goto end
    )
    set /p CLUSTER_NAME=🔹 Nombre del cluster KIND: 
    kind create cluster --name %CLUSTER_NAME%
    kubectl config use-context kind-%CLUSTER_NAME%
    echo ✅ Cluster KIND "%CLUSTER_NAME%" creado.
) else if "%opt%"=="2" (
    where minikube >nul 2>&1
    if errorlevel 1 (
        echo ❌ Minikube no está instalado.
        echo 👉 Descárgalo desde: https://minikube.sigs.k8s.io/docs/start/
        goto end
    )
    set /p PROFILE=🔹 Nombre del perfil MINIKUBE: 
    minikube start --driver=docker --profile %PROFILE%
    kubectl config use-context %PROFILE%
    echo ✅ Perfil MINIKUBE "%PROFILE%" creado.
) else (
    echo ❌ Opción inválida.
)

:end
pause
