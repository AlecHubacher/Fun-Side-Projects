package driver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Chart {
	
	JPanel pane = new JPanel();
	JPanel seriesData = new JPanel();
	JFrame frame = new JFrame("Doctor Who Data"); 
	static ArrayList<JPanel> seasonBar;
	
	public static void setUpSeasonBar(int size)
	{
		seasonBar = new ArrayList<JPanel>(size);
		
		for(int i=0;i<size;++i)
		{
			seasonBar.add(i, new JPanel(new FlowLayout(FlowLayout.LEFT)));
		}
	}
	
	
	public String createShowTitle(String title)
	{
		title = title.replaceAll(" ", "%20");
		return title;
		
	}
	
	
	
	public JPanel createEpisodePanel(double rating)
	{
		//Adding data to our chart
		JPanel episode = new JPanel();
		
		//Color for episode dependent on Episode Rating
		//this did have declaration of rating
		if(rating>=9.0)
		{
			episode.setBackground(Color.GREEN.darker());
		}
		else if(rating>=8.0)
		{
			episode.setBackground(Color.GREEN);
		}
		else if(rating>=6.5)
		{
			episode.setBackground(Color.ORANGE);
		}
		else if(rating>=0.0)
		{
			episode.setBackground(Color.RED);
		}
		episode.setPreferredSize(new Dimension(40,40));
		JLabel epRateLab = new JLabel(Double.toString(rating));
		episode.add(epRateLab);
		//TMDB API KEY: 0839c1ef6f0ce62432d8be9f3ac130a6
		//Green 9-10
		//Yellow 8-8.9
		//Orange 6.5-7.9
		//Red 0-6.4
		
		return episode;
	}
	public void createSeasonSquare(int seaNum)
	{
		JPanel season = new JPanel();
		JLabel seasonNum = new JLabel(Integer.toString(seaNum));
		season.add(seasonNum);
		season.setPreferredSize(new Dimension(40,40));
		season.setBackground(Color.BLACK);
		seasonNum.setForeground(Color.WHITE);
		
		
		seasonBar.get(seaNum-1).add(season);
	}
	
	public static String getImageUrl(String url)
	{
		return url;
	}
	
	public Chart(ImageIcon icon)
	{
		//JPanel pane = new JPanel();
				JPanel key; //This will be the panel that holds the key so the color information
				pane.setLayout(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				//Creating the Key Panel
				key = new JPanel();
				key.setLayout(new BoxLayout(key, BoxLayout.PAGE_AXIS));
				//Creating the different key components
				JPanel greatOption = new JPanel(new FlowLayout());
				JPanel color = new JPanel();
				color.setBackground(Color.GREEN);
				color.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				greatOption.add(color);
				
				JLabel colorText = new JLabel("Great");
				greatOption.add(colorText);
				key.add(greatOption);
				
				JPanel goodOption = new JPanel(new FlowLayout());
				JPanel color2 = new JPanel();
				color2.setBackground(Color.YELLOW);
				color2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				goodOption.add(color2);
				
				JLabel colorText2 = new JLabel("Good");
				goodOption.add(colorText2);
				key.add(goodOption);
				
				
				JPanel regOption = new JPanel(new FlowLayout());
				JPanel color3 = new JPanel();
				color3.setBackground(Color.ORANGE);
				color3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				regOption.add(color3);
				
				JLabel colorText3 = new JLabel("Regular");
				regOption.add(colorText3);
				key.add(regOption);
				
				JPanel greatOption2= new JPanel(new FlowLayout());
				JPanel color11 = new JPanel();
				color11.setBackground(Color.RED);
				color11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				greatOption2.add(color11);
				
				JLabel colorText11 = new JLabel("Bad");
				greatOption2.add(colorText11);
				key.add(greatOption2);
				
				
				c.fill = GridBagConstraints.HORIZONTAL;
				c.gridx = 4;
				c.gridy = 0; 
				c.anchor = GridBagConstraints.FIRST_LINE_END;
				pane.add(key, c);
				 
				
				//Heading i.e title and picture of Doctor Who
				JPanel heading = new JPanel();
				heading.setLayout(new BoxLayout(heading, BoxLayout.PAGE_AXIS));
				heading.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				heading.add(new JLabel(icon));
				c.fill = GridBagConstraints.NONE;
				c.weightx = 0.5;
				c.gridx = 2;
				c.gridy = 0;
				c.anchor=GridBagConstraints.PAGE_START;
				pane.add(heading, c);
				 
				 //Episode Bar
				//JPanel seriesData = new JPanel();
				seriesData.setLayout(new BoxLayout(seriesData, BoxLayout.PAGE_AXIS));
				seriesData.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				JPanel epBar = new JPanel();
				epBar.setLayout(new FlowLayout());
				
				//Add White Square to made good layout for chart
				JPanel whiteSq = new JPanel();
				whiteSq.setPreferredSize(new Dimension(40,40));
				whiteSq.setBackground(Color.WHITE);
				epBar.add(whiteSq);
				for(int i=0;i<25;++i)//should probs put 25 cuz 22 is the avg
				{
					JPanel ep = new JPanel();
					JLabel epNum = new JLabel(Integer.toString(i+1));
					ep.add(epNum);
					ep.setPreferredSize(new Dimension(40,40));
					ep.setBackground(Color.BLACK);
					epNum.setForeground(Color.WHITE);
					epBar.add(ep);
				}
				c.weightx=0;
				c.gridx=2;
				c.gridy=1;
				c.fill = GridBagConstraints.NONE;
				c.anchor=GridBagConstraints.PAGE_END;
				seriesData.add(epBar);
				pane.add(seriesData,c);
				
				
				//Adding panels to chart for data
				for(int i=0;i<seasonBar.size();++i)
				{
					seriesData.add(seasonBar.get(i));
				}
				
				
				/*PLAN FOR SEASON BAR IS TO ADD IT AS I ADD IN THE ACTUAL DATA ROW BY ROW
				 * 
				 * 
				 * 
				 */
				JLabel labTest = new JLabel("we here");
				epBar.add(labTest);
				
				
				/*key = new JPanel();
				JLabel lab4 = new JLabel("label 4");
				key.add(lab4);
				c.fill = GridBagConstraints.HORIZONTAL;
				//make this component tall
				c.weightx = 0.5;
				c.gridwidth = 3;
				c.gridx = 3;
				c.gridy = 0;
				pane.add(key, c);*/
				 
				key = new JPanel();
				JLabel lab5 = new JLabel("label 5");
				key.add(lab5);
				c.fill = GridBagConstraints.HORIZONTAL;
				c.ipady = 0;       //reset to default
				c.weighty = 1.0;   //request any extra vertical space
				c.anchor = GridBagConstraints.PAGE_END; //bottom of space
				//c.insets = new Insets(10,0,0,0);  //top padding
				c.gridx = 1;       //aligned with key 2
				c.gridwidth = 2;   //2 columns wide
				c.gridy = 2;       //third row
				pane.add(key, c);
				
				//JFrame frame = new JFrame("working");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(pane);
				frame.setVisible(true);
				frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	public JPanel getPane() {
		return pane;
	}

	public JPanel getSeriesData() {
		return seriesData;
	}

	public JFrame getFrame() {
		return frame;
	}

	
}
