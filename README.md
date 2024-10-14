# Учебный проект для изучения Kafka и Docker Compose

## Запустить

```bash
chmod +x build-and-up.sh
./build-and-up.sh
```

## Проверить работоспособность

```bash
# Отправка сообщения на сервис post-service:
curl --location 'localhost:8080/posts' \
--header 'Content-Type: application/json' \
--data '{
    "content": "hello"
}'

# Просмотр аудита на сервисе audit-service:
curl --location 'localhost:8081/audited'
```

## Модули

### post-service

Принимает сообщение и отправляет его в сервис аудита, после чего ожидает подтверждение.

### audit-service

Получает сообщение, производит аудит и отправляет подтверждение обратно.

### common

Содержит общие классы, используемые в обоих сервисах.
