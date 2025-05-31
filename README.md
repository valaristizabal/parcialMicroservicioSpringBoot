# 🐳 Crear Dockerfile

# Etapa 1: Construcción

FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/\*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

## utilizar estos codigos para crear la imagen en docker

docker build -t parcial-microservicio .

## con esto probamos de que el servicio este corriendo ya en desde docker

docker run -p 8080:8080 parcial-microservicio

# 🧱 Construir imagen para subir a Docker hub

## correr codigo para levantar todo con dokcer compose

docker-compose up -d

## para verificar

docker ps

docker build -t parcial-app:latest .

## loguear con docker login

## etiquetar la imagen

docker tag parcial-app:latest jamespipe/parcial-app:latest

## subir img a docker hub

docker push jamespipe/parcial-app:latest

# 🧩 Proyecto Microservicio CRUD - Kubernetes + Docker + MariaDB

Este proyecto implementa un sistema distribuido con microservicios para operaciones CRUD sobre una base de datos MariaDB, orquestado con **Kubernetes** y empaquetado con **Docker**. El despliegue garantiza tolerancia a fallos y modularidad.

---

## 📦 Componentes Principales

| Componente    | Descripción                                      |
| ------------- | ------------------------------------------------ |
| Microservicio | API Spring Boot con operaciones sobre clientes   |
| MariaDB       | Base de datos relacional                         |
| Kubernetes    | Orquestador de contenedores                      |
| Docker        | Contenerización de la aplicación y base de datos |

---

## 📁 Estructura del Proyecto

```
.
├── Dockerfile
├── docker-compose.yml
├── k8s/
│   ├── deployments/
│   │   ├── guardar-cliente-deploy.yaml
│   │   ├── eliminar-cliente-deploy.yaml
│   │   ├── actualizar-cliente-deploy.yaml
│   │   ├── obtener-id-deploy.yaml
│   │   └── mariadb-deployment.yaml
│   ├── services/
│   │   └── *.yaml
│   ├── persistentVolumes/
│   │   └── pv.yaml
│   ├── persistentVolumesClaims/
│       └── pvc.yaml
```

---

## 🚀 Instrucciones de Despliegue

### 1. Clonar el proyecto y posicionarse en la raíz:

```bash
git clone https://github.com/valaristizabal/parcialMicroservicioSpringBoot.git
cd parcial
```

---

### 2. Construir y subir la imagen a Docker Hub

```bash
docker build -t duvcy/parcial-microservicio:latest .
docker push duvcy/parcial-microservicio:latest
```

> ⚠️ Asegúrate de haber iniciado sesión en Docker Hub con `docker login`.

---

### 3. Crear volúmenes persistentes

```bash
kubectl apply -f k8s/persistentVolumes/pv.yaml
kubectl apply -f k8s/persistentVolumesClaims/pvc.yaml
```

---

### 4. Desplegar la base de datos

```bash
kubectl apply -f k8s/deployments/mariadb-deployment.yaml
kubectl apply -f k8s/services/mariadb-service.yaml
```

---

### 5. Desplegar microservicios

```bash
kubectl apply -f k8s/deployments/
kubectl apply -f k8s/services/
```

---

### 6. Verificar estado de pods

```bash
kubectl get pods
```

---

### 7. Probar el microservicio localmente

```bash
kubectl port-forward deployment/guardar-cliente-deploy 8080:8080
```

Luego desde otra terminal:

```bash
curl -X POST http://localhost:8080/api/clientes \
  -H "Content-Type: application/json" \
  -d '{"nombre":"James", "apellido":"Olarte"}'
```

---

## 🧱 Recomendaciones Profesionales

- Usa `kubectl logs <nombre-pod>` para ver errores cuando un microservicio entra en `CrashLoopBackOff`.
- Todos los servicios están bajo red interna (`ClusterIP`) por seguridad.
- Puedes implementar balanceo de carga usando un `Ingress Controller` (Nginx o Traefik).

---

## 📸 Capturas y evidencia (por agregar)

<img width="694" alt="image" src="https://github.com/user-attachments/assets/758f4523-8c53-42ba-88ac-d70f55d598c5" />


- `kubectl get pods`
- `kubectl get svc`
- Ejecución exitosa de un `curl`
- Base de datos consultada desde un cliente SQL

---
## ver lista de cluster
kubectl config get-contexts    

## cambiar contexto de cluster 
kubectl config use-context <nombre-del-contexto>

## 👨‍💻 Autores

**Daniela Soto, Valeria Aristizabal y James Olarte López**  
Proyecto académico con enfoque profesional.
