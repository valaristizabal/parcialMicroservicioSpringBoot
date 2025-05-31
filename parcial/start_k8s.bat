@echo off
echo 🔁 Aplicando PersistentVolume...
kubectl apply -f k8s\persistentVolumes\pv.yaml

echo 🔁 Aplicando PersistentVolumeClaim...
kubectl apply -f k8s\persistentVolumesClaims\pvc.yaml

echo 🚀 Desplegando base de datos (MariaDB)...
kubectl apply -f k8s\deployments\mariadb-deployment.yaml
kubectl apply -f k8s\services\mariadb-service.yaml

echo 🚀 Desplegando microservicios...
kubectl apply -f k8s\deployments\deployment-guardar.yaml
kubectl apply -f k8s\deployments\deployment-editar.yaml
kubectl apply -f k8s\deployments\deployment-eliminar.yaml
kubectl apply -f k8s\deployments\deployment-buscar.yaml

kubectl apply -f k8s\services\service-guardar.yaml
kubectl apply -f k8s\services\service-editar.yaml
kubectl apply -f k8s\services\service-eliminar.yaml
kubectl apply -f k8s\services\service-buscar.yaml

echo ✅ Todos los recursos han sido aplicados.
pause
