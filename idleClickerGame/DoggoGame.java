import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

//to-do: everything, better math for the rates of health/dmg/upgradeCost, upgrades for automatic clicks, start screen, art, ANYTHING other than swing for the gui, ability to restart the game with higher dmg so you can get further (the further you got, the more dmg you gain)

public class DoggoGame extends RunDoggoGame{

	private JFrame f;  //Making frame
	private JPanel p;  //Making panel
	
	public int doggo; //"enemy" number
	public int dmg; //user's current damage per click
	public float money; //$$.$$
	public double score;
	public int upgradeCost; //cost to raise dmg
	public double gain;
	public double health; //doggo's health
	
	//buttons and labels
	public JButton upgrade;
	public JButton attack;
	public JLabel boss;
	public JLabel bal;
	public JLabel dmgU;
	
	//so crazy long floats aren't displayed
	public DecimalFormat oneDec = new DecimalFormat("#.0");
	public DecimalFormat twoDec = new DecimalFormat("#.00");


	public void startGame() {
		//defaults
		dmg = 1;
		money = 0;
		score = .25;
		doggo = 1;
		health = 10;
		upgradeCost = 0;

		//GUI and the actual gameplay
		gui();
		
		//refresh stats
		update();
	}

	public void update() {
		upgrade.setText("Damage Upgrade [$"+ upgradeCost +"]");
		bal.setText("Wallet: $" + twoDec.format(money));
		dmgU.setText("Damage: " + dmg);
		boss.setText("Doggy: #" + doggo + "[" + oneDec.format(health) + " HP]");
	}


