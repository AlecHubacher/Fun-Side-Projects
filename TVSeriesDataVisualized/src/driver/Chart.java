package driver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
	JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row10 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row11 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel row12 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	

	
	public JPanel createEpisodePanel(double rating)
	{
		//Adding data to our chart
		JPanel episode = new JPanel();
		
		//Color for episode dependent on Episode Rating
		//this did have declaration of rating
		if(rating>=9.0)
		{
			episode.setBackground(Color.GREEN);
		}
		else if(rating>=8.0)
		{
			episode.setBackground(Color.YELLOW);
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
		
		switch(seaNum)
		{
		case 1:
			row1.add(season);
			break;
		case 2:
			row2.add(season);
			break;
		case 3:
			row3.add(season);
			break;
		case 4:
			row4.add(season);
			break;
		case 5:
			row5.add(season);
			break;
		case 6:
			row6.add(season);
			break;
		case 7:
			row7.add(season);
			break;
		case 8:
			row8.add(season);
			break;
		case 9:
			row9.add(season);
			break;
		case 10:
			row10.add(season);
			break;
		case 11:
			row11.add(season);
			break;
		case 12:
			row12.add(season);
			break;
		default:
			break;
		
		}
	}
	
	public Chart()
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
				heading.add(new JLabel(new ImageIcon("C:\\Users\\Alec\\Pictures\\Saved Pictures\\DoctorDoOver.jpg")));
				c.fill = GridBagConstraints.NONE;
				c.weightx = 0.5;
				c.gridx = 2;
				c.gridy = 0;
				c.anchor=GridBagConstraints.PAGE_START;
				pane.add(heading, c);
				//sTOP HERE IF MESS UP
				 
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
				for(int i=0;i<15;++i)
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
				seriesData.add(row1);
				seriesData.add(row2);
				seriesData.add(row3);
				seriesData.add(row4);
				seriesData.add(row5);
				seriesData.add(row6);
				seriesData.add(row7);
				seriesData.add(row8);
				seriesData.add(row9);
				seriesData.add(row10);
				seriesData.add(row11);
				seriesData.add(row12);
				
				
				
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

	public JPanel getRow1() {
		return row1;
	}

	public JPanel getRow2() {
		return row2;
	}

	public JPanel getRow3() {
		return row3;
	}

	public JPanel getRow4() {
		return row4;
	}

	public JPanel getRow5() {
		return row5;
	}

	public JPanel getRow6() {
		return row6;
	}

	public JPanel getRow7() {
		return row7;
	}

	public JPanel getRow8() {
		return row8;
	}

	public JPanel getRow9() {
		return row9;
	}

	public JPanel getRow10() {
		return row10;
	}

	public JPanel getRow11() {
		return row11;
	}

	public JPanel getRow12() {
		return row12;
	}

	
}
