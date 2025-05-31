#!/bin/bash

echo "🧹 ¿Qué cluster deseas eliminar?"
echo "1. kind"
echo "2. minikube"
read -p "Opción [1-2]: " opt

if [[ "$opt" == "1" ]]; then
  read -p "🔹 Nombre del cluster KIND: " CLUSTER_NAME
  kind delete cluster --name "$CLUSTER_NAME"
elif [[ "$opt" == "2" ]]; then
  read -p "🔹 Nombre del perfil MINIKUBE: " PROFILE
  minikube delete --profile="$PROFILE"
else
  echo "❌ Opción inválida."
fi

