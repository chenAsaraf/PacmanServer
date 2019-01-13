# WELCOME TO OUR PACMAN GAME

## GENERAL ORIANTATION:
the game works on a server which saves all the games running on every computer.
it saves the id, time, points and the id of the game that was played.

### FOR THE PROGRAMMERS BETWEEN US
### we will describe in general how the code works:
Packmans - robots with mobility, spatial orientations, speed characteristics and eating radius
* their job is to "help" us eat the fruits

Fruits - static objects in the field with different weights.

Blocks - static objects in the field that we cant go through them

Ghosts - robots with mobility, spatial orientations, speed characteristics
* their job is to chase us and damage our points, can go through blocks

Player - our robot with mobility, spatial orientations, speed characteristics and eating radius
* our job is to eat all the fruits and pacmans before the time is up 

ShortestPathAlgo - The main algorithm class uses a greedy algorithm, and search for the fruit that is closest to player at the moment. The solution is represented by a list of Pathes.

Path – a list of Fruits which belongs to the Player in the game. (Can be empty - according to the algorithm).

Map - initializes a generic map file by String 'path' of the image and a maximum left and minimum right coordinate point of the map that defines the map. It has static functions that define the conversion of global coordinates and pixels on and off the image.

GUI Package - Initializes the game on a map board, allows placing individual Player by pressing the mouse and loading Pacmans, Fruits, Blocks and Ghosts from an existing csv file. The center of the game is running animation Player according to the algorithm in real time.

SQL - has all the data of all the games that have been played on this server, saves the id's of the players, the time they played, their scores in each game and the id of the game they played. then it prints in the console after the game is over what is our place in the records table comparing our other games and comparing the other players in this game.

## 
### GPS APP:
Calculates azimuth and distance for any two coordinates,
Converts csv files into GIS collection (elements and layers).

### PACMAN GAME:
the player should choose a csv file to run ( the csv file represent the game) and then insert his ID
![open csv](https://user-images.githubusercontent.com/33022672/51088831-7434c400-176d-11e9-9af7-3dba33ff8326.png)
![insert id](https://user-images.githubusercontent.com/33022672/51088878-f58c5680-176d-11e9-9184-307345147c2d.png)

using the GPS app above getting CSV file of packmans, fruits, ghosts and blocks.
![beginnig](https://user-images.githubusercontent.com/33022672/51088897-34baa780-176e-11e9-9809-b945d4f825ae.png)

then the player draws his "player icon" where ever he wants on the game map.
![adding myself](https://user-images.githubusercontent.com/33022672/51088948-99760200-176e-11e9-967a-7fa9159d4ef0.png)

#### AUTO PLAY
calculates the shortest path to each fruit for the player while the player can eat the other pacmans and needs to avoid the blocks and run faster than the ghosts.
touching a ghost will reduce your points

#### MANUAL PLAY
the player controls the game using the mouse , while he is not clicking it, the game is in "froze" mode

##### END OF THE GAME
the game ends when all the fruits and packmans are eaten OR when the time is up
the scores comparation will be shown in the console 
when the scores are compared between your own games to the last game you played
and between your last game and the other players who played the same game.
