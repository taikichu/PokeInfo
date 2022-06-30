package useCodePoke;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class StockValue extends JFrame{
	private JPanel pane;
	public StockValue(String name,int no) {
		 pane = (JPanel)getContentPane();
			ArrayList<String> msg=new ArrayList<String>();
			if(no<=151) {msg.add("赤");msg.add("緑");msg.add("青");msg.add("ピカチュウ");}
			else if(no<=251){msg.add("金");msg.add("銀");msg.add("クリスタル");}
			else if(no<=386) {msg.add("ルビー");msg.add("サファイア");msg.add("エメラルド");msg.add("ファイアレッド");msg.add("リーフグリーン");}
			else if(no<=493) {msg.add("ダイヤモンド");msg.add("パール");msg.add("プラチナ");msg.add("ハートゴールド");msg.add("ソウルシルバー");}
			else if(no<=649) {msg.add("ブラック");msg.add("ホワイト");msg.add("ブラック2");msg.add("ホワイト2");}
			else if(no<=721) {msg.add("X");msg.add("Y");msg.add("オメガルビー");msg.add("アルファサファイア");}
			else if(no<=807) {msg.add("サン");msg.add("ムーン");msg.add("ウルトラサン");msg.add("ウルトラムーン");}
			else {msg.add("ソード");msg.add("シールド");}
		
		    JToolBar tool = new JToolBar();
		    pane.add( tool,BorderLayout.NORTH);
		    for(int i=0;i<msg.size();i++) {
		    	tool.add( new Dialog( msg.get(i) ) );
		    }
	}
	 class Dialog extends AbstractAction {
		 private String text;
		    Dialog( String text ){ 
		    	super( text );
		    	this.text=text;
		    }
		    public void actionPerformed( ActionEvent e ){
		    	Yahoopoke poke=new Yahoopoke(text);
		    JOptionPane.showMessageDialog( pane, "Yahooショッピングで検索した最初の金額:"+poke.gettitleList().get(0)+"円", text, JOptionPane.PLAIN_MESSAGE,
		    		new ImageIcon("pokesoft\\"+text+".jpg"));
		    }
		  }
}
