package useCodePoke;

import javax.swing.JFrame;
public class Main {
	public static void main(String[] args) {
		Gui w=new Gui("POKEMON_Menu");
		w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		w.setLocation(250,250);
	    w.setSize( 600, 600 );
	    w.setVisible( true );
	    
	}
}
