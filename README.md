# Spring boot and Microservice with Kubernates 
Spring demo with Microservice and Kubernetes


## Running kubernates dashboard
- kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml

- kubectl proxy

- Get token : kubectl -n kubernetes-dashboard create token admin-user

## Understanding building individual Microservice

Each microservice contains pom.xml. Before building make sure the docker hub credentials are setting settings.xml file.

To run only build : mvn clean install

To build and push to docker : mvn clean install jlib:build

## Applying kubernates configuration to make pod running.

Go to k8s directory which contains 
- Apply config maps first : kubectl apply -f generic-config-map.yaml
- Setting up mysql pod : kubectl apply -f mysql.yaml
This yaml contains mysql password as well , you can use secrets/config for passwords too.
- Set up zipkin : kubectl apply -f zipkin.yaml
Access zipkin dashboard with http://localhost:9411 which was exposed using loadbalancer
- Set up service registry pod : kubectl apply -f service-registry-deployment.yaml
- Set up config server pod : kubectl apply -f config-service-deployment.yaml
- Set up cloud gateway pod : kubectl apply -f cloud-gateway-deployment.yaml
- Set up other microservices pod : kubectl apply -f payment-service-deployment.yaml/order-service-deployment.yaml/product-service-deployment.yaml

## Related to OKTA Configuration
First create new application in okta . Application type should be open id application.
Authroization server has configuration related to access token and refresh token validity. 

Go to security -> API -> default audience to modify access policies and timing.

To get refresh token , we need to ensure we have grant type refresh token for application and also scope is offline_access provided when retriving tokens. Refer application yaml of cloud gateway.
