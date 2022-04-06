import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

/**
 * All Listener for the GameField e.g. for clicking one Field.
 */

public class Listener implements ActionListener {

    GameSummary gameFrame;
    GameField   gameField;
    int xCord = 0;
    int yCord = 0;
    StringTokenizer tokenizer;

    Listener( GameSummary gameFrame, GameField gameField ) {
        this.gameFrame = gameFrame;
        this.gameField = gameField;
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        tokenizer = new StringTokenizer(e.getActionCommand(), ",");
        xCord = Integer.parseInt(tokenizer.nextToken()) - 1;
        yCord = Integer.parseInt(tokenizer.nextToken()) - 1;

        System.out.println(xCord + " " + yCord);
        //Test if clicked
        if (gameField.getPlayground().getaShips()[xCord][yCord] == 1) {
            gameField.getPlayground().getaShips()[xCord][yCord]++;
            gameField.getPlayground().destroyShip(xCord+1,yCord+1);
            System.out.println("ship hitted");
        } else
            gameFrame.changeView();
    }
}
