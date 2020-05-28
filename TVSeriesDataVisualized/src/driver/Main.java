package driver;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
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
	public static void main(String[] args) throws IOException, AWTException, InterruptedException {
		int totalReq = 0;
		int count = 0;
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter in TV Series");
		String tvSeries = s.nextLine();
		
		System.out.println("here is show: "+tvSeries);
		tvSeries = tvSeries.replaceAll(" ", "%20");
		System.out.println("new title: "+tvSeries);
		
		//This is used to get the ID of the Doctor Who series 
		OkHttpClient client = new OkHttpClient();

		 Request request = new Request.Builder()
		 	.url("https://imdb8.p.rapidapi.com/title/find?q="+tvSeries)
		 	.get()
		 	.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
		 	.addHeader("x-rapidapi-key", "YOUR API KEY")
		 	.build();

		 Response response = client.newCall(request).execute();
		 //Parse through the JSON and extract the ID of the Doctor Who series
		 String jsonStr = response.body().string();
			System.out.println("this is it: "+jsonStr);
			
			JSONObject obj = new JSONObject(jsonStr);
			
			String showID = obj.getJSONArray("results").getJSONObject(0).getString("id").
				substring(7,obj.getJSONArray("results").getJSONObject(0).getString("id").length()-1);
			
			OkHttpClient clientEpAmount = new OkHttpClient();

			clientEpAmount.setConnectTimeout(30, TimeUnit.SECONDS);
			clientEpAmount.setReadTimeout(30, TimeUnit.SECONDS);
			clientEpAmount.setWriteTimeout(30, TimeUnit.SECONDS);
			
			Request requestEpAmount = new Request.Builder()
				.url("https://imdb8.p.rapidapi.com/title/get-seasons?tconst="+showID)
				.get()
				.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
				.addHeader("x-rapidapi-key", "YOUR API KEY")
				.build();

			Response responseEpAmount = clientEpAmount.newCall(requestEpAmount).execute();
			String jsonStr2 = responseEpAmount.body().string();
			System.out.println("jsonStrEpAmount: "+jsonStr2);
			
			JSONArray obj2 = new JSONArray(jsonStr2);
			int amountSea = obj2.length();
			
			//We need this season bar to have its size before the instance is created
			Chart.setUpSeasonBar(amountSea);
			
			Chart m = new Chart();
		
		for(int i=1;i<amountSea+1;++i)
		{	
			m.createSeasonSquare(i);
			
			int epNum = obj2.getJSONObject(i-1).getJSONArray("episodes").length();
			
			//epNum+1 for loop 
			 for(int j=1;j<epNum+1;++j)
			  {
				 
				 String epID = obj2.getJSONObject(i-1).getJSONArray("episodes").getJSONObject(j-1).getString("id")
							.substring(7, obj2.getJSONObject(i-1).getJSONArray("episodes").getJSONObject(j-1).getString("id").length()-1);
				 
				 if(count==1)
				 {
					 TimeUnit.SECONDS.sleep(1);
					 count=0;
				 }

					//Extract the rating of the Doctor Who episode
					OkHttpClient client3 = new OkHttpClient();

					client3.setConnectTimeout(30, TimeUnit.SECONDS);
					client3.setReadTimeout(30, TimeUnit.SECONDS);
					client3.setWriteTimeout(30, TimeUnit.SECONDS);
					
					Request request3 = new Request.Builder()
						.url("https://imdb8.p.rapidapi.com/title/get-ratings?tconst="+epID)
						.get()
						.addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
						.addHeader("x-rapidapi-key", "YOUR API KEY")
						.build();

					Response response3 = client.newCall(request3).execute();
					count++;
					String jsonStr3 = response3.body().string();
					System.out.println("jsonStr3: "+jsonStr3);
					
					JSONObject obj3 = new JSONObject(jsonStr3);
					//The rating of the current Doctor Who Episode
					double rating = obj3.getDouble("rating");
				 
					Chart.seasonBar.get(i-1).add(m.createEpisodePanel(rating));	
			  }
		}
		System.out.println("Amount of requests executed: "+count);
		
		BufferedImage img = new BufferedImage(m.getFrame().getWidth(), m.getFrame().getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		m.getFrame().printAll(g2d);
		g2d.dispose();
		ImageIO.write(img,"png", new File("C:\\Users\\Alec\\Desktop\\imgeJava\\DataChartTV.png"));
		System.out.println("Program Finished!");
		
}
}
