#!/bin/bash

while true; do
  clear
  echo "🧠 MENÚ KUBERNETES – PARCIAL"
  echo "============================="
  echo "1️⃣  Crear cluster"
  echo "2️⃣  Eliminar cluster"
  echo "3️⃣  Cambiar contexto"
  echo "4️⃣  Start K8s (Despliegue completo)"
  echo "5️⃣  Rollback K8s"
  echo "6️⃣  Salir"
  echo "============================="
  read -p "Selecciona una opción: " opt

  case $opt in
    1)
      bash crear_cluster.sh
      read -p "Presiona ENTER para continuar..."
      ;;
    2)
      bash eliminar_cluster.sh
      read -p "Presiona ENTER para continuar..."
      ;;
    3)
      bash cambiar_contexto.sh
      read -p "Presiona ENTER para continuar..."
      ;;
    4)
      bash start_k8s.sh
      read -p "Presiona ENTER para continuar..."
      ;;
    5)
      bash rollback_k8s.sh
      read -p "Presiona ENTER para continuar..."
      ;;
    6)
      echo "👋 Saliendo..."
      exit 0
      ;;
    *)
      echo "❌ Opción no válida"
      sleep 1
      ;;
  esac
done
