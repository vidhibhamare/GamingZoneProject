import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
class Graphics //for graphics 
	extends JPanel //Container that can store a group of components
	implements ActionListener{  //Implementing keywords
      private Timer t = new Timer(100,this);   //100 milisecond
      public String state;
      private Snake s;
      private Food f;
      private Game game;
      public Graphics(Game g) {  //Public Constructor 
    	  t.start();
    	  state = "START";
    	  game=g;
    	  s= g.getPlayer();
    	  f= g.getFood();
    	  
    	  this.addKeyListener(g);
    	  this.setFocusable(true);
    	  this.setFocusTraversalKeysEnabled(false);
      }
      public void paintComponent(java.awt.Graphics g) {
    	  super.paintComponent(g);
 
    	  Graphics2D g2d = (Graphics2D) g;
    	  g2d.setColor(Color.decode("#06283D"));  //Background colour
    	  g2d.fillRect(0,0, Game.width * Game.dimension+5, Game.height * Game.dimension+5);
    	  
    	  if(state== "START") {
    		  g2d.setColor(Color.white);
    		  g2d.drawString("WELCOME!!",Game.width/2*Game.dimension-75,Game.height/2*Game.dimension - 20);
    		  g2d.drawString("Press any key to play",Game.width/2*Game.dimension-75,Game.height/2*Game.dimension - 1);
    	  }
    	  else if (state== "Running") {
    	 
    	  g2d.setColor(Color.decode("#BF9270"));  //Food colour
    	  g2d.fillRect(f.getX()*Game.dimension,f.getY()*Game.dimension,Game.dimension,Game.dimension);
    	  
    	  g2d.setColor(Color.decode("#00E7FF"));  //Snake colour
    	  for(Rectangle r : s.getBody()) {
    		  g2d.fill(r);
    	  }}
    	  else{
    		  g2d.setColor(Color.white);
    		  g2d.drawString("CONGRATULATIONS!!",Game.width/2*Game.dimension-75,Game.height/2*Game.dimension - 20);
    		  g2d.drawString("Final Score: "+(s.getBody().size()-3),Game.width/2*Game.dimension-75,Game.height/2*Game.dimension - 1);
    	  }
      }
      
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
}
class Food {   //Class for drawing the Food at random location
   private int x;
   private int y;
   
   public Food(Snake player) {
	   this.random_spawn(player);
   }

public void random_spawn(Snake player) {
   
	   boolean onSnake = true;
	   while(onSnake) {
		   onSnake = false;
		   x =(int)( Math.random() * Game.width - 1); 
		   y =(int)( Math.random() * Game.height - 1); 
		   for(Rectangle r : player.getBody()) {
			   if(r.x == x && r.y==y ) {
				   onSnake = true;
			   }
		   }		   
	   }
   }
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
}

