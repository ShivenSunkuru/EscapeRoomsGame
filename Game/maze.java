import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import java.awt.image.*;
import javax.imageio.ImageIO;


public class maze extends JPanel implements KeyListener
{
	JFrame frame;
	String[][] maze=new String[20][0];
	Font font=new Font("Monospaced",Font.PLAIN, 20);
	int pRow, pCol, endRow, endCol, mRow, mCol;
	int k1Row, k1Col, d1Row, d1Col, k2Row, k2Col, d2Row, d2Col, lRow, lCol, fRow, fCol, efRow, efCol;
	int tRow, tCol;
	int steps=0;
	boolean key1found=false;
	boolean key2found=false;
	boolean open1=false;
	boolean open2=false;
	boolean gameOver=false;
	boolean gameOver2=false;
	boolean gameOver3=false;
	boolean gameOver4=false;
	boolean gameOver5=false;
	boolean gameDone=false;

	BufferedImage rightWalk, rightStand, monster;
	BufferedImage Key, Door, OpenDoor, Trap, laser, Fire, Electric;
	int count=0;
	int whichmaze=1;
	int dim=15;
	public maze()
	{

	frame=new JFrame("A-mazing Program!");
	frame.add(this);
	frame.setSize(1400,450);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.addKeyListener(this);

	//they could possibly not be png so change it if needed.
	try{
		rightWalk = ImageIO.read(new File("rightWalk.png"));
		rightStand = ImageIO.read(new File("rightStand.png"));
		monster = ImageIO.read(new File("monster.png"));
		laser = ImageIO.read(new File("laser.png"));
				Key = ImageIO.read(new File("Key.png"));
Door = ImageIO.read(new File("Door.png"));
OpenDoor = ImageIO.read(new File("OpenDoor.png"));
Trap = ImageIO.read(new File("Trap.png"));
Fire = ImageIO.read(new File("Fire.png"));
Electric = ImageIO.read(new File("Electric.png"));
	}catch(IOException e){}

	rightWalk=resize(rightWalk,dim,dim);
	rightStand=resize(rightStand,dim,dim);
	Trap=resize(Trap,dim,dim);


		Key=resize(Key,dim,dim);
		Door=resize(Door, dim, dim);
		OpenDoor=resize(OpenDoor, dim,dim);
		monster=resize(monster,dim,dim);
		laser=resize(laser,dim,dim);
		Electric=resize(Electric,dim,dim);
		Fire=resize(Fire,dim,dim);
	createMaze("maze1.txt");



//make an add on which boosts you 5 tiles in a random direct (in maze 3)
//in maze 3, have the mosnter go the opposite direction of the player. If m equals p make end screen.

		frame.setVisible(true);

	}

		public BufferedImage resize (BufferedImage image, int width, int height){
			Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage scaledVersion = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = scaledVersion.createGraphics();
			g2.drawImage(temp,0,0,null);
			g2.dispose();
			return scaledVersion;
		}

		public void createMaze(String name)
		{

			try
			{
				File file=new File(name);
				BufferedReader input=new BufferedReader(new FileReader(file));
				String st;
				int row=0;
				while((st=input.readLine())!=null)
				{
					maze[row]=st.split("");
					for(int c=0;c<maze[row].length;c++)
					{

						if(maze[row][c].equals("S"))
						{

						pRow=row;
						pCol=c;

						}

						if(maze[row][c].equals("M"))
						{

							mRow=row;
							mCol=c;

						}

						if(maze[row][c].equals("E"))
						{

							endRow=row;
							endCol=c;

						}
						if(maze[row][c].equals("D"))
																		{

																			d1Row=row;
																			d1Col=c;

												}
												if(maze[row][c].equals("K"))
																		{

																			k1Row=row;
																			k1Col=c;

						}
if(maze[row][c].equals("d"))
																		{

																			d2Row=row;
																			d2Col=c;

												}
												if(maze[row][c].equals("k"))
																		{

																			k2Row=row;
																			k2Col=c;

						}

																		if(maze[row][c].equals("T"))
																								{

																									tRow=row;
																									tCol=c;

						}

						if(maze[row][c].equals("t"))
																														{

																															lRow=row;
																															lCol=c;

						}
												if(maze[row][c].equals("F"))
																																				{

																																					fRow=row;
																																					fCol=c;

						}
												if(maze[row][c].equals("e"))
																																				{

																																					efRow=row;
																																					efCol=c;

						}

				    }
					row++;
				}
			}
			catch(IOException e)
			{
			}

		/*
			for(int r=0;r<maze.length;r++)
			{
				for(int c=0;c<maze[0].length;c++)
					System.out.print(maze[r][c]);
				System.out.println();
			}
		*/

		}



