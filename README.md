## APP CONECTADA A LA DATABASE (2 REPOSITORIOS)

### ⚠️NOTA PREVENTIVA PARA TOMAR EN CUENTA


Al ejecutar el proyecto que se guardo comprimido, al descomprimirlo y abrirlo en **Android studio** nos llevaba a un problema de versión por lo cual no dejaba correr el proyecto. A lo cual procedimos a instalar la versión mas reciente de ANDROID STUDIO como resultado se pudo realizar sus debidas modificaciones y añadir nuevas funciones.

### ✅PASO 1: Descargar git 

1. Ubicarnos dentro de usuarios>Nombre_usuario>.AndroidStudio
2. Seleccionamos y presionamos click derecho> git bash here donde ingresaremos el siguiente código

```

git clone https://github.com/FrancoAF10/AppTiendaVehiculos2.git

```
3. Procedemos a abrir el Android studio y abrir el proyecto clonado



### PASO 2: USO DEL XAMPP
1. Abrir el **XAMPP** y hacer correr el **Mysql**

2. Nos Ubicamos dentro de la carpeta XAMPP presionamos clic derecho>git bash here donde ingresaremos el siguiente código

```
git clone https://github.com/FrancoAF10/WSTIENDAVEH.git
``` 
3. Al haber clonado el repositorio procedemos a abrirlo en el Visual Studio code

4. se puede abrir de la misma manera pero con la siguiente línea de código

5. Presionamos clic derecho>git bash here donde ingresaremos el siguiente código

```
code .
``` 

6. Procedemos a instalar las dependencias presionando ctrl+ñ(se abre la terminal)

7. presionamos clic derecho>git bash here donde ingresaremos el siguiente código

```
npm install mysql2 express nodemon dotenv
``` 

8. Luego de ello procedemos a hacer correr el proyecto con el siguiente código
```
nodemon app
``` 
9. Al tener corriendo la terminal, ejecutamos el proyecto de la App y podremos realizar las funciones implementadas: Listar, Agregar, Actualizar, Eliminar y Buscar.