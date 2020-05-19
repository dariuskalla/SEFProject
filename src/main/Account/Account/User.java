package Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class User extends JFrame implements ActionListener {
    private JMenuBar menubar;
    private JMenu mnuView;
    private JMenuItem trainerDetails;
    private JMenuItem trainingList;
    public static JDesktopPane desktop;

    public User() {
        super("Fitness Platform");
        this.setSize(Settings.getScreenSize().width, Settings.getScreenSize().height - 30);
        this.setDefaultCloseOperation(3);
        this.setExtendedState(6);
        desktop = new JDesktopPane();
        this.menubar = new JMenuBar();
        this.mnuView = new JMenu("View");
        this.trainerDetails = new JMenuItem("Trainer Details");
        this.trainingList = new JMenuItem("Trainings List");
        this.mnuView.add(this.trainingList);
        this.mnuView.add(this.trainerDetails);
        this.menubar.add(this.mnuView);
        this.trainingList.addActionListener(this);
        this.trainerDetails.addActionListener(this);
        this.setJMenuBar(this.menubar);
        this.add(desktop);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.trainingList) {
                //implement list of trainings
            }

            if (e.getSource() == this.trainerDetails) {
                User user = new User();
                desktop.add(user);
                user.setVisible(true);
            }
        } catch (Exception var3) {
            JOptionPane.showMessageDialog(new JFrame(), "Error, Cannot load window" + var3.toString(), "Error", 0);
        }

    }
}
