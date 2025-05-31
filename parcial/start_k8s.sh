#!/bin/bash

# 📍 Ruta absoluta del script (sin importar desde dónde lo ejecutes)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
K8S_DIR="$SCRIPT_DIR/k8s"

echo "📁 Asegurando namespace 'dev'..."
kubectl apply -f "$K8S_DIR/namespaces/namespace-dev.yaml"

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

echo "🔁 Aplicando Services de microservicios..."
kubectl apply -f "$K8S_DIR/services/service-guardar.yaml"
kubectl apply -f "$K8S_DIR/services/service-editar.yaml"
kubectl apply -f "$K8S_DIR/services/service-eliminar.yaml"
kubectl apply -f "$K8S_DIR/services/service-buscar.yaml"

echo "🚀 Desplegando pod individual..."
kubectl apply -f "$K8S_DIR/pods/pod-01.yaml"

echo "🔁 Aplicando ReplicaSet nginx en namespace 'dev'..."
kubectl apply -f "$K8S_DIR/replicasets/replicaset-demo.yaml" -n dev

echo "🌐 Aplicando Ingress..."
kubectl apply -f "$K8S_DIR/ingress/ingress-app.yaml"

echo "✅ Todos los recursos han sido aplicados correctamente."