		public void paintComponent(Graphics g)
		{
			if(!gameOver)
			{
			super.paintComponent(g);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.fillRect(0,0,frame.getWidth(),frame.getHeight());
			//paint the maze
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(2));
			g.setColor(Color.CYAN);
			if(whichmaze==1)
			{
			g.drawString("Maze Room 1!",335,80);
			g.setColor(Color.WHITE);
			g.drawString("The Cyan Block - Exit.",335,45);
			g.drawString("Count: "+steps,335,20);
			}
						if(whichmaze==2)
						{
						g.drawString("Maze Room 2!",335,80);
						g.setColor(Color.WHITE);
						g.drawString("The Cyan Block - Exit.",150,45);
						g.drawString("Count: "+steps,150,20);
						}
						if(whichmaze==3)
						{
						g.drawString("Maze Room 3!",335,80);
						g.setColor(Color.WHITE);
						g.drawString("The Cyan Block - Exit.",150,45);
						g.drawString("Count: "+steps,150,20);
						}





			for(int r=0;r<maze.length;r++)
			{
				for(int c=0;c<maze[0].length;c++)
				{
					if(maze[r][c].equals("*"))
					g.setColor(Color.BLUE);
					g.fillRect(c*dim+50,r*dim+100,dim,dim);
					g.setColor(Color.BLACK);
					g.drawRect(c*dim+50,r*dim+100,dim,dim);

				}
			}

			//paint the hero
			//change the pen width when you paint
			g2.setStroke(new BasicStroke(2));
			//g.setColor(Color.CYAN);
			//g.fillOval(pCol*dim+50,pRow*dim+100, dim ,dim);
			//g.setColor(Color.WHITE);
			//g.drawOval(pCol*dim+50,pRow*dim+100, dim ,dim);


				if(!key1found)
					g.drawImage(Key, k1Col*dim+50, k1Row*dim+100,this);

					if(!key2found)
					g.drawImage(Key, k2Col*dim+50, k2Row*dim+100,this);

					if(!open1)
						g.drawImage(Door,d1Col*dim+50, d1Row*dim+100,this);
					if(!open2)
						g.drawImage(Door,d2Col*dim+50, d2Row*dim+100,this);
					if(open1)
					g.drawImage(OpenDoor,d1Col*dim+50,d1Row*dim+100,this);
									if(open2)
					g.drawImage(OpenDoor,d2Col*dim+50,d2Row*dim+100,this);

					g.drawImage(Trap,tCol*dim+50,tRow*dim+100,this);

					g.drawImage(laser,lCol*dim+50,lRow*dim+100,this);

					g.drawImage(Fire,fCol*dim+50,fRow*dim+100,this);

					g.drawImage(Electric,efCol*dim+50,efRow*dim+100,this);










			//position of sprite
			if(count%2==0)
				g.drawImage(rightStand,pCol*dim+50,pRow*dim+100,this);
				else g.drawImage(rightWalk,pCol*dim+50,pRow*dim+100,this);

			g.setColor(Color.CYAN);
			g.fillRect(endCol*dim+50,endRow*dim+100, dim ,dim);









			if(whichmaze>1)
			{
g.drawImage(monster,mCol*dim+50,mRow*dim+100,this);
			}
	}

