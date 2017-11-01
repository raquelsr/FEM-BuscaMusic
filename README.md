# BuscaMusic (Gestor de información)

Aplicación que utiliza la API de Last.FM (info: https://www.last.fm/es/api) para obtener información sobre los artistas , discos y éxitos actuales. 

Se basa en gestionar la información, cuando recibe la consulta del cliente lo busca en su base de datos, si la encuentra se la devuelve y en caso contrario, realiza la petición a la API para obtener la información y devolverla y a su vez, la almacena en su base de datos para futuras consultas. Igualmente almacena la información extra que aporta el cliente como puede ser la puntuación o comentarios.


![](https://github.com/raquelsr/FEM-BuscaMusic/blob/master/app/src/main/res/drawable/ic_busca_music_web.png)
