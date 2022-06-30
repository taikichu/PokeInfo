package useCodePoke;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MakesoftArea {
	public MakesoftArea() {}
	public MakesoftArea(String name,int no) {
		int ans = JOptionPane.showConfirmDialog( new JPanel(), name+"の登場するソフトを検索しますか？", "ソフト検索",
                JOptionPane.YES_NO_OPTION );
		if(ans==0) {
			StockValue value=new StockValue(name,no);
		    value.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    value.setLocation(250,250);
			value.setSize( 600, 600 );
			value.setVisible( true );
		}
	}
}
