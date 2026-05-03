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

## Project Structure

```text
src/
├── UmlFile.java
├── ImageGenerator.java
├── UmlManager.java
└── UmlTool.java
