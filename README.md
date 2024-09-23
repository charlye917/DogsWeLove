# Dogs We Love App 🐕‍🦺

https://github.com/user-attachments/assets/fd7acbb7-39ef-446b-b7b3-5df1cb242481

## Descripción del Proyecto
![Captura de pantalla 2024-09-22 a la(s) 8 23 23 p m](https://github.com/user-attachments/assets/86b4220c-6e87-42af-8e5f-d008c6723b66)


Este proyecto es una aplicación desarrollada en **Kotlin** utilizando **Jetpack Compose** para la interfaz de usuario, y adopta una arquitectura limpia (Clean Architecture). La app está organizada en varias capas, cada una con su responsabilidad, y utiliza las siguientes tecnologías:

- **Retrofit** para realizar llamadas de red y consumir servicios remotos.
- **Hilt** para la inyección de dependencias.
- **Room** para la persistencia de datos local.
- **Jetpack Compose** para la creación de la interfaz de usuario.
- Se han implementado **tests unitarios** para los casos de uso (`UseCase`) y el `ViewModel` para asegurar la correcta funcionalidad del negocio y la capa de presentación.

## Arquitectura

La aplicación está estructurada en las siguientes capas, según la arquitectura limpia (Clean Architecture):

### 1. **Core**
   - **DI**: Aquí se gestionan las configuraciones de inyección de dependencias mas utilizadas o generales del proyecto utilizando **Hilt**.
   - **Network**: Contiene las configuraciones necesarias para las llamadas de red, incluyendo las instancias de **Retrofit**.

### 2. **DogList**
   Este módulo se encarga de toda la lógica relacionada con la lista de perros.

   #### **Data**
   - **Local**: 
     - **DAO**: Definición de las interfaces para interactuar con la base de datos.
     - **Database**: Configuración de **Room**, la librería utilizada para el almacenamiento local.
     - **Entities**: Definición de las entidades de la base de datos.
   
   - **Remote**:
     - **DataSources**: Llamadas remotas a los servicios de API utilizando **Retrofit**.
     - **Model**: Modelos de datos que representan la estructura de los objetos que se manejan en las llamadas de red.
     - **Service**: Interfaces que definen los endpoints del servicio API.
   
   - **Repository**:
     - **DogsRepository**: Interfaz que define las funciones del repositorio.
     - **DogsRepositoryImpl**: Implementación del repositorio, gestionando la fuente de datos local y remota.

   #### **Domain**
   - **Mapper**: Se encargan de transformar los datos entre modelos (e.g., de `Remote` a `UI`).
   - **Model**: Definición de los modelos que representa la lógica de negocio.
   - **UseCase**: Contiene la lógica de negocio, separando las reglas del dominio de la capa de datos.

   #### **Presentation**
   - **State**: Gestión del estado de la UI usando **State** para representar diferentes estados (cargando, éxito, error).
   - **UI**: Componentes de **Jetpack Compose** que representan la vista de la lista de elementos.
   - **ViewModel**: Gestión de la lógica de presentación y conexión con los casos de uso.

### 3. **UI**
   - **Components**: Contiene componentes reutilizables de UI creados en **Jetpack Compose**.
   - **Theme**: Gestión del tema de la app, incluyendo colores, tipografía y estilos.

### 4. **Utils**
   - Utilidades generales utilizadas a través de la aplicación.

### 5. **MainActivity**
   El punto de entrada de la aplicación que usa **Jetpack Compose** para renderizar la interfaz de usuario.

## Tecnologías y Librerías Usadas

- **Kotlin**: Lenguaje principal del proyecto.
- **Jetpack Compose**: Para la creación de la UI declarativa.
- **Retrofit**: Para las peticiones HTTP.
- **Hilt**: Para la inyección de dependencias.
- **Room**: Para la persistencia de datos en la base de datos local.
- **Coroutines & Flow**: Para la ejecución asíncrona y manejo de flujos de datos.
- **JUnit**: Para los tests unitarios.
- **Mockito**: Para mocks en las pruebas unitarias.

## Testing

Se han implementado **tests unitarios** en las siguientes áreas:
- **ViewModel**: Se han creado pruebas para verificar el comportamiento del `ViewModel` en diferentes estados.
- **UseCase**: Los casos de uso tienen tests que aseguran la correcta ejecución de la lógica de negocio.
