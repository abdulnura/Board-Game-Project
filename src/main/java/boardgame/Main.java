package boardgame;


import boardgame.View.ConsoleMenuView;

import java.util.logging.Logger;

public class Main {


    public static void main(String[] args) throws Exception {

        Logger logger
                = Logger.getLogger(
                Main.class.getName());

        logger.info("Game Loading ....");
        ConsoleMenuView.showMenuWithResult();


    }
}









