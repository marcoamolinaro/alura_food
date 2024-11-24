## Para Rodar outra instância 
## Através do Git Bash usar o comando
./mvnw spring-boot:run -f pom.xml

## Para rodar o mysql no docker
docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:8.0.0

# Comando para gerar a imagem docker
docker build -t mam1963/pedidos-ms .