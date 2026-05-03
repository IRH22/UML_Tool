# UML Diagram Generator

A Java-based console application that generates UML class diagrams from English relationship statements using PlantUML.

This project was developed as an Object-Oriented Programming assignment using Java and the PlantUML API.

## Features

- Create UML class relationship diagrams
- Support for multiple UML relationship types:
  - Inheritance (`is a`)
  - Composition (`composed of`)
  - Aggregation (`has a`)
  - Dependency (`uses`)
  - Weak Dependency (`depends on`)
- Read and write PlantUML (`.pu`) files
- Export UML diagrams as:
  - PNG
  - SVG
- Menu-driven console interface

## Technologies Used

- Java
- PlantUML
- Eclipse IDE

## Project Structure

```text
src/
├── UmlFile.java
├── ImageGenerator.java
├── UmlManager.java
└── UmlTool.java
```

## Required Dependency

This project requires the PlantUML library.

Download the PlantUML `.jar` file from:

https://plantuml.com/download

## Sample Files

- `sample.pu` → Example PlantUML file
- `sample.png` → Generated PNG diagram
- `sample.svg` → Generated SVG diagram

## Author

Ingrid Rangel Hernandez
