# Spring boot and Microservice with Kubernetes 
Spring demo with Microservice and Kubernetes


## Running Kubernetes dashboard
- kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml

- kubectl proxy

- Get token: kubectl -n kubernetes-dashboard create token admin-user

## Understanding building individual Microservice

Each microservice contains pom.xml. Before building make sure the docker hub credentials are setting settings.xml file.

To run only build : mvn clean install

To build and push to docker: mvn clean install jib:build

## Applying Kubernetes configuration to make pod run.

Go to the k8s directory which contains 
- Apply config maps first: kubectl apply -f generic-config-map.yaml
- Setting up mysql pod: kubectl apply -f mysql.yaml
This YAML contains mysql passwords as well , you can use secrets/config for passwords too.
- Set up Zipkin: kubectl apply -f zipkin.yaml
Access Zipkin dashboard with http://localhost:9411 which was exposed using load balancer
- Set up service registry pod: kubectl apply -f service-registry-deployment.yaml
- Set up config server pod: kubectl apply -f config-service-deployment.yaml
- Set up cloud gateway pod: kubectl apply -f cloud-gateway-deployment.yaml
- Set up other microservices pod: kubectl apply -f payment-service-deployment.yaml/order-service-deployment.yaml/product-service-deployment.yaml

## Related to OKTA Configuration
First, create a new application in okta . The application type should be open id application.
Authorization server has a configuration related to access token and refreshes token validity. 

Go to security -> API -> default audience to modify access policies and timing.

To get a refresh token, we need to ensure we have grant type refresh token for the application, and also scope is offline_access provided when retrieving tokens. Refer to application yaml of cloud gateway.
