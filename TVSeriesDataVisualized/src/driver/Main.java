package driver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.*;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Main{
	
	 
	 
	
	private static HttpURLConnection connection;
	public static void main(String[] args) throws IOException {
		int totalReq = 0;
		int reqPerRun = 8;
		Chart m = new Chart();
		
		//This is used to get the ID of the Doctor Who series 
		OkHttpClient client = new OkHttpClient();

		 Request request = new Request.Builder()
		 	.url("https://imdb8.p.rapidapi.com/title/find?q=Doctor%20Who")
		 	.get()
		 	.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
		 	.addHeader("x-rapidapi-key", "7985971783mshcbfa8d0de274a59p1f40d4jsn29ea8b791ee8")
		 	.build();

		 Response response = client.newCall(request).execute();
		 reqPerRun++;
		 //Parse through the JSON and extract the ID of the Doctor Who series
		 String jsonStr = response.body().string();
			System.out.println("this is it: "+jsonStr);
			
			JSONObject obj = new JSONObject(jsonStr);
			
			String showID = obj.getJSONArray("results").getJSONObject(0).getString("id").
				substring(7,obj.getJSONArray("results").getJSONObject(0).getString("id").length()-1);
			
			
			
		for(int i=1;i<2;++i)
		{	
			m.createSeasonSquare(i);
			
			OkHttpClient clientEpAmount = new OkHttpClient();

			clientEpAmount.setConnectTimeout(60, TimeUnit.SECONDS);
			clientEpAmount.setReadTimeout(60, TimeUnit.SECONDS);
			clientEpAmount.setWriteTimeout(60, TimeUnit.SECONDS);
			
			Request requestEpAmount = new Request.Builder()
				.url("https://imdb8.p.rapidapi.com/title/get-seasons?tconst="+showID)
				.get()
				.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
				.addHeader("x-rapidapi-key", "7985971783mshcbfa8d0de274a59p1f40d4jsn29ea8b791ee8")
				//alternate key be1981c7demsh84517f72732cd32p1a7d36jsnd7c884bcae4f 
				//currently using dads so i can go over 1k requests per month
				.build();

			Response responseEpAmount = clientEpAmount.newCall(requestEpAmount).execute();
			reqPerRun++;
			String jsonStrEpAmount = responseEpAmount.body().string();
			
			JSONArray objEpAmount = new JSONArray(jsonStrEpAmount);
			
			//Extract number of episodes of current season
			int epNum = objEpAmount.getJSONObject(i-1).getJSONArray("episodes").length();
			//epNum+1 for loop 
			 for(int j=1;j<2;++j)
			  {
				 
				//This allows us to extract the ID of season 1 episode 1
					OkHttpClient client2 = new OkHttpClient();

					client2.setConnectTimeout(60, TimeUnit.SECONDS);
					client2.setReadTimeout(60, TimeUnit.SECONDS);
					client2.setWriteTimeout(60, TimeUnit.SECONDS);
					
					Request request2 = new Request.Builder()
						.url("https://imdb8.p.rapidapi.com/title/get-seasons?tconst="+showID)
						.get()
						.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
						.addHeader("x-rapidapi-key", "7985971783mshcbfa8d0de274a59p1f40d4jsn29ea8b791ee8")
						//alternate key be1981c7demsh84517f72732cd32p1a7d36jsnd7c884bcae4f 
						//currently using dads so i can go over 1k requests per month
						.build();

					Response response2 = client2.newCall(request2).execute();
					reqPerRun++;
					String jsonStr2 = response2.body().string();
					/*
					 * 
					 * Maybe make this into a bigger project where it can take input for show, season amount, ep amount
					 */
					JSONArray obj2 = new JSONArray(jsonStr2);
					
					String ep1ID = obj2.getJSONObject(i-1).getJSONArray("episodes").getJSONObject(j-1).getString("id")
					.substring(7, obj2.getJSONObject(i-1).getJSONArray("episodes").getJSONObject(j-1).getString("id").length()-1);

					//Extract the rating of the Doctor Who episode
					OkHttpClient client3 = new OkHttpClient();

					client3.setConnectTimeout(60, TimeUnit.SECONDS);
					client3.setReadTimeout(60, TimeUnit.SECONDS);
					client3.setWriteTimeout(60, TimeUnit.SECONDS);
					
					Request request3 = new Request.Builder()
						.url("https://imdb8.p.rapidapi.com/title/get-ratings?tconst="+ep1ID)
						.get()
						.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
						.addHeader("x-rapidapi-key", "7985971783mshcbfa8d0de274a59p1f40d4jsn29ea8b791ee8")
						.build();

					Response response3 = client.newCall(request3).execute();
					reqPerRun++;
					String jsonStr3 = response3.body().string();
					
					JSONObject obj3 = new JSONObject(jsonStr3);
					//The rating of the current Doctor Who Episode
					double rating = obj3.getDouble("rating");
				 
					
					switch(i)
					{
					case 1:
						m.getRow1().add(m.createEpisodePanel(rating));
						break;
					case 2:
						m.getRow2().add(m.createEpisodePanel(rating));
						break;
					case 3:
						m.getRow3().add(m.createEpisodePanel(rating));
						break;
					case 4:
						m.getRow4().add(m.createEpisodePanel(rating));
						break;
					case 5:
						m.getRow5().add(m.createEpisodePanel(rating));
						break;
					case 6:
						m.getRow6().add(m.createEpisodePanel(rating));
						break;
					case 7:
						m.getRow7().add(m.createEpisodePanel(rating));
						break;
					case 8:
						m.getRow8().add(m.createEpisodePanel(rating));
						break;
					case 9:
						m.getRow9().add(m.createEpisodePanel(rating));
						break;
					case 10:
						m.getRow10().add(m.createEpisodePanel(rating));
						break;
					case 11:
						m.getRow11().add(m.createEpisodePanel(rating));
						break;
					case 12:
						m.getRow12().add(m.createEpisodePanel(rating));
						break;
					default:
						break;
					
					}
					
					
			  }
		}
		System.out.println("Amount of requests executed: "+reqPerRun);
		System.out.println("Program Finished!");
}
}
