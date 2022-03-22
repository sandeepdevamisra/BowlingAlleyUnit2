/*

  To change this generated comment edit the template variable "typecomment":
  Window>Preferences>Java>Templates.
  To enable and disable the creation of type comments go to
  Window>Preferences>Java>Code Generation.
 */

import java.util.*;
import java.io.*;

public class ScoreHistoryFile {

    private static final String SCOREHISTORY_DAT = "SCOREHISTORY.DAT";

    public static void addScore(String nick, String date, String score)
            throws IOException {

        String data = nick + "\t" + date + "\t" + score + "\n";

        RandomAccessFile out = new RandomAccessFile(SCOREHISTORY_DAT, "rw");
        out.skipBytes((int) out.length());
        out.writeBytes(data);
        out.close();
    }

    public static Vector getScores(String nick)
            throws IOException {
        Vector scores = new Vector();

        BufferedReader in =
                new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        String data;
        while ((data = in.readLine()) != null) {
            // File format is nick\tfname\te-mail
            String[] scoredata = data.split("\t");
            //"Nick: scoredata[0] Date: scoredata[1] Score: scoredata[2]
            if (nick.equals(scoredata[0])) {
                scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
            }
        }
        return scores;
    }

    public static String getMax()throws IOException{
        BufferedReader in =
                new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        String data;
        int maximum = 0;
        String name = "";
        while ((data = in.readLine()) != null) {
            String[] scoredata = data.split("\t");
            if (Integer.parseInt(scoredata[2]) > maximum){
                maximum = Integer.parseInt(scoredata[2]);
                name = scoredata[0];
            }
        }
        
        return (name + " - " + maximum);
    }


    public static String getMin()throws IOException{
        BufferedReader in =
                new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        String data;
        int minimum = 1000;
        String name = "";
        while ((data = in.readLine()) != null) {
            String[] scoredata = data.split("\t");
            if (Integer.parseInt(scoredata[2]) < minimum){
                minimum = Integer.parseInt(scoredata[2]);
                name = scoredata[0];
            }
        }
        
        return (name + " - " + minimum);
    }
   

    public static int getCount()throws IOException{
        BufferedReader in =
                new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        int count = 0;
        while ((in.readLine()) != null) {
            count++;
        }
        return count;
    }


    public static Vector getHighestAndLowest(String nick, boolean isGeneral)
            throws IOException {
        Vector scores = new Vector();

        BufferedReader in =
                new BufferedReader(new FileReader(SCOREHISTORY_DAT));
        String data;
        while ((data = in.readLine()) != null) {
            String[] scoredata = data.split("\t");
            scores.add(new Score(scoredata[0], scoredata[1], scoredata[2]));
        }

        Iterator scoreIt = scores.iterator();
        int max = -1;
        int min = 301;
        int count_games = 0;
        String bestplayer="";
        String worstplayer="";

        while (scoreIt.hasNext()) {
            Score score = (Score) scoreIt.next();

            if (isGeneral || (!isGeneral && score.getNickName().equals(nick))) // isGeneral is a Boolean variable
            {
                if (score.getNickName().equals(nick))
                {
                    count_games++;
                }
                int intScore = Integer.parseInt(score.getScore());

                if (intScore > max)
                {
                    bestplayer = score.getNickName();
                    max = intScore;
                }
                if (intScore < min)
                {
                    worstplayer = score.getNickName();
                    min = intScore;
                }
            }
        }

        Vector toreturn = new Vector<>();
        toreturn.add(max);
        toreturn.add(bestplayer);
        toreturn.add(min);
        toreturn.add(worstplayer);
        toreturn.add(count_games);

        return toreturn;
    }
  
}
