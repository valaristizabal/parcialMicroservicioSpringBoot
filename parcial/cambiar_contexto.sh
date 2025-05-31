#!/bin/bash

echo "🔄 Contextos disponibles:"
kubectl config get-contexts

read -p "✏️ Escribe el nombre del contexto que deseas usar: " CONTEXTO
kubectl config use-context "$CONTEXTO"
