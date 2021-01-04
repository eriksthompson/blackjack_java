package blackjack.game;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Blackjack {
	private final static String[] deckPic = {"card/spades/a.png","card/diamonds/a.png", "card/hearts/a.png", "card/clubs/a.png",  "card/hearts/2.png","card/diamonds/2.png", "card/clubs/2.png","card/spades/2.png",
			"card/clubs/3.png","card/diamonds/3.png", "card/hearts/3.png",  "card/spades/3.png", "card/clubs/4.png", "card/spades/4.png", "card/diamonds/4.png","card/hearts/4.png",
			"card/clubs/5.png", "card/diamonds/5.png", "card/spades/5.png", "card/hearts/5.png",
			"card/clubs/6.png","card/diamonds/6.png","card/hearts/6.png","card/spades/6.png", "card/diamonds/7.png","card/clubs/7.png", "card/spades/7.png","card/hearts/7.png",
			"card/clubs/8.png", "card/diamonds/8.png",   "card/hearts/8.png","card/spades/8.png",  "card/spades/9.png","card/diamonds/9.png","card/clubs/9.png","card/hearts/9.png",
			"card/clubs/10.png", "card/diamonds/10.png","card/hearts/10.png","card/spades/10.png","card/diamonds/j.png","card/hearts/j.png",  "card/spades/j.png", "card/clubs/j.png",  
			"card/diamonds/q.png", "card/hearts/q.png",  "card/spades/q.png","card/clubs/q.png", "card/spades/k.png","card/hearts/k.png","card/diamonds/k.png", "card/clubs/k.png"};
	private final static int[] deck = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 ,21 ,22, 23 ,24 ,25, 26, 27, 28, 29 ,30,31,32,34,35,36,37,38, 39, 40, 41 ,42 ,43 ,44, 45,46,47
			,48,49,50,51};
	
	private static ArrayList<Integer> decki;
	static boolean play = true;
	
	private JButton hitButton;
	private JButton standButton;
	private JButton resetButton;
	
	private static int dp;
	private static int da;
	private static int pp;
	private static int pa;
	
	private static JPanel dealerHand;
	private static JPanel playerHand;
	private static JPanel buttons;
	private static JFrame j;
	private static JLabel dealerCard1;
	private static JLabel dealerCard2;
	private static JLabel playerCard1;
	private static JLabel playerCard2;
	
	public Blackjack(){
		j = new JFrame("BlackJack by Erik Thompson");
		
		j.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
		j.getContentPane ().setLayout (new BoxLayout (j.getContentPane (), BoxLayout.Y_AXIS));
		
		dealerHand = new JPanel();
		playerHand = new JPanel();
		buttons = new JPanel();
		
		hitButton = new JButton("hit");
		standButton = new JButton("stand");
		resetButton = new JButton("play/reset");
		
		Icon cardback = new ImageIcon("card/cardback.png");
		dealerCard1 = new JLabel("Dealer's hand:");
		dealerCard1.setIcon(cardback);
		dealerCard2 = new JLabel("");
		dealerCard2.setIcon(cardback);

		playerCard1 = new JLabel("Player's hand:");
		playerCard1.setIcon(cardback);
		playerCard2 = new JLabel("");
		playerCard2.setIcon(cardback);
		
		dealerHand.add(dealerCard1);
		dealerHand.add(dealerCard2);
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		buttons.add(hitButton);
		buttons.add(standButton);
		buttons.add(resetButton);
		
		j.add(dealerHand);
		j.add(playerHand);
		j.add(buttons);
		
		
		
		reset();
		
		
		j.setSize(800,600);
		j.setVisible(true);
	}
	
	public static void reset() {
		decki = new ArrayList<Integer>();
		for(int i = 0; i < deck.length; i++) {
			decki.add(i);
		}
		
		//fill hands
		play = true;
		dealerHand.removeAll();
		playerHand.removeAll();
		dp = 0;
		da=0;
		pp = 0;
		pa = 0;
		Icon randomCard;
		
		
		Icon cardback = new ImageIcon("card/cardback.png");
		
		//random card and icon
		int r = getRandomCard();
		updatePointsD(r);
		randomCard = new ImageIcon(deckPic[r]);
		
		dealerCard1.setIcon(randomCard);
		
		dealerCard2.setIcon(cardback);

		dealerHand.add(dealerCard1);
		dealerHand.add(dealerCard2);
		//random card and icon
		r = getRandomCard();
		updatePointsP(r);
		randomCard = new ImageIcon(deckPic[r]);
		playerCard1.setIcon(randomCard);
		
		

		//random card and icon
		r = getRandomCard();
		updatePointsP(r);
		randomCard = new ImageIcon(deckPic[r]);
		playerCard2.setIcon(randomCard);
		
		playerHand.add(playerCard1);
		playerHand.add(playerCard2);
		
		dealerCard1.setText("Dealer's Hand");
		playerCard1.setText("Player's hand");

		SwingUtilities.updateComponentTreeUI(j);
		
	}
	
	private static void updatePointsD(int c) {
		
			if(c >= 0 && c <= 3) {
				dp+= 11;
				da++;
				
			}else if (c>=4 &&c <=7) {
				dp+=2;
			}else if (c>=8 &&c <=11) {
				dp+=3;
			}else if (c>=12 &&c <=15) {
				dp+=4;
			}else if (c>=16 &&c <=19) {
				dp+=5;
			}else if (c>=20 &&c <=23) {
				dp+=6;
			}else if (c>=24 &&c <=27) {
				dp+=7;
			}else if (c>=28 &&c <=31) {
				dp+=8;
			}else if (c>=32 &&c <=35) {
				dp+=9;
			}else{
				dp += 10;
			}
			while(dp > 21 && da > 0) {
				da--;
				dp-=10;
			}
	}
	
	private static void updatePointsP(int c) {
		if(c >= 0 && c <= 3) {
			pp+= 11;
			pa++;
			
			
		}else if (c>=4 &&c <=7) {
			pp+=2;
		}else if (c>=8 &&c <=11) {
			pp+=3;
		}else if (c>=12 &&c <=15) {
			pp+=4;
		}else if (c>=16 &&c <=19) {
			pp+=5;
		}else if (c>=20 &&c <=23) {
			pp+=6;
		}else if (c>=24 &&c <=27) {
			pp+=7;
		}else if (c>=28 &&c <=31) {
			pp+=8;
		}else if (c>=32 &&c <=35) {
			pp+=9;
		}else{
			pp += 10;
		}
		while(pp > 21 && pa > 0) {
			pa--;
			pp-=10;
		}
	}
	
	public static int getRandomCard() {
		if(decki.size() > 0) {
			int i = (int)(Math.random() * (decki.size()-1));
			return deck[decki.remove(i)];
		}
		return 0;
	}
	
	private static void dealerHit() {
		if(play) {
			dealerHand.remove(dealerCard2);
			while(dp <= 17) {
				//random card and icon
				int r = getRandomCard();
				updatePointsD(r);
				Icon randomCard = new ImageIcon(deckPic[r]);
				JLabel nc = new JLabel("");
				nc.setIcon(randomCard);
				
				dealerHand.add(nc);
			}
			if(dp> 21) {
				playerCard1.setText("You win, dealer busts");
			}else if(dp < pp) {
				playerCard1.setText("You win");
			}else if (dp == pp) {
				playerCard1.setText("Draw ");
			}else if(dp > pp) {
				playerCard1.setText("You lose");
			}
				
				play = false;
		}
		SwingUtilities.updateComponentTreeUI(j);
		
	}
	
	private static void hit() {
		if(play) {
			int r = getRandomCard();
			updatePointsP(r);
			Icon randomCard = new ImageIcon(deckPic[r]);
			JLabel nc = new JLabel("");
			nc.setIcon(randomCard);
			playerHand.add(nc);
			playerCard1.setText("Player's hand:");
			if(pp > 21) {
				play = false;
				playerCard1.setText("you lose");
			}
			
			
		}
		SwingUtilities.updateComponentTreeUI(j);
	}
	
	
	
	public static void main(String[] args) {
		Blackjack b = new Blackjack();
		
		b.hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					hit();
				}
			});
		b.standButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dealerHit();
				}
			});
		b.resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					reset();
					
					
				}
			});
	}

}
