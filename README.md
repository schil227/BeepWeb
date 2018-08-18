# BeepWeb
Remotely controlling Raspberry Pi via web interface.

Required software:
	Maven: Project managing software for java, similar to NuGet. This will handle fetching the PI4J snapshot
		*PI4J: Libary for Java to interact with Raspberry Pi	
	WiringPi: Framework required by PI4J, must be installed manually on the PI

Commands:
	To copy files from working machine to the Raspberry Pi:
		sudo scp -r beepbeep/ pi@<Pi_IP>:/home/pi/beepWeb/
	Where the IP address is the current IP for Raspberry Pi 

	To build and run the software, navigate to the directory that has the .pom file (beepbeep/) and run the following:
		mvn package && java -jar target/beepbeep-0.0.1-SNAPSHOT.jar
	To just run the software:
		java -jar target/beepbeep-0.0.1-SNAPSHOT.jar

	To send commands via terminal to Raspberry Pi:
		curl <Pi_IP>:8080/status
	Where the IP address is the current IP for the Raspberry Pi
