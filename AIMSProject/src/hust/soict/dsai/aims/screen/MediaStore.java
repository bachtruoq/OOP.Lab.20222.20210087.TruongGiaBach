package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MediaStore extends JPanel{
	private Media media;
	private JButton playbutton = new JButton("Play");
	private JButton addtocart = new JButton("Add to cart");
	
	public JButton getAddtocart() {
		return addtocart;
	}

	public Media getMedia() {
		return media;
	}

	public MediaStore(Media media) {
		
		this.media = media;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel(media.getTitle());
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel cost = new JLabel("" + media.getCost() + " $");
		cost.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		container.add(addtocart);
		if (media instanceof Playable) {
			container.add(playbutton);
			playbutton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						((Playable)media).play();
	            		JDialog d = new JDialog();
	                    d.setLayout(new FlowLayout());
	                    d.setTitle(media.toString());
	                    JLabel l = new JLabel("Currently Playing...");
	                    d.add(l);
	                    JButton b = new JButton("Cancel");  
	                    b.addActionListener (new ActionListener(){
	                        public void actionPerformed(ActionEvent e)  
	                        {  
	                            d.dispose();  
	                        }  
	                    });
	                    d.add(b);   
	                    d.setSize(1000, 100);
	                    d.setVisible(true);
					}catch(PlayerException ex) {
						ex.printStackTrace();
					}
				}
			});
		}
		
		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(cost);
		this.add(Box.createVerticalGlue());
		this.add(container);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
