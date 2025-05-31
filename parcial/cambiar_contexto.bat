@echo off
echo 🔄 Contextos disponibles:
kubectl config get-contexts

set /p CONTEXTO=✏️ Escribe el nombre del contexto que deseas usar: 
kubectl config use-context %CONTEXTO%
