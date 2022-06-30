package useCodePoke;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import nu.validator.htmlparser.dom.HtmlDocumentBuilder;
import test.NamespaceContextHTML;

/** ポケモンの新着商品情報 */
public class Yahoopoke {
	/** ポケモン：金額のリスト */
	private ArrayList<String> titleList;
	/** コンストラクタ */
	public Yahoopoke(String softname) {
		String YAHOO_SHOPPAGE="https://shopping.yahoo.co.jp/search?first=1&ss_first=1&ts=1641184763&mcr=82ca37a8336844dcb6c47cbf9c3e20f2&tab_ex=commerce&sretry=1&area=13&astk=&aq=&oq=&p=ポケットモンスター+ソフト+"+softname+" &sc_i=shp_pc_search_searchBox_2";
		titleList = new ArrayList<String>();
		// TLS v1.2 の有効化 (Java 8 以降では指定不要)
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		try {
			// URLオブジェクトを生成
			URL url = new URL(YAHOO_SHOPPAGE);
			// URLオブジェクトから、接続にいくURLConnectionオブジェクトを取得
			URLConnection connection = url.openConnection();
			// 接続
			connection.connect();
			// サーバからやってくるデータをInputStreamとして取得
			InputStream inputStream = connection.getInputStream();
			// 次に inputStream を読み込む InputStreamReader のインスタンス inputStreamReader を生成
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			// さらに inputStreamReader をラップする BufferedReader のインスタンス reader を生成
			BufferedReader reader = new BufferedReader(inputStreamReader);

			// DOMツリーの構築
			HtmlDocumentBuilder builder = new HtmlDocumentBuilder();
			Document document = builder.parse(new InputSource(reader));

			// XPath の表現を扱う XPath オブジェクトを生成
			XPath xPath = XPathFactory.newInstance().newXPath();
			// XPath 式内で接頭辞 h がついている要素を HTML の要素として認識
			xPath.setNamespaceContext(new NamespaceContextHTML());
			// 1つの新着商品に対応する li 要素のリストを得る
			NodeList itemList = (NodeList)xPath.evaluate("//h:li[@id='searchResults1']/h:div[1]/h:ul/h:li",
					document, XPathConstants.NODESET);
			int i=0;
				Node itemNode= itemList.item(i);  
				String title = xPath.evaluate("h:div//h:p[1]/h:span[1]", itemNode);
				titleList.add(title);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 金額を返す
	 * @return 金額を返す
	 */
	public ArrayList<String> gettitleList() {
		return titleList;
	}
}