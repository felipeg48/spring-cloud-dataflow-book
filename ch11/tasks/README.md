# Running Examples


## Docker - MySQL

```shell
docker run -d --rm --name mysql \
 -e MYSQL_DATABASE=movies \
 -e MYSQL_USER=root \
 -e MYSQL_ROOT_PASSWORD=rootpw \
 -p 3306:3306 \
 mysql:5.7.25
```

```shell
mysql -h localhost --protocol TCP -uroot -prootpw movies
```