class Snake {   //Snake Class for movement and growing of Snake
   private ArrayList<Rectangle> body;
   private String move;
   public Snake() {   //Start size of the snake
	   body= new ArrayList<>() ;
		   Rectangle temp= new Rectangle(Game.dimension, Game.dimension);
		   temp.setLocation(Game.width/2* Game.dimension, Game.height/2*Game.dimension);
	       body.add(temp);
	       
	       temp =  new Rectangle(Game.dimension, Game.dimension);
	       temp.setLocation((Game.width/2-1)* Game.dimension, (Game.height/2)*Game.dimension);
	       body.add(temp);
	       
	       temp =  new Rectangle(Game.dimension, Game.dimension);
	       temp.setLocation((Game.width/2-2)* Game.dimension, (Game.height/2)*Game.dimension);
	       body.add(temp);
	       
	       move="Null";
	        
   }
   public void move() {  //Queue data structure used for the ENQUEUE and DEQUEUE of the body
	   if (move != "Null") {
		   Rectangle first = body.get(0);
		   Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
		   
		   if(move == "UP") {
			   temp.setLocation(first.x, first.y- Game.dimension); //up
		   }
		   else if(move == "DOWN") {
			   temp.setLocation(first.x, first.y+ Game.dimension);  //down
		   }
		   else if(move == "LEFT") {
			   temp.setLocation(first.x- Game.dimension, first.y);  //left
		   }
		   else  {
			   temp.setLocation(first.x+Game.dimension, first.y);  //right
		   }
		   body.add(0,temp);  //Add a new rectangle to the body of the snake in arraylist
		   body.remove(body.size()-1);  //delete the last body in arraylist
	   }
   }
   public void grow() {   //for the snake to grow (ENQUEUE)
	   Rectangle first = body.get(0);
	   Rectangle temp = new Rectangle(Game.dimension, Game.dimension);
	   
	   if(move == "UP") {
		   temp.setLocation(first.x, first.y- Game.dimension); //up
	   }
	   else if(move == "DOWN") {
		   temp.setLocation(first.x, first.y+ Game.dimension);  //down
	   }
	   else if(move == "LEFT") {
		   temp.setLocation(first.x- Game.dimension, first.y); //left
	   }
	   else  {
		   temp.setLocation(first.x+Game.dimension, first.y);  //right
	   }
	   body.add(0,temp);  //Adding  a small rectangle at the front
   }

public ArrayList<Rectangle> getBody() {
	return body;
}
public void setBody(ArrayList<Rectangle> body) {
	this.body = body;
}
public int getX() {
	return body.get(0).x;
}
public int getY() {
	return body.get(0).y; 
}
public String getMove() {
	return move;
}
public void up() {
	if(move != "DOWN") {
	move="UP";
}}
public void down() {
	if(move!="UP") {
	move="DOWN";
}}
public void left() {
	if(move!= "RIGHT") {
	move="LEFT";
}}
public void right() {
	if(move!="LEFT") {
	move= "RIGHT";
}}
}
class Game 
	implements KeyListener{
   private Snake player;
   private Food food;
   private Graphics graphics;
   private JFrame window;
   public static final int width = 30;
   public static final int height= 30;
   public static final int dimension =20;
   public Game() {
	   window= new JFrame();
	   player = new Snake();
	   food = new Food(player);
	   graphics = new Graphics(this);
	   window.add(graphics);
	   window.setTitle("Snake Game");
	   window.setSize(width*dimension +2,height* dimension +dimension +4);
	   window.setVisible(true);
	   //window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //Exits the application when the user closes the window
   }
   public void start() {
	   graphics.state = "Running";
   }
   public void update() {  //Update when game is running
	  if( graphics.state == "Running") {
		  if(check_foodcollision()) {
			  player.grow();
			  food.random_spawn(player);
		  }
		  else if(check_wallcollision() || check_selfcollision()) {
			  graphics.state = "End";
		  }
		  else {
			  player.move();
		  }
		  
	  }
   }
   public boolean check_wallcollision() {  // If the snake moves outside the Frame
	   if(player.getX()<0 || player.getX() >= width * dimension || player.getY()<0 || player.getY()>= height* dimension) {
		   return true;
	   }
	   return false;
   }
   public boolean check_foodcollision() {   //Snake Collides with the food
	   if(player.getX() == food.getX() * dimension && player.getY() == food.getY()* dimension) {
		   return true;
	   
	   }
	   return false;
   }
   public boolean check_selfcollision() { //snake touches its own body
	   for(int i=1; i<player.getBody().size();i++) { //arraylist
		   if(player.getX()== player.getBody().get(i).x && player.getY()== player.getBody().get(i).y) {
			   return true;
		   }
	   }
	   return false;
   }
@Override
public void keyTyped(KeyEvent e) {

	}
@Override
public void keyPressed(KeyEvent e) {
	int key= e.getKeyCode();
	if(graphics.state == "Running") {
	if(key==KeyEvent.VK_W && player.getMove()!="DOWN") {
		player.up();
	}
	if(key==KeyEvent.VK_A && player.getMove()!="RIGHT") {
		player.left();
	}
	if(key==KeyEvent.VK_D && player.getMove()!= "LEFT") {
	    player.right();
}
	if(key==KeyEvent.VK_S && player.getMove()!="UP") {
	    player.down();
}
	}
	else {
		this.start();
	}	
}
public Snake getPlayer() {
	return player;
}
public void setPlayer(Snake player) {
	this.player = player;;
}
public Food getFood() {
	return food;
}
public void setFood(Food food) {
	this.food = food;
}
public JFrame getWindow() {
	return window;
}
public void setWindow(JFrame window) {
	this.window = window  ;
}
@Override
public void keyReleased(KeyEvent e) {	
}		   
}
//Treasure hunt
//this class will store the clue and ans of a team 
class nodeTeam {
    char team;
    String clue;
    String clue_ans;
    nodeTeam next;
    public nodeTeam(char t, String c, String cs){
        team=t;
        clue=c;
        clue_ans=cs;
    }
}
class linkedlist {
    nodeTeam head;
}
class treasureHuntGame{
    static Scanner sc=new Scanner(System.in);
    static int incorrectA=0;
    static int Ateam_points=0;
    static int incorrectB=0;
    static int Bteam_points=0;
    static Boolean treasure_found=false;
    static String ansA;
    static String ansB;
		//this function is called when it is Team A's turn
    static void teamA(nodeTeam ptrA,nodeTeam ptrB){
        Boolean flag1=true;
				//giving clues until the team answers incorrectly
        while(flag1==true){
        System.out.println("\n★ TEAM A ★");
        System.out.println(ptrA.clue+"\nEnter your answer: ");
        ansA=sc.nextLine();
        if(ansA.equalsIgnoreCase(ptrA.clue_ans)){
            System.out.println("Correct answer!!! you are moving to the next clue ");
            flag1=true;
            Ateam_points=Ateam_points+100;
						//as next node is null it means that treasure is found
            if(ptrA.next==null){
                treasure_found=true;
                System.out.println("\nWOHOOOO TREASURE FOUND BY ★ TEAM A ★");
                return;
            }
            ptrA=ptrA.next;
        }
        else{
            System.out.println("OOPS INCORRECT ANSWER try again in next turn :(");
            incorrectA=incorrectA+10;
            flag1=false;
        }
    }
    if(flag1==false){
			//chance given to team B now as team A has answered correctly
        teamB(ptrB,ptrA);
    }
    }
		//this function is called when it is Team B's turn
    static void teamB(nodeTeam ptrB,nodeTeam ptrA){
        Boolean flag2=true;
				//giving clues until the team answers incorrectly
        while(flag2==true){
            System.out.println("\n✿ TEAM B ✿");
            System.out.println(ptrB.clue+"\nEnter your answer: ");
            ansB=sc.nextLine();
            if(ansB.equalsIgnoreCase(ptrB.clue_ans)){
                System.out.println("Correct answer!!! you are moving to the next clue ");
                flag2=true;
                Bteam_points=Bteam_points+100;
                if(ptrB.next==null){
									//as next node is null it means that treasure is found
                    treasure_found=true;
                    System.out.println("\nWOHOOOO TREASURE FOUND BY ✿ TEAM B ✿ ");
                    return;
                }
                ptrB=ptrB.next;
            }
            else{
                System.out.println("OOPS INCORRECT ANSWER try again in next turn :(");
                incorrectB=incorrectB+10;
                flag2=false;
            }
        }
        if(flag2==false){
					//chance given to team A now as team B has answered correctly
            teamA(ptrA,ptrB);
        }
    }
		//function to play treasure hunt game
    void playGame() throws InterruptedException {
				//creating two linked lists for both the teams which merge at the ending clue
        linkedlist list_teamA=new linkedlist();
        linkedlist list_teamB=new linkedlist();
				//making head node of clues and hint for team A
        nodeTeam node1A=new nodeTeam('A',"Clue1: \nThe more you code, the more in me.\n" +
                "Coders want to get rid of me.\n" +
                "I may be gone for now but you can’t get rid of me forever.\n" +
                "I am also an insect.\n","bug");
        list_teamA.head=node1A;
        nodeTeam node2A=new nodeTeam('A',"Clue2: I am a data structure.\nI use the method of First In First Out","queue");
        node1A.next=node2A;//linking the next clues to the linked list of team A
        nodeTeam node3A=new nodeTeam('A',"Clue3: I am an artificial and informal language that helps programmers develop algorithms.\nI do not require any strict programming language syntax","pseudocode");
        node2A.next=node3A;
				//making head node of clues and hint for team B
        nodeTeam node1B=new nodeTeam('B',"Clue1: \nI am your eyes as a developer.\n" +
                "I am used to show results of a code.\n" +
                "I am a statement.\n","print");
        list_teamB.head=node1B;
        nodeTeam node2B=new nodeTeam('B',"Clue2: I am a data structure.\nI use the method of Last In First Out","stack");
        node1B.next=node2B;//linking the next clues to the linked list of team B
        nodeTeam node3B=new nodeTeam('B',"Clue3: I am a set of well-defined instructions to solve a particular problem.\nI am also called the recipe of a code.","algorithm");
        node2B.next=node3B;

				//common last clue for both the teams
        nodeTeam nodefinal=new nodeTeam('N',"Final Clue: I am  number, text string, or symbol that never changes value while a program is running.\nI always stay the same throughout the program.","constant");
        //linking last nodes of the both team lists to the final clue node
				node3A.next=nodefinal;
        node3B.next=nodefinal;
        nodefinal.next=null;

        System.out.println("\n**************************************WELCOME TO THE TREASURE HUNT**************************************");
        System.out.println("\nKindly read the rules before getting started:\n1.Each team will be getting 4 clues.\n2.The final clue will be the same for both the team.\n3.If you get the clue correct then you will move to the next clue.\n4.If you get the clue incorrectly, then turn will be passes to the other team.\n5.You will get 100 points fro each correct answer.\n6.You will lose 10 points for every incorrect answer attempt.");
        System.out.println("\n GOOD LUCK");
        nodeTeam ptrA=list_teamA.head;
        nodeTeam ptrB=list_teamB.head;
				//making a toss to decide which team goes first
        System.out.println("\nLets do a toss to select which team goes first.");
        System.out.println("Press 1 to start the toss.");
        sc.nextLine();
        Thread.sleep(2000);
        char c;
				//using random function to make a toss
        if (Math.random() < 0.5){
            c='A';
            System.out.println("★ Team A ★ has won the toss. Team A goes first");
						//countdown of three secs to start the game
					  System.out.println("\nWe will begin the game in ");
            Thread.sleep(1000);
            System.out.print("3");
            Thread.sleep(1000);
            System.out.print(" 2");
            Thread.sleep(1000);
            System.out.print(" 1\n");
            Thread.sleep(1000);
        }else{
            c='B';
            System.out.println("✿ Team B ✿ has won the toss. Team B goes first");
            System.out.println("\nWe will begin the game in ");
            Thread.sleep(1000);
            System.out.print("3");
            Thread.sleep(1000);
            System.out.print(" 2");
            Thread.sleep(1000);
            System.out.print(" 1\n");
            Thread.sleep(1000);
        }
            if(c=='A') {
                teamA(ptrA, ptrB);
            }
            else{
                teamB(ptrB,ptrA);
            }
            System.out.println("\nTEAM A FINAL POINTS: "+(Ateam_points-incorrectA));
        System.out.println("TEAM B FINAL POINTS: "+(Bteam_points-incorrectB));
        Thread.sleep(3000);
    }
}

