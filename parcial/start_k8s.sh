#!/bin/bash

# 📍 Ruta absoluta del script (sin importar desde dónde lo ejecutes)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
K8S_DIR="$SCRIPT_DIR/k8s"

echo "🔁 Aplicando PersistentVolume..."
kubectl apply -f "$K8S_DIR/persistentVolumes/pv.yaml"

echo "🔁 Aplicando PersistentVolumeClaim..."
kubectl apply -f "$K8S_DIR/persistentVolumesClaims/pvc.yaml"

echo "🚀 Desplegando base de datos (MariaDB)..."
kubectl apply -f "$K8S_DIR/deployments/mariadb-deployment.yaml"
kubectl apply -f "$K8S_DIR/services/mariadb-service.yaml"

echo "🚀 Desplegando microservicios..."
kubectl apply -f "$K8S_DIR/deployments/deployment-guardar.yaml"
kubectl apply -f "$K8S_DIR/deployments/deployment-editar.yaml"
kubectl apply -f "$K8S_DIR/deployments/deployment-eliminar.yaml"
kubectl apply -f "$K8S_DIR/deployments/deployment-buscar.yaml"

kubectl apply -f "$K8S_DIR/services/service-guardar.yaml"
kubectl apply -f "$K8S_DIR/services/service-editar.yaml"
kubectl apply -f "$K8S_DIR/services/service-eliminar.yaml"
kubectl apply -f "$K8S_DIR/services/service-buscar.yaml"

echo "✅ Todos los recursos han sido aplicados."
