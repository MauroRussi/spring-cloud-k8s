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
   - Ths k8s folder has the kubertes configuration in general and for each project.

The new deployment of this lab is the following:

### Configuration to run the project
1. Start minikube:
   ```shell
   minikube start --vm-driver=virtualbox
   ```

2. Load registry 
   ```shell
   kubectl port-forward --namespace kube-system service/registry 5000:80
   curl http://localhost:5000/v2/_catalog
   ```

3. Create k8s namespace
   ```shell
   kubectl create namespace spring-cloud-demo
   ```

4. Create a role to access K8s APIs
   ```shell
   kubectl apply -f k8s/cluster-role.yaml -n default
   ```

5. Create a service account
   ```shell
   kubectl create serviceaccount spring-cloud-demo-sa -n spring-cloud-demo
   ```

6. Bind service account to role
   ```shell
   kubectl create clusterrolebinding spring-cloud-demo-role-binding --clusterrole=spring-cloud-demo-role --serviceaccount=spring-cloud-demo:spring-cloud-demo-sa
   ```

7. Create k8s config-maps
   ```shell
   kubectl create -f product-service/k8/configmap.yaml -n spring-cloud-demo
   kubectl create -f product-service/k8/configmap.yaml -n spring-cloud-demo
   kubectl create -f product-service/k8/configmap.yaml -n spring-cloud-demo
   ```

 make build
 k delete pod product-service -n spring-cloud-demo
 k apply -f k8/configmap.yaml -n spring-cloud-demo
 k run product-service --image=localhost:5000/product-service --port=8080 -n spring-cloud-demo
 k logs product-service -n spring-cloud-demo -f