							if(whichmaze>=4 && pRow==endRow && pCol==endCol)
									{
										gameDone=true;
								}
								if(gameDone)
								{
																								g.setColor(Color.BLACK);
																g.fillRect(0,0,frame.getWidth(),frame.getHeight());
																g.setColor(Color.YELLOW);
										g.drawString("Congrats! You completed all the rooms!",100,200);
									}
				if(pRow==mRow && pCol==mCol) { gameOver=true; } if(gameOver) { g.setColor(Color.BLACK); g.fillRect(0,0,frame.getWidth(),frame.getHeight()); g.setColor(Color.CYAN); g.drawString("You got eaten by the monster and died!",100,200); }

								if(pRow==tRow && pCol==tCol)
								{
									gameOver2=true;
							}
								if(gameOver2)
								{
																								g.setColor(Color.BLACK);
																g.fillRect(0,0,frame.getWidth(),frame.getHeight());
																					g.setColor(Color.CYAN);
									g.drawString("You hit a trap and died!",100,200);
								}
					if(pRow==lRow && pCol==lCol)
													{
														gameOver3=true;
												}
													if(gameOver3)
													{
																													g.setColor(Color.BLACK);
																					g.fillRect(0,0,frame.getWidth(),frame.getHeight());
																										g.setColor(Color.CYAN);
														g.drawString("You hit a laser and died!",100,200);
													}

