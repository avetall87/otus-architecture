# Как развернуть приложение в minikube

1. Создаем namespace hw2
- kubectl create namespace hw2
2. Создаем конфигмапу и секрет:
- kubectl apply -f hw2-db-secret-configmap.yaml -n hw2
3. Устанавливаем БД
- Устанавливаем helm
- Добавляем репозиторий с помощью команды: helm repo add bitnami https://charts.bitnami.com/bitnami
- Устанавливаем базу данных с помощь команды: helm install hw2-postgresql bitnami/postgresql -f pg-values.yaml -n hw2
#### В итоге должены появится: Pod, StatefulSet, PVC, Service для postgresql
4. Делоим приложение
- kubectl apply -f hw2-deployment.yaml -n hw2
5. Создаем сервис для приложения
- kubectl apply -f hw2-service.yaml -n hw2
6. Создаем ингресс для доступа из вне
- kubectl apply -f hw2-ingress.yaml -n hw2

---
Для обращения к сервису нужно в Minikube активировать addon ingress и выполнить команду: **minikube ip**, данный ip адрес будет внешним адресом для ингреса. Далее можно добавить запись в /etc/hosts: <ingress ip> arch.homework
