package useCodePoke;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PokeChoice extends AbstractAction{
	JPanel pane;
	JTextArea tfM3;
	static int count=0;
	static String[] value;
	 PokeChoice( String text,JPanel pane,JTextArea tfM3) { 
		 super( text ); 
		 this.pane=pane;
		 this.tfM3=tfM3;
	 }
	    public void actionPerformed( ActionEvent e ){
	    	JLabel label=new JLabel();
	    	 ImageIcon icon = new ImageIcon("./img/reo1s.gif");
	    	 pane.add(label,BorderLayout.PAGE_END);
	    	 
	    	 		double we=0.1;double d0=0,d1=0;Object o="a";
	    	 		int T=0;int ini=0;
				    String selectTy[] = {"ノーマル", "ほのお", "みず", "でんき","くさ","こおり","かくとう","どく","じめん","ひこう","エスパー","むし","いわ","ゴースト","ドラゴン","あく","はがね","フェアリー","何でもよい"};
				    String selectWe[]= new String[20];selectWe[19]="何でもよい";
				    String selectHe[]= new String[21];selectHe[20]="何でもよい";
				    String selectTa[]= {"植物","虫","鳥系","人型","怪獣","妖精","ドラゴン","鉱物系","陸上系","不定形","水中（浅め）","水中(中間)","水中(深め)","存在未発見","何でもよい"};
				    String selectAr[]= {"日本：　　関東","　　　　　近畿","　　　　　九州・沖縄","　　　　　北海道","アメリカ：ニューヨーク","　　　　　ハワイ","イギリス：グレートブリテン島","フランス","何でもよい(時間が掛かるのでオススメしません)"};
				    value=new String[5];
				    int skip=0;
				    Random random=new Random();
				    
				    for(int i=0;i<19;i++) {
				    	if(we==0.1) selectWe[i]=we+" kg ～ "+(we*=100)+" kg";
				    	else if(10.0<=we&&we<100.0)selectWe[i]=(we+0.1)+" kg ～ "+(we+=10)+" kg";
				    	else if(100.0<=we&&we<=800.0) selectWe[i]=(we+0.1)+" kg ～ "+(we+=100)+" kg";
				    	else selectWe[i]=(we+0.1)+" kg ～ 999.9kg";
				    }
				    for(int i=0;i<20;i++) {selectHe[i]=(i+0.1)+" m ～ "+(i+1.0)+" m";}
				    
				    for(;count<5;count++) {
				    	if(count==0) {
				    		o=JOptionPane.showInputDialog(pane, "タイプを選択してください","好みのタイプ", JOptionPane.INFORMATION_MESSAGE,icon, selectTy, selectTy[0]);
				    		if(o!=null) value[0] =o.toString();
				    	}
				    	else if(count==1) {
				    		o=JOptionPane.showInputDialog(pane, "種類を選択してください","好みの種類", JOptionPane.INFORMATION_MESSAGE,icon, selectTa, selectTa[0]);
				    		if(o!=null) {
			    			value[1] = o.toString();
			    			if(value[1].equals("鳥系"))value[1]=("飛行");
				    		else if(value[1].equals("鉱物系"))value[1]=("鉱物");
				    		else if(value[1].equals("陸上系"))value[1]=("陸上");
				    		else if(value[1].equals("水中（浅め）"))value[1]=("水中1");
				    		else if(value[1].equals("水中（中間）"))value[1]=("水中2");
				    		else if(value[1].equals("水中（深め）"))value[1]=("水中3");
				    		else if(value[1].equals("存在未発見"))value[1]=("未発見");
				    		}
				    	}
				    	else if(count==2) {
				    		o=JOptionPane.showInputDialog(pane, "高さを選択してください","好みの身長", JOptionPane.INFORMATION_MESSAGE,icon, selectHe, selectHe[0]);
				    		if(o==null||(value[2]=o.toString()).equals("何でもよい")); 
				    		else {value[2]=value[2].substring(0,value[2].indexOf("m")-1);}
				    	}
				    	else if(count==3) {
				    		o=JOptionPane.showInputDialog(pane, "重さを選択してください","好みの体重", JOptionPane.INFORMATION_MESSAGE,icon, selectWe, selectWe[0]);
				    		if(o==null||(value[3]=o.toString()).equals("何でもよい"));
				    		else{
				    			value[3] = o.toString();
					    		value[3]=value[3].substring(0,value[3].indexOf("k")-1);
				    		}
				    	}
				    	else {
				    		o=JOptionPane.showInputDialog(pane, "地方を選択してください","出身地方", JOptionPane.INFORMATION_MESSAGE,icon, selectAr, selectAr[0]);
				    		if(o!=null) {
				    			value[4] = o.toString();
					    		if(value[4].contains("関東")) {value[4]="151";T=1;}
					    		else if(value[4].contains("近畿")) {value[4]="251";T=152;}
					    		else if(value[4].contains("九州")) {value[4]="386";T=252;}
					    		else if(value[4].contains("北海道")) {value[4]="493";T=387;}
					    		else if(value[4].contains("ニューヨーク")) {value[4]="649";T=494;}
					    		else if(value[4].contains("フランス")) {value[4]="721";T=650;}
					    		else if(value[4].contains("ハワイ")) {value[4]="807";T=722;}
					    		else if(value[4].contains("イギリス")) {value[4]="898";T=808;}
					    		else if(value[4].contains("何でもよい")) {value[4]="898";T=1;}
				    		}
				    	}
							    if (o == null){
							      label.setText("取消が押されました");
							      int ans=JOptionPane.showConfirmDialog( pane, "全て取り消しますか？","",JOptionPane.YES_NO_OPTION);
							      if(ans==0) {
							    	  count=0;
							    	  tfM3.setText("");
							    	  label.setText("全て取り消されました");
							      }
							      skip=1;
							      break;
							    }else{
							    String r=o.toString().replace("(時間が掛かるのでオススメしません)","");
							    r=r.replaceAll("日本:",""); r=r.replace("アメリカ:",""); r=r.replace("イギリス:",""); r=r.replace("　","");
							      tfM3.append(r+"\n");
							    }
				    }
				    if(skip==0) {
				    	Search search=new Search();
				    	ArrayList<String> list=new ArrayList<String>(); 
				    	ArrayList<Integer> no=new ArrayList<Integer>(); 
				    	for(;T<=Integer.parseInt(value[4]);T++) {
				    		int ii=1;
				    		String[] info=search.Pokeinfo(T);
				    		String[] b=search.RetBvalue();
				    		for(;ii<30;ii++) {
				    			if(b[ii].equals("性別とタマゴ")) break;
				    		}
				    		
				    		if(value[0].equals("何でもよい"));
				    		else if(info[6].equals(value[0])==false&&info[7].equals(value[0])==false)continue;
				    		
				    		if(value[1].equals("何でもよい"));
				    		else if(info[ii+2].contains(value[1])==false)continue;
				    		
				    		if(value[2].equals("何でもよい"));
				    		else if((d0=Double.parseDouble(info[4].substring(0,info[4].indexOf("m"))))<(d1=Double.parseDouble(value[2]))||d0>(d1+0.9))continue;
				    		
				    		if(value[3].equals("何でもよい"));
				    		else if((d0=Double.parseDouble(value[3]))<100) if(d0>(d1=Double.parseDouble(info[5].substring(0,info[5].indexOf("k"))))||d1>(d0+9.9))continue;
				    		else if(d0>(d1=Double.parseDouble(info[5].substring(0,info[5].indexOf("k"))))||d1>(d0+99.9))continue;
				    		
				    		no.add(Integer.parseInt(info[3]));
				    		list.add(info[1]);
				    	}
				    	if(list.size()!=0&&list!=null) {
				    		String[] list2=new String[5];
				    		int iii=list.size();
					    	for(int i=0,ii=0;i<iii&&i<5;i++) {
					    		list2[i]=list.get(ii=random.nextInt(list.size()));
					    		list.remove(ii);
					    		if(i==0) no.set(0,no.get(ii));
					    	}
				    		if(list2.length<5) ini=list2.length+2;
				    		else ini=6;
				    		String[] lists=new String[ini];
					    	lists[0]= "対象のポケモンは "+list2[0]+" です。";lists[1]="他には";
					    	for(int i=1;i<(ini-2);i++) {
					    		lists[i+1]=list2[i];
					    		if(i==3)break;
					    	}
					    	if(iii<5)lists[ini-1]="　　　がいます";
					    	else lists[ini-1]="　　　等 合計 "+iii+" 体います";
					    	Object[] listL= {lists};
					    	JOptionPane.showMessageDialog( pane, listL,"選ばれたポケモン",
						    		  JOptionPane.PLAIN_MESSAGE,new ImageIcon( "pokemonfile\\image"+no.get(0)+".png" ));
					    	 new MakesoftArea(list2[0].toString(),no.get(0));
				    	}
				    	else {
				    		JOptionPane.showMessageDialog( pane, "検索しましたが、合致するポケモンは存在しませんでした","選ばれたポケモン",
					    			JOptionPane.QUESTION_MESSAGE);
				    	}
				    	count=0;
				    	tfM3.append("\n");
				    }
	    }
}
