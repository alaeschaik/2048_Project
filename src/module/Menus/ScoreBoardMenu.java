package module.Menus;

import module.Score.Score;
import module.Score.ScoreBoard;
import module.Score.ScoreBoardEmptyException;
import module.Server.ServerException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Biko on 12.03.2016.
 */
public class ScoreBoardMenu {

    JFrame frame;
    private JComboBox sizeList;
    private JPanel optionPanel;
    private JPanel scorePanel;

    public ScoreBoardMenu(boolean global) throws IOException, ClassNotFoundException {
        frame = new JFrame("Menu");
        if (global) createUIComponentsGlobal();
        else createUIComponentsLocal();


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void createUIComponentsLocal() {
        try {
            ScoreBoard.initializeLocal();
        } catch (IOException | ClassNotFoundException | ScoreBoardEmptyException e) {
            e.printStackTrace();
        }
        GridLayout layout = new GridLayout(ScoreBoard.scoreBoard.size(), 3);
        layout.setVgap(10); // vertical spacing
        scorePanel = new JPanel(layout);
        optionPanel = new JPanel(new GridLayout(1,2));

        JLabel nameTitle_JLB = new JLabel("Name");
        nameTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel scoreTitle_JLB = new JLabel("Score");
        scoreTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sizeTitle_JLB = new JLabel("tableSize");
        sizeTitle_JLB.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = nameTitle_JLB.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());

        nameTitle_JLB.setFont(boldFont);
        scoreTitle_JLB.setFont(boldFont);
        sizeTitle_JLB.setFont(boldFont);
        for (Score score : ScoreBoard.scoreBoard) {
            JLabel name_JLB = new JLabel(score.getName1());
            name_JLB.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel score_JLB = new JLabel("" + score.getScore());
            score_JLB.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel size_JLB = new JLabel("" + score.getTableSize());
            size_JLB.setHorizontalAlignment(SwingConstants.CENTER);
            scorePanel.add(name_JLB);
            scorePanel.add(size_JLB);
            scorePanel.add(score_JLB);

        }

        optionPanel.add(nameTitle_JLB);
        optionPanel.add(sizeTitle_JLB);
        optionPanel.add(scoreTitle_JLB);
        frame.add(optionPanel, BorderLayout.NORTH);
        frame.add(scorePanel, BorderLayout.SOUTH);
        frame.pack();
    }


    private void createUIComponentsGlobal() throws IOException, ClassNotFoundException {
        optionPanel = new JPanel(new GridLayout(2, 3));
        scorePanel = new JPanel();
        frame.add(optionPanel, BorderLayout.NORTH);
        frame.add(scorePanel, BorderLayout.SOUTH);
        //Combobox initialization
        String[] scoreStrings = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};
        sizeList = new JComboBox<>(scoreStrings);
        sizeList.setSelectedIndex(0);
        sizeList.addActionListener(e -> {
            try {
                scorePanel.removeAll();
                //System.out.println(Integer.parseInt(String.valueOf(sizeList.getSelectedItem())));
//                Server.getHTML(Integer.parseInt(String.valueOf(sizeList.getSelectedItem())));
                ScoreBoard.initializeGlobal(Integer.parseInt(String.valueOf(sizeList.getSelectedItem())));
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
                        "No Scoreboard Entrys for size: " + Integer.parseInt(String.valueOf(sizeList.getSelectedItem())) + " yet",
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






