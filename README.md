# Wheel Game
This project is a GUI based game inspired by roulette which is written in Java.<br>
This project is made from the template developed by the Further Programming Teaching Team March - June 2019 at RMIT. <br><br>

In this project, functions as follow are implemented,
  - Unlimited number of player
  - Adding and removing player(s)
  - Ball is spinning around the wheel/ roulette image
  - Player(s) that is involved in the bet will have their result affected
  - Ball will spins automatically once all players input the bets
  
## About ##

### WheelGame ###
WheelGame is the logic of the whole project. It is responsible for adding and removing player, <br>
adding and reducing points, etc. When running this, it will display a log of a hardcoded example <br>
of what the game should do.

### WheelGameGUI ###
WheelGameGUI is the extension of WHeelGame. It is connected to WheelGame and is using it for the logic.<br>
The only difference is that WheelGameGUI are accepting inputs and showing outputs via a Graphical User <br>
Interface (GUI). When running this, it will open a GUI and you will be able to play around with it.

## Installation ##

### Eclipse ###

The java version I used to run is java 12.0.1

#### WheelGame #### 
- Open src in WheelGame
- Right Click on SimpleTestClient
- Click Run as Java Application

#### WheelGameGUI #### 
- Add both folder to eclipse
- Right Click on WheelGameGUI
- Click Properties
- Click Java Build Path
- Click the Projects Tab
- Click Classpath
- Click the Add Button
- Select WheelGame
- Click OK
- Open src in WheelGameGUI
- Right Click on TestClient
- Click Run as Java Application

Developers
---
- [Vincent Pranata](https://github.com/vincent-pranata)

License
----
MIT