	public void gui() {
	//Frame
		f = new JFrame("idle game");
		f.setVisible(true);
		f.setSize(600, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

	//Panel
		p = new JPanel();
		p.setVisible(true);
		p.setBackground(Color.WHITE);
		f.add(p);

	//Boss num & health
		boss = new JLabel();
		boss.setText("Doggy: #" + doggo + "[" + health + " HP]");
		boss.setLocation(10, 60);
		
	//Money indicator
		bal = new JLabel();
		bal.setText("Wallet: $" + twoDec.format(money));
		bal.setLocation(10, 20);
		
	//Damage indicator
		dmgU = new JLabel();
		dmgU.setText("Damage: " + dmg);
		dmgU.setLocation(10, 40);
		
	//Attack Button
		attack = new JButton("attack");
		attack.setLocation(275, 150);

		attack.addActionListener(e -> {
			//Doggo captured??
			if ((health-dmg) <= 0) { //yes

				if (((doggo % 10) == 0) && (doggo != 10)) { // doggo number is divisible by 10 (--> is a boss)
				health = (int) ((doggo/10)*1.25*200);
				money += gain*3;
				
				System.out.println("\n Click!\nBoss finished!");
				System.out.println("+$"+ twoDec.format(gain*3) +"!");
				}
				else { //not a boss (or it's the first boss)
					if (doggo == 10) { // 10 = first boss
						health = 200;
					}
					else if ((1 <= doggo) && (doggo <= 9)) { // 1-9
						health = doggo * 10;
					}
					else { // every other number
						double n = (Math.floor(doggo*.1)*10);
						health = (((doggo)/(n+10))*((n/10)*250));
					}
					
					
					gain = Math.pow(2, doggo/12);
					doggo += 1;
					money += gain;

					System.out.println("\nClick!");
					System.out.println("+$"+ twoDec.format(gain) +"!");
				}

				
				if (money < 0) { // just in case
					System.out.println("\n\nError: Negative balance.\nReseting to $0.00");
					money = 0;
				}
			}
			else { // not yet
				health -= dmg;
			}

			update();
		});


	//Damage upgrade button
		upgrade = new JButton("Damage Upgrade [Free!]");
		upgrade.setLocation(450, 10);
		upgrade.addActionListener( e -> {
		if ((money - upgradeCost) < 0) { // tfw the player is broke
			System.out.println();
			System.out.println("You don't have enough money for that.");
		}
		else if ((money - upgradeCost) >= 0) { // buying the upgrade
			System.out.println();
			System.out.println("-$"+ upgradeCost +"!");
			money -= upgradeCost;
			dmg += (upgradeCost*Math.pow(10, 0.0225*score));
			score += .25;
		}
		if (money < 0) { // edge case
			System.out.println("\n\nError: Negative balance.\nReseting to $0.00)");
			money = 0;
		};
		upgradeCost = (int) (Math.pow(2, score));
		update();
	});

		
	//adding everything to the panel :>
		p.add(boss);  p.add(bal);  p.add(dmgU);  p.add(attack);  p.add(upgrade);
	}

}





//earlier attempt
/*import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Main extends Application {

	public int doggo;
	public int dmg;
	public float money;
	public double score;
	public int upgradeCost;
	public Button upgrade;
	public Button attack;
	public Text boss;
	public Text bal;
	public Text dmgU;
	public double health;
	public DecimalFormat oneDec = new DecimalFormat("#.0");
	public DecimalFormat twoDec = new DecimalFormat("#.00");


	public static void main (String[] args) {
		launch(args);
		}
	
	@Override
	public void start(Stage stage) throws Exception {

//vars n stuff
		dmg = 1;
		money = 0;
		score = .25;
		doggo = 1;
		health = 10;
		upgradeCost = 0;


//Boss num & health
		Text boss = new Text();
		boss.setText("Doggy: #" + doggo + "[" + health + " HP]");
		boss.setX(10);
		boss.setY(60);


//Money indicator
		Text bal = new Text();
		bal.setText("Wallet: $" + twoDec.format(money));
		bal.setX(10);
		bal.setY(20);


//Damage indicator
		Text dmgU = new Text();
		dmgU.setText("Damage: " + dmg);
		dmgU.setX(10);
		dmgU.setY(40);


//Attack button
		Button attack = new Button("attack");
		attack.setLayoutX(275);
		attack.setLayoutY(150);
		attack.maxWidth(50);
		attack.setOnAction(e -> {
		//Doggo ded??
			if ((health-dmg) <= 0) { //yeah
				doggo += 1;
				double gain = Math.pow(2, doggo/12)-Math.pow(2, score*.25);
//				money += gain;
				
				double temp = ((doggo/(doggo+(score*.001)*doggo))*1.25);
				System.out.println(temp);
				gain = temp;
				System.out.println(twoDec.format(gain));
				money += gain;
				
				
				System.out.println();
				System.out.println("click!");
				System.out.println("+$"+ twoDec.format(gain) +"!");
	
				if (((doggo % 10) == 0) && (doggo != 10)) { // divisible by 10
				health = (int) ((doggo/10)*1.25*200);
				money += gain*3;
				}
				else if (doggo == 10) { // just 10
					health = 200;
				}
				else if ((1 <= doggo) && (doggo <= 9)) { // 1-9
					health = doggo * 10;
				}
				else { // every other number
					double eugh = (Math.floor(doggo*.1)*10);
					health = (((doggo)/(eugh+10))*((eugh/10)*1.25*200));
				};
				if (money < 0) { // edge case
					System.out.println();System.out.println();
					System.out.println("Error:");
					System.out.println("Negative balance. Reseting to $0.");
					money = 0;
				};
			}
			else { //nah
				health -= dmg;
			};
			upgrade.setText("Damage Upgrade [$"+ upgradeCost +"]");
			bal.setText("Wallet: $" + twoDec.format(money));
			dmgU.setText("Damage: " + dmg);
			boss.setText("Doggy: #" + doggo + "[" + oneDec.format(health) + " HP]");
		});


//Damage upgrade button
		Button upgrade = new Button("Damage Upgrade [Free!]");
		upgrade.setLayoutX(450);
		upgrade.setLayoutY(10);
		upgrade.maxWidth(75);
		upgrade.setOnAction(e -> {

			if ((money - upgradeCost) < 0) { // tfw the player is broke
				System.out.println();
				System.out.println("you don't have enough money for that.");
			}
			else if ((money - upgradeCost) >= 0) { // buying the upgrade
				System.out.println();
				System.out.println("-$"+ upgradeCost +"!");
				money -= upgradeCost;
				dmg += (upgradeCost*Math.pow(10, 0.0225*score));
				score += .25;
			}
			if (money < 0) { // edge case
				System.out.println();System.out.println();
				System.out.println("Error:");
				System.out.println("Negative $. Reseting to 0)");
				money = 0;
			};
			upgradeCost = (int) (Math.pow(2, score));
			upgrade.setText("Damage Upgrade [$"+ upgradeCost +"]");
			bal.setText("Wallet: $" + twoDec.format(money));
			dmgU.setText("Damage: " + dmg);
			boss.setText("Doggy: #" + doggo + "[" + oneDec.format(health) + " HP]");
		});
		
//Scene
		Group root = new Group(dmgU, attack, upgrade, bal, boss);
		Scene scene = new Scene(root, 600, 300);
		stage.setScene(scene);
		stage.setTitle("rlly cool idle game :>");
		stage.show();
	}

}
*/
