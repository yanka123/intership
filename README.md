# intership

Requirements/Требования
- JDK 8
- Maven 3
- Tomcat 8

Run/Запуск
```
cd intership
maven clear package
rm -rf [tomcat_dir]/webapp/intership*
cp target/intership.war [tomcat_dir]/webapp/
cd [tomcat_dir]/bin
./catalina.sh run
browser: http://localhost:8080/intership/index.html
```