//Minesweeper
class Details_node{//class contianing details of node
	int adjacent;
	Details_node left;
	Details_node right;
	boolean mine;
	Details_node(boolean mine,int adjacent){//parameterized constructor
		this.mine=mine;
		this.adjacent=adjacent;
	}
}
class binaryTree{//class to create tree
	Random rand=new Random();//random function
	Details_node root,temp;
	int i;
	void addDetails(Details_node n_n) {//function to add details for the left and right child of a node
		if(i>=10) {//10 levels
			n_n.left=new Details_node(addMineLeft(n_n),2);
			n_n.right=new Details_node(addMineRight(n_n),2);
		}
		else {
			n_n.left=new Details_node(addMineLeft(n_n),rand.nextInt(2));
			n_n.right=new Details_node(addMineRight(n_n),rand.nextInt(2));
		}
	}
	boolean addMineLeft(Details_node n_n) {//function to add adjacent mines for left child
		if(n_n.adjacent==0)
			return false;
		else
			return(rand.nextBoolean());
	}
	boolean addMineRight(Details_node n_n) {//function to add adjacent mines for right child
		if(n_n.adjacent==1 && n_n.left.mine!=true)
			return true;
		else if(n_n.adjacent==1 && n_n.left.mine!=false)
			return false;
		else
			return false;
	}
	void Create() {//function to create perfect binary tree of 10 levels
		Queue<Details_node> queue =new LinkedList<Details_node>();//queue data structure from collection of framework
		root=new Details_node(false,rand.nextInt(2));
		queue.add(root);//adding root node
		i=1;
		while(queue.size()<257) {//max queue size @level 10
			i++;
			if(i>11)//10 levels
				break;
			else {
				int size=queue.size();
				for(int j=0;j<size;j++) {
					Details_node n_n=queue.poll();//poping from queue
					if(queue.size()!=256) {
						addDetails(n_n);//calling function to add details
						queue.add(n_n.left);//pushing in queue
						queue.add(n_n.right);
					}
				}
			}
		}
	}
}
class play{//class to play game
	Scanner sc=new Scanner(System.in);
	int total=0;
	boolean flag;
	void Start(Details_node temp) throws InterruptedException{//function to play the game
		Thread.sleep(350);
		int adjacentMines=temp.adjacent;
		boolean Mine=temp.mine;
		if(!Mine) {//if mine not present
			if(total==80)
				total+=20;//last round provides 20 points
			else
				total+=10;
			System.out.println("Safe!");
			if(adjacentMines!=2) {
				System.out.println("Number of adjacent mines is "+adjacentMines);
				System.out.println("Which way would you like to go?: (L for left, R for right)");
				char direction=sc.nextLine().charAt(0);//inputing direction
				if(direction=='L' || direction=='l')
					Start(temp.left);//recursively calling the function
				else if(direction=='R' || direction=='r')
					Start(temp.right);//recursively calling the function
				else {
					System.out.println("Wrong input of choice! Let's go again");
					total-=10;//updating total
					Start(temp);
				}
			}
			else {//completed all levels
				System.out.println("YOU COMPLETED THE GAME!\nWELL PLAYED!!");
				System.out.println("Your total score is "+total);
			}
		}
		else {//if mine is present
			System.out.println("*\nOOPS! Mine found!");
			System.out.println("Total score: "+total);
			flag=true;
		}
	}
}
class Minesweeper {//main class for minesweeper game
	binaryTree BTobj=new binaryTree();
	Scanner sc=new Scanner(System.in);
	char choice='y';
	void playGame() throws InterruptedException {
		do {
			play Pobj=new play();
			System.out.println("\n**********WELCOME TO MINESWEEPER GAME**********");
			BTobj.Create();//calling function to create the tree
			System.out.println("Be ready... The game begins in..");
			Thread.sleep(1000);
			System.out.print("3 ");
			Thread.sleep(500);
			System.out.print("2 ");
			Thread.sleep(500);
			System.out.println("1");
			System.out.println("According the number of adjacent mines displayed choose your way wisely :)");
			System.out.println("GOOD LUCK!!!");
			Thread.sleep(200);
			System.out.println("\nNumber of adjacent mines is "+BTobj.root.adjacent);
			System.out.println("Which way would you like to go?: (L for left, R for right)");
			char direction=sc.nextLine().charAt(0);//inputing direction
			if(direction=='L' || direction=='l')
				Pobj.Start(BTobj.root.left);//calling function to start the game
			else if(direction=='R' || direction=='r')
				Pobj.Start(BTobj.root.right);//calling function to start the game
			else {//case: wrong input of choice
				System.out.println("Wrong input of choice! Let's go again");
				playGame();
			}
			System.out.println("Do you want to play again? (Y for yes)");
			choice=sc.nextLine().charAt(0);
		}
		while(choice=='y'|| choice=='Y');
		Thread.sleep(3000);//timer delay
	}
	
}
public class Main {//Main class
	public static void main(String[] args) throws InterruptedException{//main function
		Scanner sc=new Scanner(System.in);
		System.out.println("============================== GAMING ZONE ==============================");
		System.out.println("✽Try out your reflexes, knowledge and guessing power here!!!");//introduction
		int choice=0;
		do {//do...while loop to continue playing games
			System.out.println();//games menu
			System.out.println(" ---------------------------");
			System.out.println("|\t   GAMES            |");
			System.out.println(" ---------------------------");
			System.out.println("|\t1: SNAKE GAME       |");
			System.out.println("|\t2: TREASURE HUNT    |");
			System.out.println("|\t3: MINESWEEPER      |");
			System.out.println("|\t4: EXIT             |");
			System.out.println(" ---------------------------");
			System.out.println("\nGo ahead, make your choice for a game");
			choice=sc.nextInt();//inputing choice
			switch(choice) {
			case 1://sname game chosen
				System.out.println("\t\tLet's test your reflexes!\t\t");
				Game newGame = new Game();
				break;
			case 2://treasure hunt game chosen
				System.out.println("\t\tLet's test your knowledge!\t\t");
				treasureHuntGame THG=new treasureHuntGame();
				THG.playGame();
				break;
			case 3://minesweeper game chosen
				System.out.println("\t\tLet's test your guessing power!\t\t");
				Minesweeper mS=new Minesweeper();
				mS.playGame();
				break;
			case 4://exit option chosen
				System.out.println("We hope you enjoyed your time playing these games! :)");
				System.exit(0);
				break;
			default://default case wrong input of choice
				System.out.println("Don't get too excited to lead to the wrong input of choice!");
			}
		}while(choice!=4);
		sc.close();
	}
}