/*

  SMTP implementation based on code by R�al Gagnon mailto:real@rgagnon.com

 */


import java.io.*;
import java.util.Vector;
import java.util.Iterator;
import java.net.*;
import java.awt.print.*;


/**
 * This class calculate score of every bowler, sends it on email address and also print it.
 */
public class ScoreReport {

    private final String content;

    public String ComposeContentString(Vector v, int games, int[] scores, String full, String nick) {

        Iterator scoreIt = v.iterator();

        StringBuilder bld = new StringBuilder();
        bld.append(String.format("--Lucky Strike Bowling Alley Score Report--\n\nReport for %s, aka \"%s\":\n\n" + "Final scores for this session: %d", full, nick, scores[0]));
        for (int i = 1; i < games; i++) {
            bld.append(", ");
            bld.append(scores[i]);
        }
        bld.append(".\n\n\nPrevious scores by date: \n");
        while (scoreIt.hasNext()) {
            Score score = (Score) scoreIt.next();
            bld.append("  ");
            bld.append(score.getDate());
            bld.append(" - ");
            bld.append(score.getScore());
            bld.append("\n");
        }
        bld.append("\n\nThank you for your continuing patronage.");
        return bld.toString();
    }

    public ScoreReport(Bowler bowler, int[] scores, int games) {
        String nick = bowler.getNickName();
        String full = bowler.getFullName();
        Vector v = null;
        try {
            v = ScoreHistoryFile.getScores(nick);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
        assert v != null;
        content = ComposeContentString(v, games, scores, full, nick);
    }

    // Sends email to Bowler about score.
    public void sendEmail(String recipient) {
        try {
            Socket s = new Socket("osfmail.rit.edu", 25);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "8859_1"));

            // here you are supposed to send your username
            sendln(out, "HELO world\r\nMAIL FROM: <mda2376@rit.edu>\r\nRCPT TO: <" + recipient + ">\r\nDATA\r\nSubject: Bowling Score Report \r\nFrom: <Lucky Strikes Bowling Club>\r\nContent-Type: text/plain; charset=\"us-ascii\"\r\n");
            sendln(out, content + "\n\n\r\n.\r\nQUIT");
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Print Bowler's score.
    public void sendPrintout() {
        PrinterJob job = PrinterJob.getPrinterJob();

        PrintableText printobj = new PrintableText(content);

        job.setPrintable(printobj);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendln(BufferedWriter out, String s) {
        try {
            out.write(s + "\r\n");
            out.flush();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
