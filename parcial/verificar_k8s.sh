#!/bin/bash

echo "📋 Verificando recursos en todos los namespaces..."
kubectl get all -A
echo ""

echo "📦 Verificando pods en el namespace 'dev'..."
kubectl get pods -n dev
echo ""

echo "🔁 Verificando ReplicaSets en el namespace 'dev'..."
kubectl get rs -n dev
echo ""

echo "💾 Verificando volúmenes (PVC y PV)..."
kubectl get pvc,pv
echo ""

echo "✅ Verificación completada."
