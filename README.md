# ProyectoBaraja

# Miembro del Proyecto
Sean Sáez Fuller

# Instruccion para ejecutar el proyecto
Antes de todos guardamos el archivo carta XML en la base de datos exist en una coleccion llamada ProyectoBaraja.

1 - Para empezar en el proyecto tenemos que ir al package "main.implementacionesDAO" y abrimos la clase "ConectionExist".

2 - En esta comanda que esta arriba decimos que pille el archivo con esta URL => protected static String URI = "xmldb:exist://localhost:8844/exist/xmlrpc/db/ProyectoBaraja";

3 - Y aqui escibimos el nombre del archivo XML que tenemos creada en la base de datos ExistDB => 	protected static String resourceName = "cartas";

4 - Para finalizar vamos al package main.principal, abrimos la clase "Principal" y lo iniciamos.
