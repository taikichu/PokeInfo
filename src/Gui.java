package useCodePoke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Gui extends JFrame{
	private JPanel pane=(JPanel)getContentPane();
	private ActionListener actionListener = new TextFieldAction();
	private static JTextField tfM1;
	private static JTextArea tfM2;
	private static JTextArea tfM3;
	public Gui() {}
	public Gui( String title ){
	    super( title );
	    //textField　作成
	    tfM1=new JTextField();
	    tfM1.setPreferredSize(new Dimension(Short.MAX_VALUE,50));
	    tfM1.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
	    //Enterキーを押したら反応する
	  	tfM1.addActionListener( actionListener );
	    tfM1.setBorder( new TitledBorder( "ポケモンについて検索できます（カタカナで入力して下さい）" ) );
	    
	    tfM2=new JTextArea();
	    tfM2.setPreferredSize(new Dimension(150,50));
	    tfM2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
	    tfM2.setBorder( new TitledBorder( "検索履歴" ) );
	    tfM2.setEnabled(false);
	    
	    tfM3=new JTextArea();
	    tfM3.setPreferredSize(new Dimension(150,50));
	    tfM3.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
	    tfM3.setBorder( new TitledBorder( "好みのポケモン選択履歴" ) );
	    tfM3.setEnabled(false);
	    
	    //メニューパネル作成
	    JPanel menu1=new JPanel();
	    menu1.setLayout( new BoxLayout( menu1, BoxLayout.Y_AXIS ) );// レイアウト方法の指定
	    JPanel menu2=new JPanel();
	    menu2.setLayout( new BoxLayout( menu2, BoxLayout.X_AXIS ) );// レイアウト方法の指定
	    //メニューボタン作成
	      JButton hp_bt = new JButton(new ActHomepage("公式ホームページから新着情報取得")); // ボタンの生成
	      hp_bt.setAlignmentX(0.5f);//ボタン位置を中央に
	      //hp_bt.setPreferredSize( new Dimension(240,5) ); // 240ボタンサイズの設定 
	      hp_bt.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
	      hp_bt.setContentAreaFilled(false);
	      hp_bt.setOpaque(false);
	//      bt.addActionListener(this);
	       
	      JButton jd_bt=new JButton(new PokeChoice("好みのポケモン探索",pane,tfM3));
	      jd_bt.setIcon(new ImageIcon("nya-su3.png"));
	      jd_bt.setHorizontalTextPosition(JButton.LEFT);
	      jd_bt.setAlignmentX(0.5f);//ボタン位置を中央に
	      jd_bt.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
	      jd_bt.setContentAreaFilled(false);
	      
	      JButton clear_bt=new JButton(new Clear("検索履歴クリア"));
	      clear_bt.setAlignmentX(0.5f);//ボタン位置を中央に
	      clear_bt.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
	      
	   // ボタンの追加
	      menu1.add( hp_bt ); 
	      menu1.add( jd_bt ); 
	      menu1.add(clear_bt);
	      
	      menu2.add(menu1);
	      menu2.add(tfM2);
	      menu2.add(tfM3);
	      
	    pane.add(tfM1,BorderLayout.PAGE_START);
	    pane.add(menu2,BorderLayout.CENTER);
  }
	public void remT2() {
		tfM2.setText("");
	}
	public void remT3() {
		tfM3.setText("");
	}
	public void addT2(String add) {
		tfM2.append(add);
	}
	public void addT3(String add) {
		tfM3.append(add);
	}
//検索バーにEnterが押されたときに反応する
	class TextFieldAction implements ActionListener {
	    public void actionPerformed( ActionEvent e ){
	    	String name=tfM1.getText();//検索バーに入力された文字をnameに格納 JPanel pane=new JPanel();
	    	tfM1.setText(null);//検索バーを初期化
	    	ObjectMapper mapper = new ObjectMapper();
	    	JsonNode search;
	    	int no=0;
			try {
				search = mapper.readTree(new File("pokemon.json"));
				JPanel pane=new JPanel();	
				for(int i=0;i<898;i++) {
					if(name.equals(search.get(i).get("名前").asText())) {
						no=search.get(i).get("全国No.").asInt();//全国ナンバーをnoに代入
	            		break;
					}
				}
				if(0<no&&no<899) {//searchから送られてきたポケモンを調べる
					Search info=new Search();
					String[] a=new String[info.Pokeinfo(no).length];a=info.Pokeinfo(no);
					String[] b=new String[info.RetBvalue().length];b=info.RetBvalue();
					String[] c=new String[info.RetCvalue().length];c=info.RetCvalue();
					//searchクラスで抜き出した情報を見やすくするために整理する
					for(int i=1;i<(a.length);i++) {
						if(b[i]==null) break;
						else {
							if(b[i].equals(b[i-1])) {
								if(c[i]==null)a[i]="  "+a[i];
								else a[i]="  "+c[i]+" "+a[i];
							}
							else {
								if(c[i]==null)a[i]=b[i]+" "+a[i];
								else a[i]=b[i]+" "+c[i]+" "+a[i];
							}
						}
					}
					Object[] msg = { a }; // メッセージの準備
				     JOptionPane.showMessageDialog( pane, msg,name+"の情報",
				    		  JOptionPane.PLAIN_MESSAGE,new ImageIcon( "pokemonfile\\image"+no+".png" )); // ポケモンの情報をダイアログの表示
				      tfM2.append(name+"\n");
				      new MakesoftArea(name,no);
				}
				else JOptionPane.showMessageDialog( pane, "errorが発生しました");
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}//JsonNode型のsearchを定義。ここからポケモンの情報をサーチする
	    }
	}
	class Clear extends AbstractAction{
		public Clear(String title) {
			super(title);
		}
		public void actionPerformed( ActionEvent e ) {
			tfM2.setText("");
		}
	}
}
