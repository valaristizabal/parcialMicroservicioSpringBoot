@echo off
echo 🧨 Eliminando microservicios...
kubectl delete -f k8s\services\service-buscar.yaml
kubectl delete -f k8s\services\service-editar.yaml
kubectl delete -f k8s\services\service-eliminar.yaml
kubectl delete -f k8s\services\service-guardar.yaml

kubectl delete -f k8s\deployments\deployment-buscar.yaml
kubectl delete -f k8s\deployments\deployment-editar.yaml
kubectl delete -f k8s\deployments\deployment-eliminar.yaml
kubectl delete -f k8s\deployments\deployment-guardar.yaml

echo 🧨 Eliminando base de datos...
kubectl delete -f k8s\services\mariadb-service.yaml
kubectl delete -f k8s\deployments\mariadb-deployment.yaml

echo 🧨 Eliminando volúmenes...
kubectl delete -f k8s\persistentVolumesClaims\pvc.yaml
kubectl delete -f k8s\persistentVolumes\pv.yaml

echo 🔁 Rollback completo.
pause
