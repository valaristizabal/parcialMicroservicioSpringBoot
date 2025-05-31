#!/bin/bash

check_command() {
  if ! command -v "$1" &>/dev/null; then
    echo "❌ El comando '$1' no está instalado."
    case "$1" in
      kind)
        echo "👉 Instala KIND con:"
        echo "   curl -Lo ./kind https://kind.sigs.k8s.io/dl/latest/kind-$(uname)-amd64"
        echo "   chmod +x ./kind && sudo mv ./kind /usr/local/bin/kind"
        ;;
      minikube)
        echo "👉 Instala Minikube con:"
        echo "   curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64"
        echo "   sudo install minikube-linux-amd64 /usr/local/bin/minikube"
        ;;
      kubectl)
        echo "👉 Instala kubectl con:"
        echo "   curl -LO https://dl.k8s.io/release/$(curl -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
        echo "   chmod +x kubectl && sudo mv kubectl /usr/local/bin/"
        ;;
      docker)
        echo "👉 Instala Docker siguiendo la guía oficial:"
        echo "   https://docs.docker.com/engine/install/"
        ;;
    esac
    exit 1
  fi
}

echo "🧠 ¿Qué cluster deseas crear?"
echo "1. kind"
echo "2. minikube"
read -p "Opción [1-2]: " opt

check_command docker
check_command kubectl

if [[ "$opt" == "1" ]]; then
  check_command kind
  read -p "🔹 Nombre del cluster KIND: " CLUSTER_NAME
  kind create cluster --name "$CLUSTER_NAME"
  kubectl config use-context "kind-$CLUSTER_NAME"
elif [[ "$opt" == "2" ]]; then
  check_command minikube
  read -p "🔹 Nombre del perfil MINIKUBE: " PROFILE
  minikube start --driver=docker --profile="$PROFILE"
  kubectl config use-context "$PROFILE"
else
  echo "❌ Opción inválida."
fi
