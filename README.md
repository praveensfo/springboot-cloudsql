# SpringbootMySqlAppDeploymentGCP
 In this project we will learn to deploy Spring boot application with MySql to Google cloud Platform
#gcr.io/innov-htfr-0mhe-1/userdatasample:v3


graph LR
  subgraph "GKE Cluster"
    GKE("GKE Cluster")
    subgraph "Deployment: userdatasample"
      POD1("Pod 1")
      POD2("Pod 2")
      POD3("Pod 3")
    end
    POD1 --> "Service:\ninternal-lb-service"
    POD2 --> "Service:\ninternal-lb-service"
    POD3 --> "Service:\ninternal-lb-service"
  end
  
  "Ingress:\nmy-ingress" --> "Service:\ninternal-lb-service"
  "Internet\nUsers" --> "Ingress:\nmy-ingress"
  
  subgraph "Cloud SQL"
    CLOUDSQL("Cloud SQL\nInstance")
  end
  
  "Service:\ninternal-lb-service" --> CLOUDSQL
  
  subgraph "Kubernetes Resources"
    CONFIGMAP("ConfigMap:\nmy-config-map")
    SECRET("Secret:\nmysql-credentials")
  end
  
  CONFIGMAP --> "Deployment:\nuserdatasample"
  SECRET --> "Deployment:\nuserdatasample"

xplanation:

GKE Cluster: Represents your Google Kubernetes Engine cluster.
Deployment (userdatasample): Shows the deployment with 3 replica pods.
Pods: The individual instances of your Spring Boot application running inside the pods.
Service (internal-lb-service): The internal load balancer that distributes traffic to the pods.
Ingress (my-ingress): Exposes the internal-lb-service to external traffic.
Internet Users: Represents users accessing your application.
Cloud SQL Instance: Your managed Cloud SQL database.
ConfigMap (my-config-map): Holds configuration data (database URL, username) for the application.
Secret (mysql-credentials): Stores sensitive information (database password).
Data Flow:

Internet users send requests to your Ingress.
The Ingress forwards traffic to the internal-lb-service.
The Service distributes traffic among the running pods.
The pods, using configuration from the ConfigMap and Secret, connect to the Cloud SQL instance



ila158083@etforge.com -gcloud_id
gcloud compute ssh [INSTANCE_NAME] --zone=[ZONE]

 gcloud compute ssh --zone "us-west1-a" "master-instance" --tunnel-through-iap --project "innov-htfr-0mhe-1"
 
 kubectl apply --dry-run=client -f userdatasample_deploy.yaml 
 
 kubectl exec -it deployment/userdatasample -c userdatasample -- bash 

kubectl exec -it userdatasample-5f54c998fb-4hsl8 -- env
 
 
 kubectl describe configmap <configmap-name>

kubectl exec -it <pod-name> -- bash -c 'echo $(kubectl get secret mysql-credentials -o jsonpath="{.data.password}" | base64 -d)'


curl -X POST http://10.0.0.84/api/users \
-H "Content-Type: application/json" \
-d '{
  "name": "John foo",
  "role": "developer"
}'

kubectl rollout restart deployment userdatasample -n default


docker rmi -f 9bf679e73388

docker 
   21  docker -v
   22  docker images
   40  docker build -t gcr.io/innov-htfr-0mhe-1/myspringboot:v1 .
   41  gcloud auth configure-docker
   42  docker push gcr.io/innov-htfr-0mhe-1/myspringboot:v1
   43  docker 
   44  docker mages 
   
   Kubectl Cheatsheet with Examples
This cheatsheet provides common kubectl commands grouped by task, along with practical examples.

Assumptions:

You have a working Kubernetes cluster.
Your kubectl is configured to connect to the cluster.
Replace placeholders like <resource-name>, <namespace>, etc. with your actual values.
Basic Commands

Command	Description	Example
kubectl version	Show client and server versions	kubectl version
kubectl cluster-info	View cluster information	kubectl cluster-info
kubectl config get-contexts	List available contexts	kubectl config get-contexts
kubectl config use-context <context>	Switch to a different context	kubectl config use-context my-cluster
kubectl get nodes	List all nodes in the cluster	kubectl get nodes
Namespaces

Command	Description	Example
kubectl get namespaces	List all namespaces	kubectl get namespaces
kubectl create namespace <namespace>	Create a new namespace	kubectl create namespace my-namespace
kubectl delete namespace <namespace>	Delete a namespace	kubectl delete namespace my-namespace
kubectl get all -n <namespace>	List all resources in a specific namespace	kubectl get all -n my-namespace
kubectl -n <namespace> <command> ...	Run any kubectl command in a specific namespace	kubectl -n my-namespace get pods
Deployments

