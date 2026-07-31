# Nombre del Proyecto

Este proyecto tiene la finalidad afianzar los conocimiento de programacion, pero ahora enfocados a Java,
tambien adentrarse en la programación orientada a objetos y entender cada uno de sus principios.

## 📋 Requisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java JDK 21 o superior
- Apache Maven 3.9 o superior
- Git (opcional)

Verifica las versiones:

```bash
java -version
mvn -version
```

## 🚀 Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/Danilo-Doria/CorporateTalentHub-HU.git
```

2. Entra al directorio del proyecto:

```bash
cd CorporateTalentHub-HU
```

3. Compila el proyecto:

```bash
mvn clean install
```

## ▶️ Ejecución

Ejecuta la aplicación con:

```bash
mvn exec:java
```

> Si el proyecto no utiliza el plugin `exec-maven-plugin`, también puedes ejecutar la clase principal desde tu IDE (NetBeans, IntelliJ IDEA o Eclipse).

## 📂 Estructura del proyecto

```
.
├── pom.xml
├── README.md
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── mycompany
│   │               └── corporatetalenthub
│   │                   ├── App.java
│   │                   ├── documentation
│   │                   │   └── NotasArquitectura.java
│   │                   └── modelo
│   │                       ├── Empleado.java
│   │                       └── EmpresaRecord.java
│   └── test
│       └── java
└── target
    ├── generated-sources
    └── maven-status
```

## 🧪 Pruebas

Para ejecutar las pruebas:

```bash
mvn test
```

## 🛠️ Tecnologías utilizadas

- Java
- Maven
- JUnit (si aplica)

## 📦 Compilación

Genera el archivo JAR:

```bash
mvn package
```

El archivo generado se encontrará en la carpeta:

```
target/
```

## 👨‍💻 Autor

Danilo-Doria

