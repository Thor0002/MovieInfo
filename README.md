# MovieInfo
REST Приложение для хранения информации о фильмах

Запуск плиложения: java -jar MovieInfo-1.0.0.jar в target

Работа приложения:
1)http://localhost:8080/movie-api/add/movie - добавить фильм(название, описание, тип, жанр, дата выхода)
Для это нужно отправить Post запрос с json вида:
{
    "title" : "Iron Man",
    "description" : "Power",
    "type" : "type",
    "genre" : "Pop",
    "date" : "2022-08-15"
}

2) http://localhost:8080/movie-api/add/movies - добавить несколько фильмов 
Для это нужно отправить Post запрос со споском фильмов в json( [ {фильм1}, .... ])
(В текущий момент если один из запросов не валиден, то ни один из фильмов не будет добавлен. Можно было сделать так, чтобы корректные запросы добавляло)

3)http://localhost:8080/movie-api/get/movies?title=Iron Man - получить фильм или фильмы по названию фильма, типу, или году выхода.
Для это нужно отправить GET запрос с параметрами title, type, year(название, тип, год выхода)

4)http://localhost:8080/h2-console - достут к бд jdbc:h2:mem:main в h2. Пароль: h2.

5)http://localhost:8080/swagger-ui/index.html#/ - отправить запрос через swagger

6)http://localhost:8080/actuator/health - actuator

Лог сохраняется в файл log.txt

P.S. Если поменять бд в application.properties на jdbc:h2:file:/filename, то можно сохранять данные в таблицах в файле. 
Пытался сделать сохранение через скрипты(SCRIPT TO 'h2database', RUNSCRIPT FROM 'h2database'). Тогда не собирался jar.
