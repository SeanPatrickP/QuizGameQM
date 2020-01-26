// Sean Palmer, miniproject, quiz

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
            // calls the methods for the programme

        String [] players = {"null","null"};
        players = playersinfo(players);
        questions(players);

    }

    public static String [] playersinfo(String [] players) {
            // this will ask the player for an input of their name and if there are to be any additional players - uses a popup box

        int welcome = JOptionPane.showConfirmDialog(null, "Welcome to Seans' quiz game, do you want to play?");

        if (welcome == 0) {
            players[0] = JOptionPane.showInputDialog(null, "What is your name?");

            int moreplayers = JOptionPane.showConfirmDialog(null, "Add another player?");
            if (moreplayers == 0) {

                players[1] = JOptionPane.showInputDialog(null, "What is the next players name?");
    }
            else players [1] = players [0];
    }

        else System.exit(0);
        return players;
    }



    public static void questions(String [] players) throws FileNotFoundException {
            // in this method the questions are asked in a for loop and then the point is added if correct
            // uppercase answer converted from user, cross checked with uppercase answer array
        String[] questions = {"Question 1", "Question 2", "Question 3", "Question 4", "Question 5", "Question 6", "Question 7", "Question 8", "Question 9", "Question 10"};
        String[] actualQuestions = {"What is the name of Abba's first number 1 song?", "Who is the king of rock and roll?","How many oscars did the Titanic movie got?","How many Tomb Raider movies were made?","Which malformation did Marilyn Monroe have when she was born?","What is the house number of the Simpson's?","What is the name of the prison in the film The Rock?","Who played the role of Peter Pan in the Peter Pan movie?","Which famous group was once known as The Quarrymen?","Who won the most Grammy Awards in the 80's?"};
        String [] answers = {"WATERLOO","ELVIS","11","2","6 TOES","742","ALCATRAZ","ROBIN WILLIAMS","THE BEATLES","MICHAEL JACKSON"};
        interestingFact [] stupid = new interestingFact[6];
        String [] stupidity = {"Did you know that the Virgin Pendolino is named after pendulum in Italian?","Did you know that pluto is not large enough to be a planet?","Did you know that Candela is the unit for light intensity?","Did you know the nearest planet to the Sun is Mercury?","Wow do you know 2 + 2 = 4","Wow facts run out for the game!"};
        String [] cleverFact = {"The average person spends 6 months of their lifetime waiting on a red light to turn green","A single cloud can weight more than 1 million pounds","In 1386 a pig in France was executed by public hanging for the murder of a child","You burn more calories sleeping than you do watching television","A coyote can hear a mouse moving underneath a foot of snow","A broken clock is right two times every day"};
        interestingFact [] clever = new interestingFact[6];
        int fact = 0;
        int cFact = 0;

        for(int i = 0; i<stupid.length; i++){
            // the below is key in actually creating the record within each part of the array - otherwise this wouldn't exist, the name of the array is stupid
            stupid[i] = new interestingFact();
            clever[i] = new interestingFact();
            stupid[i] = setFact(stupidity [i], stupid[i]);
            clever[i] = setFact(cleverFact[i], clever[i]);
    }

        pointsystem player1 = new pointsystem();
        pointsystem player2 = new pointsystem();
            // The above 2 lines are very important as initializing the pointsystem outside the setter means that each time the setter is called it does not re-generate each time, thus not keeping an accumulation of the points
        for(int i = 0; i < questions.length; i++) {

            if( (i%2) == 0) {

                String uans;

                uans = JOptionPane.showInputDialog(null, questions[i] + " for " + players[0] + " " + actualQuestions[i]);

                if (uans.toUpperCase().equals(answers[i])) {
                    System.out.println("Correct! well done");
                    int score = addPointForPlayer1(player1);

                    if(cFact < 6) {
                        System.out.println(prefixForCleverPlayer(getFact(cFact, clever)));
                        cFact = cFact + 1;
    }
                    else
                        System.out.println("You players in this game are really clever");

                    System.out.println(players[0] + " has " + score + " points");
    }
                else {
                    System.out.println("Incorrect answer! the correct answer was " + answers[i]);
                    if(fact < 5) {
                        System.out.println("You clearly don't know enough ... " + getFact(fact, stupid));
                        fact = fact + 1;
    }
                    else {
                        fact = 5;
                        System.out.println("You clearly don't know enough ... " + getFact(fact, stupid));
    }
    }
    }
            if( (i%2) != 0)
            // here the boolean statement will define what player the question goes to, the question numbers that are even go to player 0, odd to player 1
    {
                String uans;

                uans = JOptionPane.showInputDialog(null, questions[i] + " for " + players[1] + " " + actualQuestions[i]);
                if (uans.toUpperCase().equals(answers[i])) {
                    System.out.println("Correct! well done");

                    if(cFact < 6) {
                        System.out.println(prefixForCleverPlayer(getFact(cFact, clever)));
                        cFact = cFact + 1;
    }
                    else
                    System.out.println("The players in this game are really clever");

                    if(players[0].equals(players[1])){
                        int score = addPointForPlayer1(player1);
                        System.out.println(players[0] + " has " + score + " points");
    }
                    else {
                        int score = addPointForPlayer2(player2);
                        System.out.println(players[1] + " has " + score + " points");
    }
    }
                else {
                    System.out.println("Incorrect answer! the correct answer was " + answers[i]);

                    if(fact < 5) {
                        System.out.println("You clearly don't know enough ... " + getFact(fact, stupid));
                        fact = fact + 1;
    }
                    else {
                        fact = 5;
                        System.out.println("You clearly don't know enough ... " + getFact(fact, stupid));
    }
    }
    }
    }
        int return1;
                return1 = getPointsPlayer1(player1);
        int return2;
                return2 = getPointPlayer2(player2);

        int [] scores = {return1, return2};

        winnerLooser(scores, players, return1, return2);

        return;

    }

    public static void winnerLooser(int [] scores, String [] players, int return1, int return2) throws FileNotFoundException {

            //this for loop within a for loop demonstrates a bubble sort to sort the scores in order to find the winning score number, then this method will find out what player achieved this winning score
        for(int pass = 0; pass < (scores.length - 1); pass++){
            for(int i = 0; i< (scores.length - 1); i++){
                if(scores[i]>scores[i+1]) {
                    int tempHolder = scores[i+1];
                    scores[i + 1] = scores[i];
                    scores[i] = tempHolder;
    }
    }
    }
        System.out.println("The winning score for the game was " + scores[1] + " this was achieved by " + findplayer(scores[1], players, return1, return2));
        int Output = JOptionPane.showConfirmDialog(null, "Well Done !!!! " + findplayer(scores[1], players, return1, return2) + " you WON, would you like an output message to really make your opponents day?");
        if(Output == 0) {
            PrintWriter message = new PrintWriter("output.txt");
            message.println("Well Done " + findplayer(scores[1], players, return1, return2) + " you are not stupid unlike " + findplayer(scores[0], players, return1, return2) + ". You have permission to now taunt your opponent");
            if(findplayer(scores[1], players, return1, return2).equals(findplayer(scores[0], players, return1, return2)))
                message.println("Oh Wait .... you played against yourself, that's" +
                        "" +
                        " sad!");
            message.close(); // we close the PrintWriter here, meaning end of message and this will be printed in file
    }
        else
            System.out.println("You clearly are not interested in the game");
            int furtherOutput = JOptionPane.showConfirmDialog(null, "Would you like to know the leaderboard and if you have scored the highest score?");
        if(furtherOutput == 0) {
            File input = new File("leaderboard.txt");
            Scanner scan = new Scanner(input);
            String leaderName = scan.nextLine();
            int leaderScore = scan.nextInt();
            if (scores[1] > leaderScore) {
                System.out.println("Wow new high score added to leader board !!!!");
                PrintWriter message = new PrintWriter("leaderboard.txt");
                message.println(findplayer(scores[1], players, return1, return2));
                message.println(scores[1]);
                message.close(); // we close the PrintWriter here, meaning end of message and this will be printed in file
    }
            else
                System.out.println("You didn't beat the highest scorer or you scored the same as " + leaderName + " who scored " + leaderScore + " points");

            System.exit(0);
    }
        else
            System.out.println("Great, even if you had the highest score you would not know and thus you have not been added to the leader board !!!!");

        System.exit(0);
    }

    public static int getPointsPlayer1(pointsystem player1){

        return player1.points;
    }

    public static int getPointPlayer2(pointsystem player2){

        return player2.points;
    }

    public static int addPointForPlayer2(pointsystem player2){

        player2.points = player2.points + 1;

        return player2.points;
    }

    public static int addPointForPlayer1(pointsystem player1){

        player1.points = player1.points + 1;

        return player1.points;
    }


    public static interestingFact setFact(String getIFact, interestingFact temp){

        temp.fact = getIFact;

        return temp;
    }

    public static String getFact(int counter, interestingFact [] stupidOrClever){

        int i = counter;

        return stupidOrClever[i].fact;
    }
            //the below is to demonstrate prefixing the clever fact get with "Here is a fact for you ... "

    public static String prefixForCleverPlayer(String responseFromGetFact){
        String toReturn = "Here is a fact for you ... " + responseFromGetFact;

        return toReturn;
    }

    public static String findplayer(int score, String [] players, int return1, int return2){
        if(score == return1 )
            return players[0];
        else if(score == return2 )
            return players[1];
        else
            return "error";
    }
    }



class pointsystem {

    int points = 0;
    }

class interestingFact {

    String fact;
    }





