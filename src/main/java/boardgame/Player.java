package boardgame;

import boardgame.helper.JAXBHelper;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.FileOutputStream;


@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType
@XmlRootElement
@XmlType(propOrder = {"name", "figure", "score", "startTime"})
public class Player {

    public static Object main;
    private String name;
    private Figure figure;
    private int score;
    private int startTime;

    public static void main(String[] args) throws Exception {
        Player player = new Player();

        Player Player;
        Player = new boardgame.Player();
        Player.setName(player.name);
        Player.setFigure(Player.figure);
        Player.setScore(player.score);
        Player.setStartTime(player.startTime);
        JAXBHelper.toXML(Player, System.out);
        JAXBHelper.toXML(Player, new FileOutputStream("Save.txt"));
        System.out.println(JAXBHelper.fromXML(Player.class, new FileInputStream("Player.xml")));


    }


    public Player(final String name, final Figure figure) {
        assert name != null;
        assert figure != null;


        this.name = name;
        this.figure = figure;
        this.score = 0;


    }


}





