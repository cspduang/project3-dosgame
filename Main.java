import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static DosHand dosHand = new DosHand();
    static CenterRow centerRow = new CenterRow();
    static DiscardPile discardPile = new DiscardPile();


    public static void main(String[] args) {

        dosHand.print();

        List<DosPlayer> players = getPlayers();

        // init dosHand
        for (int i = 0; i < 7; i++) {
            for (DosPlayer player : players) {
                try {
                    player.obtain(dosHand.deal());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // center row
        for (int i = 0; i < 2; i++) {
            try {
                centerRow.add(dosHand.deal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //
        centerRow.show();
        // show cards of players
        for (DosPlayer player : players) {
            System.out.println(player.getName() + ": " + Arrays.toString(player.hand.toArray()));
        }
        while(true) {

            for (DosPlayer player : players) {
                // for each player
                // To end the game, either a player has been awarded at least 5 bonus points or the deck/player ran
                // out of cards. (No need to shout "Dos"!)
                try {
                    turn(player);

                    if(player.getPoints() == 5 || player.hand.size() == 0) {
                        System.out.println("winner is " + player.getName());
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }

        }

    }

    private static void turn(DosPlayer player) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<DosCard> canChoice = new ArrayList<>();
        List<ArrayList<DosCard>> canChoicePair = new ArrayList<>();

        // find match card or pair
        for (DosCard card : centerRow.getCards()) {

            for(DosCard matchSingeCard : dosHand.getSingleNumberMatches(card)) {
                if(player.hand.contains(matchSingeCard))
                    canChoice.add(matchSingeCard);
            }
            for(ArrayList<DosCard> matchPair : dosHand.getDoubleNumberMatches(card)) {
                if(player.hand.contains(matchPair.get(0)) && player.hand.contains(matchPair.get(1))) {
                    if(matchPair.get(0) == matchPair.get(1)){
                        for (int i = 0; i < player.hand.size(); i++) {
                            if(player.hand.get(i) == matchPair.get(0)) {
                                for (int j = i + 1; j < player.hand.size(); j++) {
                                    if(player.hand.get(j) == matchPair.get(1)) {
                                        canChoicePair.add(matchPair);
                                    }
                                }
                            }
                        }
                    } else {
                        canChoicePair.add(matchPair);
                    }
                }
            }
        }

        // player turn to choice
        System.out.println("==== " + player.getName() + " ====");
        if(canChoice.size() > 0 || canChoicePair.size() > 0) {
            System.out.println("the possible options you can choice:");
            int index = 0;
            while(index < canChoice.size()) {
                System.out.println(index + ". " + canChoice.get(index));
                index++;
            }
            while(index - canChoice.size() < canChoicePair.size()) {
                System.out.println(index + ". " + canChoicePair.get(index - canChoice.size()));
                index++;
            }

            // choose and Display the new contents of the Center Row
            while(true) {
                System.out.println("Please input the number you choice");
                int choiceNum = Integer.valueOf(sc.nextLine());

                if(choiceNum < canChoice.size()) {
                    player.hand.remove(canChoice.get(choiceNum));
                    centerRow.addMatch(canChoice.get(choiceNum));
                    centerRow.show();
                    break;
                } else if (choiceNum - canChoice.size() < canChoicePair.size()) {
                    player.hand.remove(canChoicePair.get(choiceNum - canChoice.size()).get(0));
                    player.hand.remove(canChoicePair.get(choiceNum - canChoice.size()).get(1));
                    centerRow.addMatchpair(canChoicePair.get(choiceNum - canChoice.size()));
                    centerRow.show();
                    break;
                } else {
                    System.out.println("==== WRONG CHOICE NUM ====");
                }
            }

            // Check to see if the player is eligible for bonus points.
            int bounsPoints = centerRow.countBounsPoints();
            // End a player's turn by collecting the cards used in this turn. Add to discard pile.
            centerRow.discardto(discardPile);
            // Display the new contents of the Center Row
            System.out.println("==== the new contents of the Center Row ====");
            centerRow.show();
            // Make sure that there are at least two cards in the Center Row. If not, replenish from the DosDeck.
            while(centerRow.lessThenTwo()) {
                centerRow.add(dosHand.deal());
            }
            // Display the new contents of the Center Row
            System.out.println("==== the new contents of the Center Row ====");
            centerRow.show();

            player.setPoints(player.getPoints() + 1 + bounsPoints);

            // Check to see if the player is eligible for bonus points. If so, randomly select card(s) to be added to
            // the Center Row.
            if(bounsPoints > 0) {
                System.out.println("==== the player " + player.getName() + " is eligible for bonus points. ====");
                int indexOfHandCard = 0;
                while(indexOfHandCard < player.hand.size()) {
                    System.out.println(indexOfHandCard + ": " + player.hand.get(indexOfHandCard));
                    indexOfHandCard++;
                }
                System.out.println("Please input the number of card you need to add to Center Row");
                int selectIndex = Integer.valueOf(sc.nextLine());
                centerRow.add(player.hand.remove(selectIndex));
            }
            // Display the new contents of the Center Row
            System.out.println("==== the new contents of the Center Row ====");
            centerRow.show();
        } else {
            // If there is no match, have the player draw a card and randomly pick a card to add to the
            // Center Row
            System.out.println("no possible options you can choice, the player draw a card");
            player.hand.add(dosHand.deal());
        }
    }

    private static List<DosPlayer> getPlayers() {
        Scanner sc = new Scanner(System.in);
        List<DosPlayer> players = new ArrayList<DosPlayer>();
//        players.add(new DosPlayer("A"));
//        players.add(new DosPlayer("B"));
//        players.add(new DosPlayer("C"));

        String name = null;  //读取字符串型输入
        while(true) {
            System.out.println("Please Enter Name for new player or press Enter to end");
            name = sc.nextLine();

            if(name == null || name.length() == 0) {
                if(players.size() < 2) continue;
                else break;
            }
            
            DosPlayer player = new DosPlayer(name);
            players.add(player);

            if(players.size() >= 4) break;
        }

        return players;
    }
}
