import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class SpamBot extends JFrame
{
	public static final int HEIGHT = 500;
	public static final int WIDTH = 400;
	private JTextArea text; 
	private JPanel panel;
	private JButton exportText;
	private JButton importText;
	private JTextField duration;
	private FlowLayout layout;
	private JButton spamButton;
	public SpamBot()
	{
		super("Joey's spam bot!");
		
		
		setSize(HEIGHT, WIDTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creating buttons 
		
		text = new JTextArea("Enter your text here", 3, 3);
		panel = new JPanel();
		exportText = new JButton("export");
		@Override
		exportText.addActionListener(e -> {
			
		})
			
		importText = new JButton("import");
		duration = new JTextField("Duration");
		spamButton = new JButton("Spam");
		layout = new FlowLayout();
		
		//adding them to the pane
		
		panel.setLayout(layout);
		panel.add(text);
		panel.add(importText);
		panel.add(exportText);
		panel.add(spamButton);
		panel.add(duration);
		//adding pane to frame
		
		add(panel);
		setVisible(true);
		pack();
		
		//panel.addActionListener()
		
		
	}
	public static void main(String[] args)
	{
		SpamBot spambot = new SpamBot();
		
	}
    
    
}