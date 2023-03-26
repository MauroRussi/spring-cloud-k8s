# Getting Started

### Description

This project is an evolution of the Spring Cloud Docker project located in https://github.com/MauroRussi/spring-cloud-docker focused in the deployment on a Kubernetes cluster.

The details and new features of this lab are:
 * The applications view of the architecture changed, several support applications were removed because they are supported out-of-the-box by Kubernetes. This is the list of applications removed and the replacement:
   - config-service is supported by configmaps.
   - admin-service is supported by kubectl and controlplane.
   - registry-service is supported by kubectl and controlplane.
   - gateway-service is supported using ingress.

 * The technology view of the architecture changed as follows:
   - It is needed to have a Kubernetes implementation. I recommend to use [Minikube](https://kubernetes.io/docs/tasks/tools/install-minikube/).
   - It is needed to have an external Docker Images Registry supported by Minukube to load the images and allows K8s to get the images from there.
   - Ths k8s folder has the kubernetes configuration in general and for each project.

To run the project is needed to setup the following components

1. Docker runtime (in macOS use Colima)
   ```shell
   brew install docker docker-compose colima
   ```

2. kubectl and minikube
   ```shell
   brew install kubectl minikube
   ```

3. Configure minikube
   ```shell
   minikube config set driver docker
   minikube start
   minikube status
   
   # List the installed and supported addons
   minikube addons list

   # Install the basic addons   
   minikube addons enable metrics-server
   minikube addons enable dashboard
   minikube addons enable ingress
   ```

4. It is not possible to use minikube registry because it assigns a random port on each startup. I rather use github registry.

The new deployment of this lab is the following:

### Configuration to run the project
1. Start colima (container runtime in macOS) and check docker runtime
   ```shell   
   colima start
   docker info
   ```

1. Start minikube:
   ```shell
   minikube start
   ```

2. Create k8s namespace
   ```shell
   kubectl create namespace spring-cloud-demo
   ```

3. Create the role, service account and role-binding to access K8s APIs
   ```shell
   kubectl apply -f k8s/services-role.yaml
   ```

4. Per service execute
   ```shell
   # Execute it only if the image does not exist in the dockerhub repository
   make build
   make deploy
   ```

 minikube tunnel to allow ingress on 127.0.0.1

 Registry runs on port 49165 instead of default port 5000