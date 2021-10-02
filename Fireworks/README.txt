Xiurong Yu and Ruolan Lin

To see how fireworks work, please run the the program and enter the velocity, angle, and time based on your preference. In addition, you may select the color and the type of firework you like before launching. 
Note that the default value is set for you, but feel free to change it. 


1. "ﬁrst explosion merely breaks the projectile into several smaller pieces, each of which shoot off in some direction and then explode separately".
	
	This bonus feature are implemented in following types of firework: random, cicle, and wave, and can be shown in the "regular" mode.  
	For the type "random," in order to generate some explosions to the original smaller pieces, we use another for loop for the "random" type of fireworks to get their coordinates after the first explosion and let them draw lines from each of their starting points to
	another random coordinates. 
	For the type "curve," we generated multiple arcs to show multiple explosion. 
	For the type "circle," we use a for loop to generate multiple circles rather than just one. 

	Please choose the “Regular” mode to see the effect. T
	The projectile will explode as normal, with an addition of this bonus feature (select random, curve, or circle type to see the effect).



2. "With some fireworks, the first explosion merely breaks the projectile into several smaller
pieces, each of which shoot off in some direction and then explode separately. Add
support for this type of fireworks. Your trajectory simulations should take account of
the trajectory of the original projectile when determining the trajectories of the child
projectiles."

	To do so, we ﬁred two same type of projectiles towards each other, one from the bottom left and one from the bottom right. 
	If their x coordinates are the same, there will be a circle between them, indicating the third explosion. Note that this rarely happens because the x coordinate must be very accurate. 
	
	Please choose the “addition” mode to see the effect (There will be two projectiles fire toward each other).


3. "Other ideas: document what you did clearly and prominently in your documentation."

	We decided to create multiple fireworks. To do so, we fired 3 different trajectory and fireworks from the first trajectory. 
	There are a total of three modes which are regular, addition, and multiple.  
	
	Please choose the "multiple" mode to see the effect (There will be two projectiles fire toward each other).

