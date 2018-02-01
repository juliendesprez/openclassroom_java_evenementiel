package openclassroom_interaction_boutons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Fenetre extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3052529935748844938L;
	private Panneau pan = new Panneau();
	private JButton bouton = new JButton("bouton 1");
	private JButton bouton2 = new JButton("bouton 2");
	private JPanel container = new JPanel();
	private JLabel label = new JLabel("Le JLabel");
	public Fenetre(){
		this.setTitle("Animation");
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);

		//Ce sont maintenant nos classes internes qui �coutent nos boutons 
		bouton.addActionListener(new BoutonListener());
		bouton2.addActionListener(new Bouton2Listener());

		JPanel south = new JPanel();
		south.add(bouton);
		south.add(bouton2);
		container.add(south, BorderLayout.SOUTH);
		Font police = new Font("Tahoma", Font.BOLD, 16);
		label.setFont(police);
		label.setForeground(Color.blue);
		label.setHorizontalAlignment(JLabel.CENTER);
		container.add(label, BorderLayout.NORTH);
		this.setContentPane(container);
		this.setVisible(true);
		go();
	}

	private void go(){  
		//Les coordonn�es de d�part de notre rond
		int x = pan.getPosX(), y = pan.getPosY();
		//Le bool�en pour savoir si l'on recule ou non sur l'axe x
		boolean backX = false;
		//Le bool�en pour savoir si l'on recule ou non sur l'axe y
		boolean backY = false;

		//Dans cet exemple, j'utilise une boucle while
		//Vous verrez qu'elle fonctionne tr�s bien
		while(true){
			//Si la coordonn�e x est inf�rieure � 1, on avance
			if(x < 1)backX = false;
			//Si la coordonn�e x est sup�rieure � la taille du Panneau moins la taille du rond, on recule
			if(x > pan.getWidth()-50)backX = true;
			//Idem pour l'axe y
			if(y < 1)backY = false;
			if(y > pan.getHeight()-50)backY = true;

			//Si on avance, on incr�mente la coordonn�e
			if(!backX)
				pan.setPosX(++x);
			//Sinon, on d�cr�mente
			else
				pan.setPosX(--x);
			//Idem pour l'axe Y
			if(!backY)
				pan.setPosY(++y);
			else
				pan.setPosY(--y);

			//On redessine notre Panneau
			pan.repaint();
			//Comme on dit : la pause s'impose ! Ici, trois milli�mes de seconde
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}    
	}  
	//Classe �coutant notre premier bouton
	class BoutonListener implements ActionListener{
		//Red�finition de la m�thode actionPerformed()
		public void actionPerformed(ActionEvent arg0) {
			label.setText("Vous avez cliqu� sur le bouton 1");        
		}
	}

	//Classe �coutant notre second bouton
	class Bouton2Listener implements ActionListener{
		//Red�finition de la m�thode actionPerformed()
		public void actionPerformed(ActionEvent e) {
			label.setText("Vous avez cliqu� sur le bouton 2");    
		}
	}     
}