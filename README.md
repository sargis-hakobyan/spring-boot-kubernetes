# spring-boot-kubernetes
Running Spring Boot two Applications With Minikube

1. Please setup docker, minikube and kubectl in your local environment
2. Pull application 
3. To deploy Backend and Frontend Apps do this steps for each project (backend-app and frontend-app).
4. In the application we are using spotify's maven plugin `dockerfile-maven-plugin` to build and push docker image to docker repository, so please update username, password on repository in `<configuration>` section in pom.xml
5. Clean install the application
6. Open dockerfile plugin and run dockerfile:build and dockerfile:push
7. Open terminal and navigate to `spring-boot-kubernetes/backend-app/` for backend app and `spring-boot-kubernetes/frontend-app/` for frontend app
8. Deploy backend-app running command: `kubectl create -f deployment.yaml`
9. Check if deployment is successfull: `kubectl get deployments.` and check if deployment is successful. Output should look like something like this for backend: `demo-backend    2         2         2            2           26m` and like this for frontend: `demo-frontend   2         2         2            2           43m`
10. Create service running command: `kubectl create -f service.yaml`
11. Check if service is created successfully, run command: `kubectl get services.`. Output should be like: `demo-backend    ClusterIP   10.104.12.137   <none>        8080/TCP         16s`, and this for frontend `demo-frontend   NodePort    10.107.164.154   <none>        8080:30001/TCP   42m`
12. Check if pods are created, run this command: `kubectl get pods`. Result should be similar to this, for each pod:
`demo-backend-6fdf8b55b-57qn2     1/1     Running   0          27m`
13. After deploying front end and backend apps run command `minikube service demo-frontend`. Command will open browser. Goto to opened tab and in address bar add /greetings at the end of url. Should be something like this: `http://192.168.64.8:30001/greetings`. And the output should be `Demo Backend :)`.
14. Type this url: `http://192.168.64.8:30001/capital/armenia` and the result should be `The capital of armenia is Yerevan`.


Referencees: 
https://www.baeldung.com/spring-boot-minikube
https://gorillalogic.com/blog/build-and-deploy-a-spring-boot-app-on-kubernetes-minikube/
