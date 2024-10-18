**Console Board Games Application**

**Overview**

This project is designed to handle multiple board games in a console-based application. Currently, it includes classic games like Snakes and Ladders, and more games can easily be added in the future. The project provides a fun, interactive way to enjoy these games directly from the command line, making it simple and lightweight without any graphical user interface.

**Features**

	•	Play Snakes and Ladders and other classic board games.
	•	Simple console-based interface with easy-to-follow instructions.
	•	Extendable architecture to add more games in the future.
	•	Uses JSON configuration files to set up game boards and rules.

**How to Run**

	1.	Clone or Download the Project:
	  •	Clone the repository from GitHub or download it as a ZIP file and extract it.
	2.	Run the Application:
	  •	Navigate to the src/main/java directory and locate the Main.java file.
	  •	Run the Main.java file using your preferred Java IDE or from the command line.

  javac Main.java
  java Main

  3.	Follow the Instructions:
	  •	Once the application starts, follow the on-screen prompts to select and play the available board games.

**Prerequisites**

	•	Java 11+: Ensure you have JDK 11 or a higher version installed.
	•	Maven (optional): If you want to build the project using Maven, you can use the provided pom.xml file.

**Project Structure**

	•	src/main/java: Contains the main application code.
	•	resources/snakenladders: Contains configuration files for board games like Snakes and Ladders.
	•	pom.xml: Maven build file for managing dependencies.

**Future Improvements**

	•	Add more board games to the collection.
	•	Add an option for custom game configurations.
