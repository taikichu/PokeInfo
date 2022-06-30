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
public class Homepage_Getnewdata {
	/** ポケモン：トピックスのリスト */
	private ArrayList<Pokemon> productList;
	/** コンストラクタ */
	public Homepage_Getnewdata(String NINTENDO_HOMEPAGE) {
		productList = new ArrayList<Pokemon>();
		// TLS v1.2 の有効化 (Java 8 以降では指定不要)
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		try {
			// URLオブジェクトを生成
			URL url = new URL(NINTENDO_HOMEPAGE);
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
			NodeList itemList = (NodeList)xPath.evaluate("//h:div[@class='nc3-l-grid__inner']/h:div",
					document, XPathConstants.NODESET);
			for(int i = 0; i < itemList.getLength(); i++) {	
				Node itemNode= itemList.item(i);  
				String title = xPath.evaluate("h:div//h:div[@class='nc3-c-articleCard__name']", itemNode);
				String jURL= "https://topics.nintendo.co.jp/list"+xPath.evaluate("h:div//h:a/@href", itemNode);
				// PokemonProduct のインスタンスを生成し、リストに追加
				if(title.contains("ポケットモンスター")||title.contains("ポケモン")) {
						productList.add(new Pokemon(title,jURL));
					}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * トピックスのリストを返す
	 * @return トピックスのリスト
	 */
	public ArrayList<Pokemon> getProductList() {
		return productList;
	}
}/** ポケモンの情報 */
class Pokemon {
	private static int count=0;
	/** タイトル */
	private String title;

	/** URL */
	private String jURL;

	/**
	 * コンストラクタ
	 * @param title タイトル
	 * @param jURL URL
	 */
	public Pokemon(String title,String jURL) {
		this.title = title;
		this.jURL=jURL;
	}
	public String getTitle() {
		return this.title;
	}
	public String getURL() {
		return this.jURL;
	}
	/**
	 * ポケモンの文字列表現を返す
	 * @return ポケモン情報の文字列表現
	 */
	public String toString() {
		return "\n"+(++count)+"　記事のタイトル："+title+"\nURL:"+jURL+"\n";
	}
}