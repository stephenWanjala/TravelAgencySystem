package github.stephenwanjala.travelagency;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        var mainFrame= new MainFrame();
    }
}

class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Travel Agency");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}