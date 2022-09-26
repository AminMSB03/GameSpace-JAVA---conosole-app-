package admin;

import checkers.TimerChecker;
import gamesRoom.GamesRoom;
import interfaces.IAdmin;
import post.Post;
import session.Session;

import java.sql.Time;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Admin implements IAdmin {
    @Override
    public void addSession(String fName, String lName, String game, int nPost, int period, String startTimeT) throws ParseException {


        TimerChecker time = new TimerChecker();
        String finishTime = time.getTheEndTime(startTimeT,period);


        // Create an instance of the session object
        Session session = new Session(fName, lName, game, nPost, period, startTimeT, finishTime);

        // link the timer
        TimerChecker timer = new TimerChecker(nPost, session, 100000000);


        // Add the session to the list

        GamesRoom.places.add(session);

        // Add the session to the post and make it not available
        HashMap<Post, Integer> posts = GamesRoom.posts;
        Iterator<Map.Entry<Post,Integer>> iterator = posts.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Post, Integer> entry = iterator.next();
            Post post = (Post) entry.getKey();
            if(post.getPostNum()==nPost){
                entry.setValue(1);
                post.setSession(session);
            }

        }



    }



    @Override
    public void addClient() {

    }

    @Override
    public void addToWaitingLine(String fName, String lName, String game, int period) {

        Session session = new Session(fName, lName, game, 0, period, null , null);

        GamesRoom.waitingLine.add(session);
    }

    @Override
    public HashMap<Post, Integer> getPostsInfo() {
        HashMap<Post, Integer> posts = GamesRoom.posts;
        return posts;
    }

    @Override
    public String getTotalIncome() {
        return null;
    }
}
