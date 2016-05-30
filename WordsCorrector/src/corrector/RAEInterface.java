package corrector;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class RAEInterface {
	private final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.86 Safari/537.36";
	private String url = "http://dle.rae.es/srv/search?w=%s";
	private static RAEInterface instance;
	private HttpClient client;

	private RAEInterface() {
		client = HttpClientBuilder.create().build();
	}

	public static RAEInterface getInstance() {
		if (instance == null) {
			instance = new RAEInterface();
		}

		return instance;
	}

	public String searchWord(String word) {
		StringBuffer responseText = new StringBuffer();
		HttpResponse response;
		BufferedReader bufferedReader;
		String line = "";

		try {
			HttpGet request = new HttpGet(String.format(url, word));
			request.addHeader("User-Agent", USER_AGENT);
			response = client.execute(request);
			bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			while ((line = bufferedReader.readLine()) != null) {
				responseText.append(line);
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseText.toString();
	}

}
