#!/bin/bash

# 📍 Ruta absoluta del script
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
K8S_DIR="$SCRIPT_DIR/k8s"

echo "🧨 Eliminando microservicios..."
kubectl delete -f "$K8S_DIR/services/service-buscar.yaml"
kubectl delete -f "$K8S_DIR/services/service-editar.yaml"
kubectl delete -f "$K8S_DIR/services/service-eliminar.yaml"
kubectl delete -f "$K8S_DIR/services/service-guardar.yaml"

kubectl delete -f "$K8S_DIR/deployments/deployment-buscar.yaml"
kubectl delete -f "$K8S_DIR/deployments/deployment-editar.yaml"
kubectl delete -f "$K8S_DIR/deployments/deployment-eliminar.yaml"
kubectl delete -f "$K8S_DIR/deployments/deployment-guardar.yaml"

echo "🧨 Eliminando base de datos..."
kubectl delete -f "$K8S_DIR/services/mariadb-service.yaml"
kubectl delete -f "$K8S_DIR/deployments/mariadb-deployment.yaml"

echo "🧨 Eliminando volúmenes..."
kubectl delete -f "$K8S_DIR/persistentVolumesClaims/pvc.yaml"
kubectl delete -f "$K8S_DIR/persistentVolumes/pv.yaml"

echo "✅ Rollback completo."