Command	Description	Example
kubectl get deployments	List all deployments	kubectl get deployments
kubectl get deployments -n <namespace>	List deployments in a specific namespace	kubectl get deployments -n my-app
kubectl create -f <deployment-file>.yaml	Create a deployment from a YAML file	kubectl create -f my-deployment.yaml
kubectl apply -f <deployment-file>.yaml	Create or update a deployment from a YAML file	kubectl apply -f my-deployment.yaml
kubectl delete deployment <deployment-name>	Delete a deployment	kubectl delete deployment my-app-deployment
kubectl scale deployment <deployment-name> --replicas=<number>	Scale the number of replicas in a deployment	kubectl scale deployment my-app-deployment --replicas=3
kubectl rollout status deployment <deployment-name>	Check the rollout status of a deployment	kubectl rollout status deployment my-app-deployment
Pods

Command	Description	Example
kubectl get pods	List all pods	kubectl get pods
kubectl get pods -n <namespace>	List pods in a specific namespace	kubectl get pods -n my-app
kubectl describe pod <pod-name>	Get detailed information about a pod	kubectl describe pod my-app-pod
kubectl logs <pod-name>	View logs from a pod	kubectl logs my-app-pod
kubectl logs -f <pod-name>	Follow logs from a pod in real-time	kubectl logs -f my-app-pod
kubectl exec -it <pod-name> -- bash	Open a shell inside a pod (requires bash to be installed in the container)	kubectl exec -it my-app-pod -- bash
kubectl delete pod <pod-name>	Delete a pod	kubectl delete pod my-app-pod
Services

Command	Description	Example
kubectl get services	List all services	kubectl get services
kubectl get services -n <namespace>	List services in a specific namespace	kubectl get services -n my-app
kubectl describe service <service-name>	Get detailed information about a service	kubectl describe service my-app-service
kubectl create -f <service-file>.yaml	Create a service from a YAML file	kubectl create -f my-service.yaml
kubectl apply -f <service-file>.yaml	Create or update a service from a YAML file	kubectl apply -f my-service.yaml
kubectl delete service <service-name>	Delete a service	kubectl delete service my-app-service
ConfigMaps and Secrets

Command	Description	Example
kubectl get configmaps	List all ConfigMaps	kubectl get configmaps
kubectl get secrets	List all Secrets	kubectl get secrets
kubectl describe configmap <configmap-name>	Get detailed information about a ConfigMap	kubectl describe configmap my-config
kubectl describe secret <secret-name>	Get detailed information about a Secret	kubectl describe secret my-db-password
kubectl create -f <configmap/secret-file>.yaml	Create a ConfigMap or Secret from a YAML file	kubectl create -f my-configmap.yaml
kubectl apply -f <configmap/secret-file>.yaml	Create or update a ConfigMap or Secret from a YAML file	kubectl apply -f my-secret.yaml
kubectl delete configmap <configmap-name>	Delete a ConfigMap	kubectl delete configmap my-config
kubectl delete secret <secret-name>	Delete a Secret	kubectl delete secret my-db-password
   
  305  docker images
  
  Find more information at: https://kubernetes.io/docs/reference/kubectl/

Basic Commands (Beginner):
  create          Create a resource from a file or from stdin
  expose          Take a replication controller, service, deployment or pod and expose it as a new Kubernetes service
  run             Run a particular image on the cluster
  set             Set specific features on objects

Basic Commands (Intermediate):
  explain         Get documentation for a resource
  get             Display one or many resources
  edit            Edit a resource on the server
  delete          Delete resources by file names, stdin, resources and names, or by resources and label selector

Deploy Commands:
  rollout         Manage the rollout of a resource
  scale           Set a new size for a deployment, replica set, or replication controller
  autoscale       Auto-scale a deployment, replica set, stateful set, or replication controller

Cluster Management Commands:
  certificate     Modify certificate resources
  cluster-info    Display cluster information
  top             Display resource (CPU/memory) usage
  cordon          Mark node as unschedulable
  uncordon        Mark node as schedulable
  drain           Drain node in preparation for maintenance
  taint           Update the taints on one or more nodes

Troubleshooting and Debugging Commands:
  describe        Show details of a specific resource or group of resources
  logs            Print the logs for a container in a pod
  attach          Attach to a running container
  exec            Execute a command in a container
  port-forward    Forward one or more local ports to a pod
  proxy           Run a proxy to the Kubernetes API server
  cp              Copy files and directories to and from containers
  auth            Inspect authorization
  debug           Create debugging sessions for troubleshooting workloads and nodes
  events          List events

Advanced Commands:
  diff            Diff the live version against a would-be applied version
  apply           Apply a configuration to a resource by file name or stdin
  patch           Update fields of a resource
  replace         Replace a resource by file name or stdin
  wait            Experimental: Wait for a specific condition on one or many resources
  kustomize       Build a kustomization target from a directory or URL

Settings Commands:
  label           Update the labels on a resource
  annotate        Update the annotations on a resource
  completion      Output shell completion code for the specified shell (bash, zsh, fish, or powershell)

Subcommands provided by plugins:

Other Commands:
  api-resources   Print the supported API resources on the server
  api-versions    Print the supported API versions on the server, in the form of "group/version"
  config          Modify kubeconfig files
  plugin          Provides utilities for interacting with plugins
  version         Print the client and server version information
