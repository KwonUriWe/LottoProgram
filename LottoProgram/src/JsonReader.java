import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReader {

	public JSONObject connectionUrlToJSON(String turn) throws Exception  {
		
		try {
		//url 불러오기	//구글링
		URL url = new URL("https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + turn);
		HttpsURLConnection conn = null;
		HostnameVerifier hostV = new HostnameVerifier() {
			@Override			//자동완성됨
			public boolean verify(String hostname, SSLSession session) {
				return true;	//모든 서버를 신뢰한다
			}
		};
		HttpsURLConnection.setDefaultHostnameVerifier(hostV);
		
		//연결
		conn = (HttpsURLConnection)url.openConnection();
		BufferedReader jReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String iLine = jReader.readLine();
		JSONParser ps = new JSONParser();
		JSONObject jo = (JSONObject)ps.parse(iLine);
		return jo;
		}
		catch (Exception e) {
			System.out.println("접속 실패");
			return null;
		}
	}
}
