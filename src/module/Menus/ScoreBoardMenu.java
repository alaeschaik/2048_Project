package module.Menus;

import module.Score.Score;
import module.Score.ScoreBoard;
import module.Score.ScoreBoardEmptyException;
import module.Server.Server;
import module.Server.ServerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoardMenu {
    private JLabel ImageLabel;
    private JScrollBar scrollBar1;
    JFrame frame;
    private JPanel panel;
    private JComboBox sizeList;
    private JPanel optionPanel;
    private JPanel scorePanel;

    public ScoreBoardMenu() throws IOException, ClassNotFoundException {
        createUIComponents();
        frame = new JFrame("Menu");
        frame.add(optionPanel,BorderLayout.NORTH);
        frame.add(scorePanel,BorderLayout.SOUTH);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


    public static void main(String[] args) {
        try {
            ScoreBoardMenu oM = new ScoreBoardMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void createUIComponents() throws IOException, ClassNotFoundException {
        try {
            ImageLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("/resources/crown.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
       optionPanel = new JPanel(new GridLayout(2, 3));
        scorePanel = new JPanel();

        //Combobox initialization
        String[] scoreStrings = {"start","start", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        sizeList = new JComboBox(scoreStrings);
        sizeList.setSelectedIndex(1);
        sizeList.addActionListener(e -> {
            try {
               scorePanel.removeAll();
                Server.getHTML(sizeList.getSelectedIndex());
                System.out.println("lol");
                ScoreBoard.initializeGlobal(sizeList.getSelectedIndex());
                GridLayout layout = new GridLayout(ScoreBoard.scoreBoard.size(), 3);
                layout.setVgap(10); // vertical spacing
                JPanel scorePanelTemp = new JPanel(layout);
                for (Score score : ScoreBoard.scoreBoard) {
                    JLabel name_JLB = new JLabel(score.getName1());
                    name_JLB.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel score_JLB = new JLabel("" + score.getScore());
                    score_JLB.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel time_JLB = new JLabel(score.getGlobalTime());
                    time_JLB.setHorizontalAlignment(SwingConstants.CENTER);

                    scorePanelTemp.add(name_JLB);
                    scorePanelTemp.add(score_JLB);
                    scorePanelTemp.add(time_JLB);
                   scorePanel.add(scorePanelTemp);
                    frame.pack();

                }
            } catch (IOException | NullPointerException | ServerException | ClassNotFoundException e1) {
                JOptionPane.showMessageDialog(frame,
                        "No Scoreboard Entrys for size: " + sizeList.getSelectedIndex() + " yet",
                        "NoScoreBoardEntrysError",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });

        //textfield initialization
        JLabel selectTitle_JLB = new JLabel("Select");
        selectTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sizeTitle_JLB = new JLabel("size");
        sizeTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel nameTitle_JLB = new JLabel("Name");
        nameTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel scoreTitle_JLB = new JLabel("Score");
        scoreTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel timeTitle_JLB = new JLabel("Time");
        timeTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);

        // sets the titles to bold
        Font font = nameTitle_JLB.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());

        nameTitle_JLB.setFont(boldFont);
        scoreTitle_JLB.setFont(boldFont);
        timeTitle_JLB.setFont(boldFont);

        // adding the title "bar"
        optionPanel.add(selectTitle_JLB);
        optionPanel.add(sizeTitle_JLB);
        optionPanel.add(sizeList);
        optionPanel.add(nameTitle_JLB);
        optionPanel.add(scoreTitle_JLB);
        optionPanel.add(timeTitle_JLB);
    }

}