													if(pRow==fRow && pCol==fCol)
																										{
																											gameOver4=true;
																									}
																										if(gameOver4)
																										{
																																										g.setColor(Color.BLACK);
																																		g.fillRect(0,0,frame.getWidth(),frame.getHeight());
																																							g.setColor(Color.CYAN);
																											g.drawString("You stepped on fire and died!",100,200);
																										}
																										if(pRow==efRow && pCol==efCol)
																																							{
																																								gameOver5=true;
																																						}
																																							if(gameOver5)
																																							{
																																																							g.setColor(Color.BLACK);
																																															g.fillRect(0,0,frame.getWidth(),frame.getHeight());
																																																				g.setColor(Color.CYAN);
																																								g.drawString("You hit a electric field and died!",100,200);
																																							}






		}

		public void monsterone()
		{
			int random=(int)(Math.random()*4)+1;
			switch(random)
						{
							case 1: //left
								if(!maze[mRow][mCol-1].equals("*"))
								{
									mCol--;
								}
								break;
							case 2: //up
								if(!maze[mRow-1][mCol].equals("*"))
								{
									mRow--;
								}
								break;
							case 3: //right
								if(!maze[mRow][mCol+1].equals("*"))
								{
									mCol++;
								}
								break;
							case 4: //down
								if(!maze[mRow+1][mCol].equals("*"))
								{
									mRow++;
								}
								break;
								}

		}

		public void monstertwo(int which)
		{
		    switch(which) {
		        case 37: // left

		            if(!maze[mRow][mCol+1].equals("*")) {
		                mCol++;
					}
		            break;
		        case 38: // up

		            if(!maze[mRow+1][mCol].equals("*")) {
		                mRow++;
				 	}
				 	break;
		        case 39: // right

		            if( !maze[mRow][mCol-1].equals("*")) {
		                mCol--;
					}
					break;
		        case 40: // down
		            if(!maze[mRow-1][mCol].equals("*")) {
		                mRow--;
		            }
		            break;
		    }

}


		public void keyPressed(KeyEvent e) {
			count++;
			switch(e.getKeyCode())
			{
				case 37: //left
					if(maze[pRow][pCol-1].equals(" ") || maze[pRow][pCol-1].equals("K") || maze[pRow][pCol-1].equals("k") || maze[pRow][pCol-1].equals("E") || maze[pRow][pCol-1].equals("S") || maze[pRow][pCol-1].equals("F") || maze[pRow][pCol-1].equals("M") || maze[pRow][pCol-1].equals("e") || maze[pRow][pCol-1].equals("T") || maze[pRow][pCol-1].equals("t"))
					{
						pCol--;
					}


										if(key1found && pRow==d1Row && pCol-1==d1Col)
										{
											if(!open1)
																																open1=true;
																					else pCol--;
										}
																				if(key2found && pRow==d2Row && pCol-1==d2Col)
																				{
																					if(!open2)
																																										open2=true;
																					else pCol--;
										}
										steps++;
					break;
				case 38: //up
					if(maze[pRow-1][pCol].equals(" ") || maze[pRow-1][pCol].equals("K") || maze[pRow-1][pCol].equals("k") || maze[pRow-1][pCol].equals("E") || maze[pRow-1][pCol].equals("S") || maze[pRow-1][pCol].equals("F") || maze[pRow-1][pCol].equals("M") || maze[pRow-1][pCol].equals("e") || maze[pRow-1][pCol].equals("T") || maze[pRow-1][pCol].equals("t"))
					{
						pRow--;
					}
										if(key1found && pRow-1==d1Row && pCol==d1Col)
										{
											if(!open1)
																																open1=true;
																					else pRow--;
										}
																				if(key2found && pRow-1==d2Row && pCol==d2Col)
																				{
																					if(!open2)
																																										open2=true;
																					else pRow--;
										}
										steps++;
					break;
				case 39: //right
					if(maze[pRow][pCol+1].equals(" ") || maze[pRow][pCol+1].equals("K") || maze[pRow][pCol+1].equals("k") || maze[pRow][pCol+1].equals("E") || maze[pRow][pCol+1].equals("S") || maze[pRow][pCol+1].equals("F") || maze[pRow][pCol+1].equals("M") || maze[pRow][pCol+1].equals("e") || maze[pRow][pCol+1].equals("T") || maze[pRow][pCol+1].equals("t"))
					{
						pCol++;
					}
										if(key1found && pRow==d1Row && pCol+1==d1Col)
										{
											if(!open1)
																																open1=true;
																					else pCol++;
										}
																				if(key2found && pRow==d2Row && pCol+1==d2Col)
																				{
																		if(!open2)
																																							open2=true;
																					else pCol++;
										}
										steps++;
					break;
				case 40: //down
					if(maze[pRow+1][pCol].equals(" ") || maze[pRow+1][pCol].equals("K") || maze[pRow+1][pCol].equals("k") || maze[pRow+1][pCol].equals("E") || maze[pRow+1][pCol].equals("S") || maze[pRow+1][pCol].equals("F") || maze[pRow+1][pCol].equals("M") || maze[pRow+1][pCol].equals("e") || maze[pRow+1][pCol].equals("T") || maze[pRow+1][pCol].equals("t"))
					{
						pRow++;
					}
										if(key1found && pRow+1==d1Row && pCol==d1Col)
										{
											if(!open1)
																																open1=true;
																					else pRow++;
										}
																				if(key2found && pRow+1==d2Row && pCol==d2Col)
																				{
																					if(!open2)
																					open2=true;
																					else pRow++;
										}
										steps++;
					break;
					}


if(pRow==endRow && pCol==endCol)
					{
					whichmaze++;
					if(whichmaze==2)
					{
							key1found=false;
						key2found=false;
						open1=false;
						open2=false;
					createMaze("maze2.txt");
					}
					if(whichmaze==3)
					{
							key1found=false;
						key2found=false;
						open1=false;
						open2=false;
					createMaze("maze3.txt");
					}

}


if(pRow == k1Row && pCol == k1Col)
				{

					key1found=true;

				}
if(pRow==k2Row && pCol==k2Col)
				{
						key2found=true;
				}


		    if(whichmaze == 2) {
		        monsterone();
			}
			if(whichmaze == 3)
			{
		        monstertwo(e.getKeyCode());
			}


		    repaint();






	}
	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}

 	public static void main(String[] args)
  	{
		maze app=new maze();
  	}
}
