import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Monster extends Creature
	{
	private boolean boss;
	private String location;
	private int monsterDamage;
	static boolean apMob, unluckyMob, drainingMob, acidicMob, poisonousMob;
	static int magicRes = 1, meleeRes = 1; 
	
	static ArrayList <Monster> monsters = new ArrayList<Monster>();
	
	public Monster(String n, int h, boolean b, String l, int md)
		{
		setHitPoints(h);
		setName(n);
		boss = b;
		location = l;
		monsterDamage = md;
		}
	
	public static int randomize()
		{
		ImageIcon icon = new ImageIcon(("monster comin.png"));
		JFrame frame = new JFrame();
		int monsterNumber = 0;
		
		if(Monster.monsters.size() > 7)
			{
			boolean check = true;
			
			while(check)
				{
				monsterNumber = (int) (Math.random() * monsters.size());
				if(!Monster.monsters.get(monsterNumber).isBoss())
					{
					JOptionPane.showMessageDialog(frame, "From the underbrush beside the path something arises."
							+ ".",
							"COMBAT",
							JOptionPane.QUESTION_MESSAGE,
							icon);
					
					prepMonster(monsterNumber);
					
					JOptionPane.showMessageDialog(frame, "It is a " + Monster.monsters.get(monsterNumber).getName() + "!",
							"COMBAT",
							JOptionPane.QUESTION_MESSAGE,
							icon);
					check = false;
					}
				}
			return monsterNumber;
			}
		else if(Monster.monsters.size() < 7)
			{
			ImageIcon end = new ImageIcon("end.jpg");
			JOptionPane.showMessageDialog(frame, "As the last of your foes falls to the ground you sigh in releif.",
					"VICTORY!!!!",
					JOptionPane.QUESTION_MESSAGE,
					end);
			JOptionPane.showMessageDialog(frame, "Behind the corpses of your enemies is a door made of gold.",
					"VICTORY!!!!",
					JOptionPane.QUESTION_MESSAGE,
					end);
			JOptionPane.showMessageDialog(frame, "You open the room to find it full of thousands of gold pieces!",
					"VICTORY!!!!",
					JOptionPane.QUESTION_MESSAGE,
					end);
			JOptionPane.showMessageDialog(frame, "You are rich and will live a long happy life.",
					"VICTORY!!!!",
					JOptionPane.QUESTION_MESSAGE,
					end);
			JOptionPane.showMessageDialog(frame, "YOU GOT THE ENDING: RICHES!",
					"VICTORY!!!!",
					JOptionPane.QUESTION_MESSAGE,
					end);
			System.exit(0);
			}
		else
			{
			ImageIcon boss = new ImageIcon("boss.jpg");
			monsterNumber = (int) (Math.random() * monsters.size());
				JOptionPane.showMessageDialog(frame, "An opponent charges you from the shadows of the underbrush."
						+ ".",
						"COMBAT",
						JOptionPane.QUESTION_MESSAGE,
						boss);
				JOptionPane.showMessageDialog(frame, "You have heard that it is the source of evil in the land."
						+ ".",
						"COMBAT",
						JOptionPane.QUESTION_MESSAGE,
						boss);
				JOptionPane.showMessageDialog(frame, "This final enemy is your greatest obstacle."
						+ "!",
						"COMBAT",
						JOptionPane.QUESTION_MESSAGE,
						boss);
				JOptionPane.showMessageDialog(frame, "It is a " + Monster.monsters.get(monsterNumber).getName() + "!",
						"COMBAT",
						JOptionPane.QUESTION_MESSAGE,
						boss);
			}
		return monsterNumber;
		}
	
	//@Override
	public static void attack(int monsterDamage, int heroHP)
		{
		ImageIcon icon = new ImageIcon(("rip.jpg"));
		ImageIcon iconTwo = new ImageIcon(("game over.jpg"));
		ImageIcon iconThree = new ImageIcon(("claws.jpg"));
		JFrame frame = new JFrame();
		
		monsterDamage = (int) (Math.random() * monsterDamage) + (monsterDamage + (Hero.heroes.get(0).getOverAllLevel() * 2));	
		
		if(Hero.heroes.get(0).getOverAllLevel() >= 12)
			{
			monsterDamage = monsterDamage * 2;
			}
		int enemyAttackLovation = (int) (Math.random() * 2);
		if(Hero.defend(enemyAttackLovation) == false)
			{
			if(apMob == false)
				{
				if(Hero.heroInventory.get(1) instanceof Armor)
					{
					Armor armor = (Armor) Hero.heroInventory.get(1);
					monsterDamage = monsterDamage - (armor.getArmorLevel() + Hero.heroes.get(0).getNaturalArmor());
					if(monsterDamage <= 0)
						{
						monsterDamage = 1;
						}
					heroHP = heroHP - monsterDamage;
					}	
				}
			else
				{
				heroHP = heroHP - monsterDamage;	
				}
			
			Hero.heroes.get(0).setHeroHP(heroHP);
				
			if(unluckyMob)
				{
				Hero.heroes.get(0).setLuck(Hero.heroes.get(0).getLuck() - 1);
				Hero.heroes.get(0).setMagicLuck(Hero.heroes.get(0).getMagicLuck() - 1);
				if(Hero.heroes.get(0).getMagicLuck() < 0)
					{
					Hero.heroes.get(0).setMagicLuck(0);	
					}
				if(Hero.heroes.get(0).getLuck() < 0)
					{
					Hero.heroes.get(0).setLuck(0);		
					}
				}
			
			if(drainingMob)
				{
				Hero.heroes.get(0).setAdrenaline(Hero.heroes.get(0).getAdrenaline() - 1);
				Hero.heroes.get(0).setWardPower(Hero.heroes.get(0).getWardPower() - 1);
				if(Hero.heroes.get(0).getWardPower() < 0)
					{
					Hero.heroes.get(0).setWardPower(0);	
					}
				if(Hero.heroes.get(0).getAdrenaline() < 0)
					{
					Hero.heroes.get(0).setAdrenaline(0);		
					}	
				}
			
			if(acidicMob)
				{
					
				}
			
			if(poisonousMob)
				{
				Hero.heroes.get(0).setMaxHeroHP(heroHP);	
				}
			
			
			JOptionPane.showMessageDialog(frame, "The monster attacks and does " + monsterDamage + " damage!",
					"" + Hero.heroes.get(0).getName() + "'s HP = " + Hero.heroes.get(0).getHeroHP() + "/" + Hero.heroes.get(0).getMaxHeroHP() + "",
					JOptionPane.QUESTION_MESSAGE,
					iconThree);
			
			Companion.defend(monsterDamage);
			if(Hero.heroes.get(0).getHeroHP() <= 0)
				{
				JOptionPane.showMessageDialog(frame, "You have been slain!",
						"" + Hero.heroes.get(0).getName() + "'s HP = " + Hero.heroes.get(0).getHeroHP() + "/" + Hero.heroes.get(0).getMaxHeroHP() + "",
						JOptionPane.QUESTION_MESSAGE,
						iconTwo);
				JOptionPane.showMessageDialog(frame, "YOU GOT THE ENDING: DEATH",
						"" + Hero.heroes.get(0).getName() + "'s HP = " + Hero.heroes.get(0).getHeroHP() + "/" + Hero.heroes.get(0).getMaxHeroHP() + "",
						JOptionPane.QUESTION_MESSAGE,
						icon);
				System.exit(0);
				}
			}
		}
	
	//@Override
	public static boolean defend(int meleeChoice)
		{
		int enemyBlockLocation = 0;
		
		if(meleeChoice == 0)
			{
			enemyBlockLocation = (int) (Math.random() * 3) + 1;
			}
		if(meleeChoice == 1)
			{
			enemyBlockLocation = (int) (Math.random() * 6) + 1 + (Hero.heroes.get(0).getAgilityLevel() / 4);
			}
		if(meleeChoice == 2)
			{
			enemyBlockLocation = (int) (Math.random() * 15) + 1 + (Hero.heroes.get(0).getAgilityLevel() / 2);
			}
		
		if(enemyBlockLocation != 1 || Hero.heroes.get(0).getAccuracyEffect() == true)
			{
			Hero.heroes.get(0).setAccuracyEffect(false);
			return true;
			}
		else
			{
			return false;
			}
		}
	
	public static void prepMonster(int monsterNum)
		{
		ImageIcon icon = new ImageIcon(("monster comin.png"));
		JFrame frame = new JFrame();
		//unluckyMob, drainingMob, acidicMob, poisonousMob
			
		int eliteCheck = (int) (Math.random() * 11);
		int bhCheck = (int) (Math.random() * 11);
		int apCheck = (int) (Math.random() * 11);
		int luckyCheck = (int) (Math.random() * 11);
		int drainCheck = (int) (Math.random() * 11);
		int acidCheck = (int) (Math.random() * 11);
		int poisonCheck = (int) (Math.random() * 11);
		int magicCheck = (int) (Math.random() * 11);
		int meleeCheck = (int) (Math.random() * 11);
		meleeRes = (int) (Math.random() * 11);
		magicRes = (int) (Math.random() * 11);
		
		switch(acidCheck)
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				{
				acidicMob = false;
				break;
				}
			case 10:
				{
				Monster.monsters.get(monsterNum).setName("Acidic " + Monster.monsters.get(monsterNum).getName());
				acidicMob = true;
				break;
				}
			}
		
		switch(poisonCheck)
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				{
				poisonousMob = false;
				break;
				}
			case 10:
				{
				Monster.monsters.get(monsterNum).setName("Poisonous " + Monster.monsters.get(monsterNum).getName());
				poisonousMob = true;
				break;
				}
			}
		
		switch(luckyCheck)
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				{
				unluckyMob = false;
				break;
				}
			case 10:
				{
				Monster.monsters.get(monsterNum).setName("Unlucky " + Monster.monsters.get(monsterNum).getName());
				unluckyMob = true;
				break;
				}
			}
		
		switch(drainCheck)
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				{
				drainingMob = false;
				break;
				}
			case 10:
				{
				Monster.monsters.get(monsterNum).setName("Draining " + Monster.monsters.get(monsterNum).getName());
				drainingMob = true;
				break;
				}
			}
		
		switch(eliteCheck)
			{
			case 9:
				{
				Monster.monsters.get(monsterNum).setName("Exhausted " + Monster.monsters.get(monsterNum).getName());
				Monster.monsters.get(monsterNum).setMonsterDamage(Monster.monsters.get(monsterNum).getMonsterDamage() / 2);	
				break;	
				}
			case 10:
				{
					Monster.monsters.get(monsterNum).setName("Frenzied " + Monster.monsters.get(monsterNum).getName());
					Monster.monsters.get(monsterNum).setMonsterDamage(2 * Monster.monsters.get(monsterNum).getMonsterDamage());
				break;
				}
			}
		switch(bhCheck)
			{
			case 9:
				{
				Monster.monsters.get(monsterNum).setName("Weak " + Monster.monsters.get(monsterNum).getName());
				Monster.monsters.get(monsterNum).setHitPoints(Monster.monsters.get(monsterNum).getHitPoints() / 2);	
				break;	
				}
			case 10:
				{
				Monster.monsters.get(monsterNum).setName("Tough " + Monster.monsters.get(monsterNum).getName());
				Monster.monsters.get(monsterNum).setHitPoints(2 * Monster.monsters.get(monsterNum).getHitPoints());
				break;
				}
			}
		switch(apCheck)
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				{
				apMob = false;
				break;
				}
			case 10:
				{
				Monster.monsters.get(monsterNum).setName(Monster.monsters.get(monsterNum).getName() + " with Armor Piercing Weapons");
				apMob = true;
				break;
				}
			}
		}

	public boolean isBoss()
		{
		return boss;
		}

	public void setBoss(boolean boss)
		{
		this.boss = boss;
		}

	public String getLocation()
		{
		return location;
		}

	public void setLocation(String location)
		{
		this.location = location;
		}

	public int getMonsterDamage()
		{
		return monsterDamage;
		}

	public void setMonsterDamage(int monsterDamage)
		{
		this.monsterDamage = monsterDamage;
		}

	public static ArrayList<Monster> getMonsters()
		{
		return monsters;
		}

	public static void setMonsters(ArrayList<Monster> monsters)
		{
		Monster.monsters = monsters;
		}

	
	}
