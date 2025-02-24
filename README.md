# SOFE3290U_LAB3 - Deploying using Google Kubernetes Engine

### Discussion
After finishing the Docker and Kubernetes lab, I had a solid understanding on how orchestration and containerization function in modern software deployment. Applications can be packaged with all of their dependencies into a container using Docker, an effective tool that guarantees consistency across various environments. Images serve as the blueprints for containers, which house all of the code, runtime, libraries, and dependencies required to run an application. Additionally, I learned about Docker Hub, a cloud registry for storing and sharing Dockerfiles, which specify how to build images.  

In contrast, Kubernetes is a platform for container orchestration that scales the management of containerized applications.  It presents important ideas like deployments, which automate the scaling and management of application replicas, and pods, which are the smallest deployable units with one or more containers.  Ingress is used to manage external access to services, Clusters are used to group machines running workloads managed by Kubernetes, and Services are used to enable network access to pods.  

Docker images have a number of benefits.  They offer portability, which enables programs to function uniformly in a variety of settings, including a testing server, a production cloud system, or a developer's laptop.  Another important advantage is scalability, since Docker integrates easily with orchestration tools like Kubernetes to handle massive deployments.  Additionally, because containers share the same OS kernel, Docker improves resource efficiency by using fewer system resources than traditional virtual machines.  Because containers can be quickly started, stopped, or replaced without affecting the system as a whole, it also makes deployment and updates faster.  Additionally, Docker packages all dependencies inside the container, which solves the common "works on my machine" issue.  

Docker images do have certain drawbacks, though. Containers share the host OS kernel, which increases their vulnerability in the event of a container compromise. This raises security concerns. Because containers are meant to be temporary, storage management can get complicated, particularly when dealing with persistent data. Networking issues could occur, necessitating careful setup to guarantee seamless container-to-container communication. Furthermore, Docker is still slower than running apps directly on bare metal, even with its low performance overhead. Lastly, Docker is primarily optimized for CLI-based workloads, not all applications are well suited for containerization, particularly those with intricate GUI-based interactions.  

Overall, this lab provided valuable hands-on experience with both Docker and Kubernetes, helping me understand how these technologies simplify deployment, improve efficiency, and enable scalability in modern software development.  


### Follow the following steps:
1. Create the Artifact Registry
2. Git clone https://github.com/Huzaifa-Farhan/SOFE3290U_LAB3.git
3. Cd SOFE3290U_LAB3
4. Cd BinaryCalculatorWebapp
5. mvn clean package
6. docker build -t <repo-path>/binarycalculator .
7. docker push <repo-path>/binarycalculator
8. kubectl create deployment binarycalculator-deployment --image <repo-path>/binarycalculator --port=8080
9. kubectl expose deployment binarycalculator-deployment --type=LoadBalancer --name=binarycalculator-service
10. Kubectl get deployments
11. Kubectl get pods
12. Kubectl get services  

Take the external ip address from the Kubectl get services of the binarycalculator-service  
Go to any browser and enter the <External-ip>:8080 (this should launch and show the app working)  

* To get the yaml files in the same GKE console enter the following:  
kubectl get deployment binarycalculator-deployment -o yaml (for the deployment yaml file)  
kubectl get service binarycalculator-service -o yaml (for the service yaml file)  

