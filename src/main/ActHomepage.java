package useCodePoke;
import java.awt.event.ActionEvent;
import java.util.Scanner;


import javax.swing.AbstractAction;

//homepageを読み取るクラス
public class ActHomepage extends AbstractAction {
	public ActHomepage( String text ) { super( text ); }
	    public void actionPerformed( ActionEvent e ){
	    	long sleepLength = 1000;
	    	Scanner input=new Scanner(System.in);
	    	int count=0,judge=-1;
	    	System.out.println("1秒間隔で取得します");
	    	try {
				for(int i=1;i<=85;i++) {
					Homepage_Getnewdata products = new Homepage_Getnewdata("https://topics.nintendo.co.jp/list?p="+i);
					for(Pokemon product: products.getProductList()) {
						count++;
						Thread.sleep(sleepLength);
						System.out.println(product);
				        if((count%5)==0) {
							System.out.println("\n\nまだ読み込みますか？(0:yes それ以外:no)\n\n");
							judge=input.nextInt();
							if(judge==0) continue;
							else{
								i=84;
								break;
							}
						}
					}
				}
	    	}catch(Exception e1) {
	    		System.out.println("エラーが発生しました。");
	    	}
	    }
	 }