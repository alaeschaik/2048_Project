package module.Menus;

import module.Logic.Calc;
import module.Score.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static module.Logic.Calc.getTableSize;

/**
 * Created by Biko on 07.06.2016.
 * <p>
 * Drawing the playing field
 */
public class Tiledrawer extends JPanel {


    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Impact";
    private static final int TILE_SIZE = 64;
    private static final int TILES_MARGIN = 16;
    private Calc game2048;
    private int guiX;
    private int guiY;
    private GameGui gG;
    {

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) { // delivers you which key was pressed

                    case KeyEvent.VK_ESCAPE:
//                        gG.dispose();
                        gG.dispose();

                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        game2048.onKeyPressLeftNew();
                        break;

                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        game2048.onKeyPressRightNew();
                        break;

                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        game2048.onKeyPressDownNew();
                        break;

                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        game2048.onKeyPressUpNew();
                        break;
                }
                repaint();
            }


        });

    }


    public Calc getGame2048() {
        return game2048;
    }

    public Tiledrawer(int tableSize,GameGui gG) {
        this.gG=gG;
        game2048 = new Calc(tableSize);
    }

    public Tiledrawer(boolean useBackup,GameGui gG)throws IOException, ClassNotFoundException {
        this.gG=gG;
        game2048 = new Calc(useBackup);
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int x = 0; x < getTableSize(); x++) {
            for (int y = 0; y < getTableSize(); y++) { //every single tile gets drawn separately
                drawTile(g, x, y);

            }
        }
    }

    private void drawTile(Graphics g2, int x, int y) {
        Graphics2D g = ((Graphics2D) g2);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        int highScore = !ScoreBoard.scoreBoard.isEmpty() ? ScoreBoard.scoreBoard.get(0).getScore() : 0;
        Font gO_FTN = new Font("Arial", Font.BOLD, 26);
        int value = game2048.table[y][x];
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y) + 25;
//if the game is not over yet
        if (!game2048.eOG) {
            final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;

            g.setColor(new Color(0, 0, 0, 25));

            g.fillRoundRect(xOffset + 8, yOffset + 8, TILE_SIZE, TILE_SIZE, 14, 14);
            g.setColor(getBackground(game2048.table[y][x]));
            g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);

            g.setColor(getForeground(game2048.table[y][x]));

            final Font font = new Font(FONT_NAME, Font.BOLD, size);
            g.setFont(font);

            String s = String.valueOf(value);
            final FontMetrics fm = getFontMetrics(font);


            final int w = fm.stringWidth(s);
            final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];
            if (value != 0)
                g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);

            g.setFont(new Font(FONT_NAME, Font.PLAIN, 25));
            g.drawString("Score: " + game2048.getScoreValue(), guiX + 10, guiY + 30);


            if (game2048.getScoreValue()> highScore) {
                g.setColor(new Color(51, 255, 43, 150));
                g.drawString("HighScore: " + game2048.getScoreValue(), getWidth() - (getFontMetrics(new Font(FONT_NAME, Font.PLAIN, 25)).stringWidth("HighScore" + game2048.getScoreValue())) - 20, guiY + 30);
                g.setColor(new Color(0, 0, 0, 25));
            } else {
                g.drawString("HighScore: " + highScore, getWidth() - (getFontMetrics(new Font(FONT_NAME, Font.PLAIN, 25)).stringWidth("HighScore" + highScore)) - 20, guiY + 30);
            }


            //end of !eoG statement

            //if the game is over & the new score is higher than the old one, you "win"
        } else if (game2048.iseOG()&& game2048.getScoreValue()> highScore) {
            g.setColor(new Color(255, 255, 255, 10));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(78, 139, 202));
            g.setFont(gO_FTN);
            g.drawString("Congratulations!!!", getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Congratulations!!!") / 2), getHeight() / 2 - 120);
            g.drawString("You beat the highscore for this size!",
                    getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("You beat the highscore for this size") / 2), getHeight() / 2 - 70);
            g.setColor(new Color(138, 255, 98));
            g.drawString("Your Score: " + game2048.getScoreValue(), getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Your Score:" + game2048.getScoreValue()) / 2), getHeight() / 2);
            Image icon = new ImageIcon("src/resources/confetti.gif").getImage();
            g.drawImage(icon, (getWidth() - icon.getWidth(null)) / 2, (getHeight() - icon.getWidth(null)) / 2, this);
            g.setColor(new Color(128, 128, 128, 128));
            g.drawString("Press ESC to save the score", getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Press ESC to save score") / 2), getHeight() - 15);

            //if the game is over & the new score is higher than the old one, you "lose"
        } else if (game2048.iseOG() && game2048.getScoreValue()< highScore) {
            g.setColor(new Color(255, 255, 255, 15));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(new Color(78, 139, 202));
            g.setFont(gO_FTN);

            g.drawString("Game over!!!", getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Game over!!!") / 2), getHeight() / 2 - 120);
            g.drawString("Better luck next time!", getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Better luck next time!") / 2), getHeight() / 2 - 70);
            g.setColor(new Color(255, 98, 68));
            g.drawString("Your Score: " + game2048.getScoreValue(), getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Your Score:" + game2048.getScoreValue()) / 2), getHeight() / 2);
            g.setColor(new Color(128, 128, 128, 128));
            Image icon = new ImageIcon("src/resources/rain.gif").getImage();
            g.drawImage(icon, (getWidth() - icon.getWidth(null)) / 2, (getHeight() - icon.getWidth(null)) / 2, this);
            g.drawString("Press ESC to save score", getWidth() / 2 - (getFontMetrics(gO_FTN).stringWidth("Press ESC to save score") / 2), getHeight() - 15);

        }

    }

    public static Color getBackground(int value) {

        switch (value) {

            case 0:
                return new Color(0xcdc1b4);
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);

        }
        return new Color(0xcdc1b4 * value);
//        return new Color (value * new Random ().nextInt (100) * 100);
    }





    public static Color getBgColor() {
        return BG_COLOR;
    }

    public static String getFontName() {
        return FONT_NAME;
    }

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getTilesMargin() {
        return TILES_MARGIN;
    }

    /**
     * Tiledrawer Back & Foreground
     */

    public static Color getForeground(int value) {
        return value < 16 ? new Color (0x776e65) : new Color (0xf9f6f2);
    }

    public static int offsetCoors(int arg) {
        return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
    }



    public static void setRange(int range) {
        Calc.range = range;
    }

    public int getGuiX() {
        return  guiX;
    }

    public void setGuiX(int guiX) {
        this.guiX = guiX;
    }

    public int getGuiY() {
        return  guiY;

    }

    public void setGuiY(int guiY) {
        this.guiY = guiY;
    }

}
