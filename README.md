# Proyecto-POO
link del video: https://youtu.be/Tv8e735fq2Q?si=njn9sHlKB8-o_SyI

INFORME DEL PROYECTO 
Tema: Proyecto de Gestión de Consultorios Médicos.

En este informe se describirá la implementación de un sistema de gestión para un consultorio médica desarrollado en Java, utilizando componentes de Swing para la interfaz gráfica de usuario y MongoDB Compass y Atlas para la base de datos. 
El sistema incluye funcionalidades para el registro y manejo de pacientes, personal médico y generación de reportes estadísticos. Junto a ello el sistema incluye 2 roles las cuales son Administrador y Personal Médico como se lo observa a continuación:
![image](https://github.com/user-attachments/assets/374d90db-81a9-4e96-82f8-2f928ac63dfb)

Estructura del Proyecto
En este proyecto su base fundamental fue la base de datos que al ser creado en la nube para el acceso desde cualquier parte. Esto se tuvo que crear credenciales en la plataforma de MongoDB Atlas para la conexión al proyecto, junto a ello si instalo unas librerías en el proyecto para poder realizar la conexión: 
![image](https://github.com/user-attachments/assets/8c75ea71-11e6-4b15-8dd7-f2da89c1abe6)
![image](https://github.com/user-attachments/assets/6636871a-8daf-4505-81c0-da51d4e448ee)

Entrando en los elementos que posee el proyecto, está organizado en varias clases de Java, cada una encargada de una parte específica del sistema. A continuación se detallan las clases y sus funcionalidades:


Comenzamos con el rol de Administrador 
1. Login Administrador
En este apartado se creó una interfaz gráfica que pida los datos de cédula y contraseña las cuales se almacenan en una variable para la extracción de su contendió en un formato String
![image](https://github.com/user-attachments/assets/07d2c4bb-e29c-4ea3-9723-8d388de5ba0c)

Luego de ser almacenamos realizamos la conexión con la base de datos mediante la sentencia de comandos try-catch y la implementación de las librerías anteriormente mencionadas y toda esta información se almacena en una base de datos llamada POO dentro de una carpeta llamada “usuarios” en donde se almacenarán todos los usuarios del sistema.
![image](https://github.com/user-attachments/assets/fccbf545-c96a-4795-bbcb-8b3dc7c14076)
![image](https://github.com/user-attachments/assets/b0531b05-b203-413a-8494-589f581a9e79)

2. Registro de Pacientes/Personal Médico
En este apartado se lleva a cabo todas las funciones que posee el administrador que son la de gestionar personal médico y pacientes, Registrar a los pacientes en el sistema y la eliminación de los mismos.
Todo esto se realizó con la implementación de comandos de búsqueda que leen la base de datos y extraigan la información para la eliminación o el añadir nuevos médicos/pacientes. Como sabemos la información almacenada en MongoDB son archivos JSON o CSV y se almacenan en un array, por eso para facilitar la búsqueda se trasforma la forma de consulta en un document tipo list que lee todo como un array para buscar en todos los archivos la petición que deseamos hacer.
![image](https://github.com/user-attachments/assets/a8d6c1ff-fd58-4b3a-911f-bb5641a7c5fc)
![image](https://github.com/user-attachments/assets/0e449350-bdce-478c-acad-eb9dcc0de6c1)

3. Visualización de estadísticas
En este apartado se presenta el número de registros de pacientes y tratamientos almacenados en la carpeta “pacientes”.
![image](https://github.com/user-attachments/assets/75c8b6c7-26d4-4eba-b881-0be6b079ad7c)

 Además de que en ciertas partes del código se implementan la presentación de una interfaz con scroll para facilitar el uso del sistema, ya que en muchos apartados se salían de la pantalla e impedía el llenar ciertos campos que no se podían acceder. De esta manera se realiza la conversión del jFRame a un JscrollPanel como se ve a continuación:
 ![image](https://github.com/user-attachments/assets/83833385-9ad2-4534-90e1-f233c85694d6)
Permitiendo que se visualice de la siguiente manera:
![image](https://github.com/user-attachments/assets/ee7abef6-6ab3-459f-8869-34bb37428dfc)



Ahora llegamos al apartado de Personal médico

1. Historial Clínico
En esta parte tras acceder al sistema tras un login con credenciales dadas por el administrador, se presenta el apartado de historial clínico en el que se llena el motivo de consulta, etc. en esta parte se implementa diferentes funciones como la búsqueda de pacientes por medio de su cédula y el añadir campos a la base de datos para completar la información de los pacientes.
![image](https://github.com/user-attachments/assets/cd74028e-f2ea-4ba8-ade8-60b3f7044ba1)

Esta información se almacena de la misma manera que pasaba con los datos de credenciales del personal médico, pero con más parámetros y con la posibilidad de actualizar las veces que sea necesario
![image](https://github.com/user-attachments/assets/5599ff1c-97de-4b7c-9e3b-e01892715e7d)

2. Tratamiento y medicaciones
 Es la misma estructura que el de historial clínico; sin embargo, aquí en el código se pasa por referencia el número de cédula del paciente para que se continúe almacenando la información del paciente correspondiente.
![image](https://github.com/user-attachments/assets/d266b937-0aaf-4196-a57f-b2a23511eadb)
![image](https://github.com/user-attachments/assets/0961f4f4-8dd7-4698-b533-1a0c50d7a2ab)

3. Visualización de exámenes
En esta parte se visualiza todo el registro que se llevó a cabo del paciente y te da la posibilidad de imprimir (función que no está implementada) y de regresarnos.
La búsqueda de esta parte se le hace con la implementación del array que lee todos los documentos y un for para extraer los datos de cada parámetros mientras la cédula coincida con los datos almacenados.
![image](https://github.com/user-attachments/assets/99ab0d39-26ee-4c37-a24d-20661358de1c)

Terminado de visualizarse los resultados en un JTextArea, ya que el JLabel no aguanta mucha información para presentar en pantalla. En esta parte toca configuras el JtextArea para que solo permita visualizar más no editar con los siguientes comandos
![image](https://github.com/user-attachments/assets/0a410c36-e000-4eaa-ba87-0dd008c8c7c5)
 ![image](https://github.com/user-attachments/assets/b0d77dd0-6546-4fd6-a095-d7ae367bd576)

Conclucion
El Proyecto de Gestión de Consultorios Médicos desarrollado en Java con Swing para la interfaz gráfica y MongoDB Compass y Atlas para la base de datos ha implementado con éxito funcionalidades esenciales para la gestión de pacientes y personal médico, así como la generación de reportes estadísticos. Este sistema, estructurado en roles de Administrador y Personal Médico, permite el registro, búsqueda y manejo de información clínica de manera adecuada desde cualquier ubicación. La integración con MongoDB asegura una gestión robusta de los datos, mientras que la interfaz gráfica ofrece una experiencia de usuario interactiva y atractiva.








