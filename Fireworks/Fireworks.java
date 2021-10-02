//import all existing classes.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Fireworks extends JComponent implements ActionListener {
	//variables for the program
	static double x, y;
	static double x2, y2, x3, y3, x4, y4; //variables for multiple trajectory and fireworks
	static double a;
	protected Timer timer;
	
	//variables for the GUI
	static JTextField angle1, velocity1, time1;
	int angle, velocity, time;
	static JButton button1, button2;
	static JRadioButton jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, jRadioButton5; 
	static Color color;
	static JComboBox box1;
	
	//variables to detect the types of fire work
	protected Boolean standard = false;
	protected Boolean star = false;
	protected Boolean random = false;
	protected Boolean circle = false;
	protected Boolean wave = false;
	
	//extra credit variables
	protected Boolean additional = false;
	protected Boolean multiple = false;
	protected Boolean regular = false;
	
	//the constructor for the class
	public Project3 () {
		timer = new Timer(1000, new TimerCallback());
		timer.start();
	}
	
	public void paintComponent(Graphics g) {	
		//sets color base on user selection
		g.setColor(color);
		//draws the trajectory
		for (double i = 0; i <= time; i+=0.05) {
			x= (velocity*i)*Math.cos(a);
			y= (velocity*i*Math.sin(a))-(0.5*9.8*i*i);
			g.fillOval((int) x,(int)(getHeight() - y) , 4, 4);	
		}
		
		//when addition is selected
		//draws the trajectory for the additional fireworks on the bottom right
		if (additional == true) {
			for (double i = 0; i <= time; i+=0.05) {
				x= (velocity*i)*Math.cos(a);
				y= (velocity*i*Math.sin(a))-(0.5*9.8*i*i);
				g.fillOval((int) (getWidth()-x),(int)(getHeight() - y) , 4, 4);	
			}
		}
		
		//calculate the x and y coordinates based on the velocity and equations.
		x= (velocity*time)*Math.cos(a);
		y= (velocity*time*Math.sin(a))-(0.5*9.8*time*time);
		
		//when multiple is selected
		//multiple: shoots 3 additional trajectory
		if (multiple == true) {
			//middle trajectory
			for (double i = 0; i <= time; i+=0.05) {
				x2 = x + (velocity*i)*Math.cos(a);
				y2 = y + (velocity*i*Math.sin(a))-(0.5*9.8*i*i);
				g.fillOval((int) x2,(int)(getHeight() - y2) , 4, 4);	
			}
			//bottom trajectory
			for (double i = 0; i <= time; i+=0.05) {
				x3 = x + (velocity*i)*Math.cos(a - 0.5);
				y3 = y + (velocity*i*Math.sin(a - 0.5))-(0.5*9.8*i*i);
				g.fillOval((int) x3,(int)(getHeight() - y3) , 4, 4);	
			}
			//upper trajectory
			for (double i = 0; i <= time; i+=0.05) {
				x4 = x + (velocity*i)*Math.cos(a + 0.5);
				y4 = y + (velocity*i*Math.sin(a + 0.5))-(0.5*9.8*i*i);
				g.fillOval((int) x4,(int)(getHeight() - y4) , 4, 4);	
			}
		}
		
		//checks it the colliding x points are exactly equal, if so, it will draw a circle (indicating third explosion) in the middle
		if (additional == true) { 
			if (((int) (x+2)) == ((int) ((getWidth()-(x))))){
				g.drawOval(((int) (x+2) - (velocity/2)), (int)((getHeight() - y)+2 - (velocity/2)), velocity, velocity);
			}
		}
		
		int radius = velocity;
		//fireworks types:
		//standard fireworks
		if (multiple == true && standard == true) {//if user selects both 
			for (int i = 0; i<= 360; i+= 45) {
				g.drawLine((int) (x2+2), (int)((getHeight() - y2)+2), (int) (x2 + 2 + (int) ((radius*Math.cos(Math.toRadians(i))))), (int)((getHeight() - y2)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
				g.drawLine((int) (x3+2), (int)((getHeight() - y3)+2), (int) (x3 + 2 + (int) ((radius*Math.cos(Math.toRadians(i))))), (int)((getHeight() - y3)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
				g.drawLine((int) (x4+2), (int)((getHeight() - y4)+2), (int) (x4 + 2 + (int) ((radius*Math.cos(Math.toRadians(i))))), (int)((getHeight() - y4)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
			}
		}else if (standard == true) { 
			for (int i = 0; i<= 360; i+= 45) {
				g.drawLine((int) (x+2), (int)((getHeight() - y)+2), (int) (x + 2 + (int) ((radius*Math.cos(Math.toRadians(i))))), (int)((getHeight() - y)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
			}	
			
			//additional fireworks on the bottom right
			if (additional == true) { 
				for (int i = 0; i<= 360; i+= 45) {
					g.drawLine((int) ((getWidth()-(x))), (int) ((getHeight() - y)+2), (int) (((getWidth()-(x)) + (radius*Math.cos(Math.toRadians(i))))), (int)((getHeight() - y)+ 2 +((radius*Math.sin( Math.toRadians(i))))));	
				}	
			}
		}
		
		//star fireworks
		if (multiple == true && star == true) {//if user selects both
			for (int i = 0; i<= 360; i+= 72) {
				g.drawLine((int) (x2+2), (int) ((getHeight() - y2)+2), (int) (x2 + 2 + (int)(radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y2)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
				g.fillOval((int) (x2 + 2.5 + (radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y2)+ 2.5 + ((radius*Math.sin( Math.toRadians(i))))), 5, 5);
				g.drawLine((int) (x3+2), (int) ((getHeight() - y3)+2), (int) (x3 + 2 + (int)(radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y3)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
				g.fillOval((int) (x3 + 2.5 + (radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y3)+ 2.5 + ((radius*Math.sin( Math.toRadians(i))))), 5, 5);
				g.drawLine((int) (x4+2), (int) ((getHeight() - y4)+2), (int) (x4 + 2 + (int)(radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y4)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
				g.fillOval((int) (x4 + 2.5 + (radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y4)+ 2.5 + ((radius*Math.sin( Math.toRadians(i))))), 5, 5);
			}	
		}else if (star == true) {
			for (int i = 0; i<= 360; i+= 72) {
				g.drawLine((int) (x+2), (int) ((getHeight() - y)+2), (int) (x + 2 + (int)(radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
				g.fillOval((int) (x + 2.5 + (radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2.5 + ((radius*Math.sin( Math.toRadians(i))))), 5, 5);
			}	
			//additional fireworks on the bottom right
			if (additional == true) {
				for (int i = 0; i<= 360; i+= 72) {
					g.drawLine((int) ((getWidth()-(x))+2), (int) ((getHeight() - y)+2), (int) ((getWidth()-(x)) + 2 + (int)(radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2 + ((radius*Math.sin( Math.toRadians(i))))));
					g.fillOval((int) ((getWidth()-(x)) + 2.5 + (radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2.5 + ((radius*Math.sin( Math.toRadians(i))))), 5, 5);
				}
			}	
		}
		
		//random fireworks
		if (multiple == true && random == true) {//if user selects both
			Random r = new Random();
			for (int i = 0; i< 360; i += 36) {
				int random_radius = r.nextInt(velocity);
				g.drawLine((int) (x2+2), (int) ((getHeight() - y2)+2), (int) (x2 + 2 + (random_radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y2)+ 2 + ((random_radius*Math.sin( Math.toRadians(i))))));
				g.drawLine((int) (x3+2), (int) ((getHeight() - y3)+2), (int) (x3 + 2 + (random_radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y3)+ 2 + ((random_radius*Math.sin( Math.toRadians(i))))));
				g.drawLine((int) (x4+2), (int) ((getHeight() - y4)+2), (int) (x4 + 2 + (random_radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y4)+ 2 + ((random_radius*Math.sin( Math.toRadians(i))))));
			}
			for (int i = 0; i< 360; i += 45) {
				int random_radius2 = r.nextInt(20);
				g.drawLine((int) (x2+2), (int) ((getHeight() - y2)+2), (int) (x2 + 2 + (random_radius2*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y2)+ 2 + ((random_radius2*Math.sin( Math.toRadians(i))))));
				g.drawLine((int) (x3+2), (int) ((getHeight() - y3)+2), (int) (x3 + 2 + (random_radius2*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y3)+ 2 + ((random_radius2*Math.sin( Math.toRadians(i))))));
				g.drawLine((int) (x4+2), (int) ((getHeight() - y4)+2), (int) (x4 + 2 + (random_radius2*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y4)+ 2 + ((random_radius2*Math.sin( Math.toRadians(i))))));
			}
		}else if (random == true && additional == true) { 
			Random r = new Random();
			for (int i = 0; i< 360; i += 36) {
				int random_radius = r.nextInt(velocity);
				g.drawLine((int) (x+2), (int) ((getHeight() - y)+2), (int) (x + 2 + (random_radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2 + ((random_radius*Math.sin( Math.toRadians(i))))));
			}
			
			if (additional == true) {
				for (int i = 0; i< 360; i += 36) {
					int random_radius = r.nextInt(velocity);
					g.drawLine((int) ((getWidth()-(x))+2), (int) ((getHeight() - y)+2), (int) ((getWidth()-(x)) + 2 + (random_radius*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2 + ((random_radius*Math.sin( Math.toRadians(i))))));
				}
				for (int i = 0; i< 360; i += 45) {
					int random_radius2 = r.nextInt(20);
					g.drawLine((int) ((getWidth()-(x))+2), (int) ((getHeight() - y)+2), (int) ((getWidth()-(x)) + 2 + (random_radius2*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2 + ((random_radius2*Math.sin( Math.toRadians(i))))));
				}
			}
		}else if (regular = true && random == true) {
			Random r = new Random();
			for (int i=0;i<360;i+=36) {
				int random_radius = r.nextInt(velocity);
				int xc=(int) (x + 2 * (random_radius*Math.cos(Math.toRadians(i))));
				int yc=(int)((getHeight() - y)+ 2 *((random_radius*Math.sin( Math.toRadians(i)))));
				for(int j=0;j<360;j+=10) {
					g.drawLine(xc, yc, (int)(xc+2 * (random_radius*Math.cos(Math.toRadians(j)))),(int) (yc+2 *((random_radius*Math.sin( Math.toRadians(j))))));
				}
			}
			
			for (int i = 0; i< 360; i += 45) {
				int random_radius2 = r.nextInt(20);
				g.drawLine((int) (x+2), (int) ((getHeight() - y)+2), (int) (x + 2 + (random_radius2*Math.cos(Math.toRadians(i)))), (int)((getHeight() - y)+ 2 + ((random_radius2*Math.sin( Math.toRadians(i))))));
			}
		} 
		
		//circle fireworks
		if (multiple == true && circle == true) {//if user selects both
			for (double i = 0; i< velocity*2; i += velocity/3) {
				double w = 10 + i;
				double h = 10 + i;
				g.drawOval((int) (x2 + 2 - (w/2)), (int)((getHeight() - y2) + 2-(h/2)), (int)(w), (int)(h));
				g.drawOval((int) (x3 + 2 - (w/2)), (int)((getHeight() - y3) + 2-(h/2)), (int)(w), (int)(h));
				g.drawOval((int) (x4 + 2 - (w/2)), (int)((getHeight() - y4) + 2-(h/2)), (int)(w), (int)(h));
			}
		}else if (circle == true) { 
			for (double i = 0; i< velocity*2; i += velocity/3) {
				double w = 10 + i;
				double h = 10 + i;
				g.drawOval((int) (x + 2 - (w/2)), (int)((getHeight() - y) + 2-(h/2)), (int)(w), (int)(h));
			}
			//additional fireworks on the bottom right
			if (additional == true) {
				for (double i = 0; i< velocity*2; i += velocity/3) {
					double w = 10 + i;
					double h = 10 + i;
					g.drawOval((int) ((getWidth()-(x)) + 2 - (w/2)), (int)((getHeight() - y) + 2-(h/2)), (int)(w), (int)(h));
				}
			}
		}
		//wave fireworks
		if (multiple == true && wave == true) {//if user selects both
			for(double i=0;i<10;i+=1 ){
				g.drawArc((int)(x2+i*velocity/2), (int)(getHeight()-y2-velocity/2), velocity, velocity, 0, 180);
				g.drawArc((int)(x3+i*velocity/2), (int)(getHeight()-y3-velocity/2), velocity, velocity, 0, 180);
				g.drawArc((int)(x4+i*velocity/2), (int)(getHeight()-y4-velocity/2), velocity, velocity, 0, 180);
			}
		}else if (wave==true) {
			for(double i=0;i<10;i+=1 ){
				g.drawArc((int)(x+i*velocity/2), (int)(getHeight()-y-velocity/2), velocity, velocity, 0, 180);
			}
			//additional fireworks on the bottom right
			if (additional == true) {
				for(double i=0;i<10;i+=1 ){
					g.drawArc((int)((getWidth()-(1.34*x))-i*velocity/2), (int)(getHeight()-y-velocity/2), velocity, velocity, 0, 180);
				}
			}
		}
	}
	
	protected class TimerCallback implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			repaint();
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Fireworks");
		frame.setLayout(new BorderLayout());
		Project3 canvas = new Project3();
		
		//create button and set it as action listener
		button1 = new JButton("Launch");
		button1.addActionListener(canvas);
		button2 = new JButton("Color");
		button2.addActionListener(canvas);
		
		//create user text field
		angle1 = new JTextField(5);
		velocity1 = new JTextField(5);
		time1 = new JTextField(5);
		
		//buttons for types of fireworks
		ButtonGroup bg = new ButtonGroup();
		jRadioButton1 = new JRadioButton(); 
		bg.add(jRadioButton1);
		jRadioButton2 = new JRadioButton(); 
		bg.add(jRadioButton2);
		jRadioButton3 = new JRadioButton(); 
		bg.add(jRadioButton3);
		jRadioButton4 = new JRadioButton(); 
		bg.add(jRadioButton4);
		jRadioButton5 = new JRadioButton(); 
		bg.add(jRadioButton5);
		jRadioButton1.setText("Standard");
		jRadioButton2.setText("Star");
		jRadioButton3.setText("Random");
		jRadioButton4.setText("Circle");
		jRadioButton5.setText("Wave");
		
		JLabel label1=new JLabel("Angle");
		JLabel label2 =new JLabel("Velocity");
		JLabel label3=new JLabel("Time");
		
		//Jcombobox
		String strings[] = {"regular", "additional", "multiple"}; 
		
		// create checkbox 
        box1 = new JComboBox(strings); 
        box1.addActionListener(canvas);
		
		// create a panel to add buttons, text field, radio buttons, and combobox
		JPanel p = new JPanel();
		p.add(button1);
		p.add(button2);
		p.add(label1);
		p.add(angle1);
		p.add(label2);
		p.add(velocity1);
		p.add(label3);
		p.add(time1);
		
		p.add(jRadioButton1);
		p.add(jRadioButton2);
		p.add(jRadioButton3);
		p.add(jRadioButton4);
		p.add(jRadioButton5);
		
		p.add(box1);
	
		//set default; for debugging
		angle1.setText("55");
		velocity1.setText("55");
		time1.setText("5");
		jRadioButton1.doClick();
		
		// layout
		frame.add(p, BorderLayout.NORTH);
		frame.add(canvas, BorderLayout.CENTER);
		frame.setSize(1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

//	when the button is clicked
	public void actionPerformed(ActionEvent e) { 
		//when clicked on launch
		
		if (e.getSource() == button1) {
			//extra credit/ more fireworks options
			if (box1.getSelectedItem().equals("regular")) { 
				regular = true;
				additional = false;
				multiple = false;
			}else if (box1.getSelectedItem().equals("additional")) { 
				additional = true;
				multiple = false;
				regular = false;
		    } else if (box1.getSelectedItem().equals("multiple")) {
		    	additional = false;
		    	multiple = true;
		    	regular = false;
		    }
			
			//types of fireworks 
			if (jRadioButton1.isSelected()) { //standard
				standard = true;
				star = false;
				random = false;
				circle = false;
				wave=false;
	        }else if (jRadioButton2.isSelected()) { //star
	        	standard = false;
	        	star = true;
	        	random = false;
	        	circle = false;
	        	wave=false;
	        }else if (jRadioButton3.isSelected()) { //random
	        	standard = false;
	        	star = false;
	        	random = true;
	        	circle = false;
	        	wave = false;
	        }else if (jRadioButton4.isSelected()) { //circle
	        	standard = false;
	        	star = false;
	        	random = false;
	        	circle = true;
	        	wave = false;
	        }else if (jRadioButton5.isSelected()) {//wave
	        	standard = false;
	        	star = false;
	        	random = false;
	        	circle = false;
	        	wave = true;
	        }   
	        else { 
	           System.out.println("none");
	        } 
			
			angle = Integer.parseInt(angle1.getText());
			velocity = Integer.parseInt(velocity1.getText());
			time = Integer.parseInt(time1.getText());
			a= Math.toRadians(angle);
			
			timer.restart();
			this.repaint();
		}
		
		//when clicked on color button
		if  (e.getSource() == button2) {
			Color initialcolor=Color.BLACK;    
			color=JColorChooser.showDialog(this,"Select a color",initialcolor); 
		} 			
	}
}
