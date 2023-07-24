//imports
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

public class MapScreen extends JFrame {

    //creates the engine as well as the browser window
    public static void main(String[] args) {
        // Creating and running Chromium engine
        EngineOptions options =
                EngineOptions.newBuilder(HARDWARE_ACCELERATED).build();
        Engine engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.OFF_SCREEN)
                .licenseKey("1BNDHFSC1G54DGDNF68S9EVZYKMGCM4OUL9BFJ7W4IL3UX6OBY3UGNZEP7Z18H5KJSWXZ9").build());
        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            // Creating Swing component for rendering web content
            // loaded in the given Browser instance.
            BrowserView view = BrowserView.newInstance(browser);

            // Creating and displaying Swing app frame.
            JFrame frame = new JFrame("University Maps");

            JLabel background = new JLabel(new ImageIcon("/Users/happysoloxy/Post Secondary App/.idea/Images/programBackground.jpeg"));
            background.setBounds(0,0,1920,1080);
            frame.add(background);


            // Close Engine and onClose app window
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    engine.close();
                }
            });
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JTextField addressBar = new JTextField("/Users/happysoloxy/Post Secondary App/src/.idea/map.html");
            addressBar.addActionListener(e ->
                    browser.navigation().loadUrl(addressBar.getText()));

            //creates button to refresh the page
            JButton returnButton = new JButton("Return");
            returnButton.setBounds(2000,900,100,100);
            returnButton.addActionListener(e-> {
                browser.navigation().loadUrl("/Users/happysoloxy/Post Secondary App/src/.idea/map.html");
            });


            JButton startingButton = new JButton("Start Screen");
            JButton informationScreen = new JButton("Information Screen");


            JPanel panel1 = new JPanel();
            panel1.setLayout(new BorderLayout(150, 50));
            //panel1.setLocation(500,100);
            JLabel titleLabel = new JLabel();
            panel1.setSize(1980,300);
            titleLabel.setHorizontalAlignment(0);

            panel1.add(returnButton);
            panel1.add(titleLabel, BorderLayout.NORTH);


            JPanel bigPanel = new JPanel();
            bigPanel.setLayout(new BorderLayout(0, 0));
            panel1.add(bigPanel, BorderLayout.CENTER);

            JPanel mapPanel = new JPanel();
            mapPanel.setLayout(new BorderLayout(100, 0));
            mapPanel.setMaximumSize(new Dimension(1320, 902));
            bigPanel.add(mapPanel, BorderLayout.CENTER);

            mapPanel.add(view);


            frame.setSize(1920, 1080);
            frame.setLocationRelativeTo(null);


            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.getContentPane().add(panel1);
            bigPanel.add(returnButton,BorderLayout.NORTH);

            bigPanel.add(Box.createHorizontalStrut(20), BorderLayout.WEST);



            frame.setVisible(true);
            

            browser.navigation().loadUrl(addressBar.getText());


        });
    }
}