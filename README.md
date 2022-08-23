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
   - There is needed to have an external docker images registry supported by minukube.
   - 

 * There is a platform docker compose project that includes the support applications around Spring Cloud such as:
   - config-service
   - admin-service
   - registry-service
   - gateway-service
 * There is a services docker compose project that includes the business specific microservices such as:
   - product-service
   - customer-service
   - shopping-service
 * The run.sh shells were replaced by Make files scripts.
 * Non necessary external ports were removed.
 
The new deployment of this lab is the following:

# Getting Started
1. Start minikube:
    minikube start --vm-driver=virtualbox

2. Load registry 
    kubectl port-forward --namespace kube-system service/registry 5000:80
    curl http://localhost:5000/v2/_catalog    

2. Push image to registry
3. Create k8s namespace
    k create namespace spring-cloud-demo

4. Create a role
   k apply -f cluster-role.yaml -n default

5. Create a service account
   k create serviceaccount spring-cloud-demo-sa -n spring-cloud-demo

6. Bind service account to role
   k create clusterrolebinding spring-cloud-demo-role-binding --clusterrole=spring-cloud-demo-role --serviceaccount=spring-cloud-demo:spring-cloud-demo-sa

4. Create k8s config-map
    k create -f k8/configmap.yaml -n spring-cloud-demo

 make build
 k delete pod product-service -n spring-cloud-demo
 k apply -f k8/configmap.yaml -n spring-cloud-demo
 k run product-service --image=localhost:5000/product-service --port=8080 -n spring-cloud-demo
 k logs product-service -n spring-cloud-demo -f



### Description

This project is an evolution of the Spring Cloud Docker project located in https://github.com/MauroRussi/spring-cloud-docker.

The details and new features of this lab are:
...

The new deployment of this lab is the following:

![Deployment Diagram](docs/deployment%20view.png?raw=true "Deployment Diagram")

### Configuration to run the project

You need to run first a local docker registry

https://shashanksrivastava.medium.com/create-a-local-docker-registry-on-mac-74cbeac86bfc

You need to setup the following enviroment variables in the [.env file](./config-service/.env):
 * GIT_USER: This contains the username to access the Github repository of the configuration files.
 * GIT_PASSWORD: This contains the password to access the Github repository of the configuration files.

### Run the application

To run the complete solution you should go to the root directory of the project and execute any of the following commands using the *make* script:
 * *make build*: Build and prepare the Docker image for all subprojects.
 * *make compose-down*: Executes docker compose down for platform and services containers.
 * *make compose-start*: Executes docker compose start for platform and services containers.
 * *make compose-stop*: Executes docker compose stop for platform and services containers.
 * *make compose-up*: Executes docker compose up for platform and services containers.
