
/**
 * Write a description of class epicQuest here.
 *
 * @author Solomon Emet
 * @version 16/4/2021
 */

//for the keyboard
import java.util.Scanner;

public class epicQuest
{
    //making some variables
    //replace ROOMS with the number of rooms in your text adventure and ITEMS with the number of items, don't touch anything else
    final int ITEMS = 6;
    final int ROOMS = 11;
    int room = 0;
    int lastRoom = 0;
    
    //replace the six with however many directions the text adventure will have
    //this text adventure has North, East, South, West, Up, and Down, which is six directions
    int connect[][] = new int[ROOMS][6];
    
    //itemDesc[][] is two-dimensional because each item has a seperate description for each room it's in
    //in this text adventure, every item is either in one room, or in your inventory
    //if any item can be in multiple rooms in addition to your inventory, replace the 2 with a different number
    String itemName[] = new String[ITEMS];
    String itemDesc[][] = new String[ITEMS][2];
    int itemRoom[] = new int[ITEMS];
    
    boolean doWhat = true;
    
    boolean haveYouLostYet = true;
    
    String command;

    String desc[] = new String[ROOMS];
    
    /**
     * Constructor for objects of class epicQuest
     */
    public epicQuest()
    {
        //more keyboard things
        Scanner keyboard = new Scanner(System.in);
        
        setUp();
        
        //replace this with your intro text
        System.out.println("Welcome to Epic Quest!");
        System.out.println();
        System.out.println("Epic Quest is a text adventure.");
        System.out.println("This means that it is played with words, because we couldn't afford graphics.");
        System.out.println("You will need to type things in simple sentences with poor grammer so that the computer can understand you.");
        System.out.println("FOR EXAMPLE:");
        System.out.println("If you want to pick something up, you can type 'get (object)'.");
        System.out.println();
        System.out.println("Type 'start' to begin.");
        
        while (room == 0){
            command = keyboard.nextLine();
            
            if (command.toLowerCase().equals("start")){
                room = 2;
                System.out.println(desc[room]);
            } else {
                System.out.println("That's not the word 'start'. If you have trouble reading, this probably isn't the game for you.");
            }
        }
        
        while (haveYouLostYet){
            command = keyboard.nextLine();
            doWhat = true;
            if(command.toLowerCase().startsWith("examine ")){
                String[] parts = command.split(" ",2);
                String part1 = parts[0];
                String part2 = parts[1];
                command = part2;
                for(int i = 0;i < ITEMS;i++){
                    if(command.equals(itemName[i])){
                        if(room == itemRoom[i] || itemRoom[i] == 1){
                            System.out.println(itemDesc[i][1]);
                            doWhat = false;
                        }
                    }
                }
            }
            
            if(command.toLowerCase().startsWith("get ") || command.toLowerCase().startsWith("take ")){
                String[] parts = command.split(" ",2);
                String part1 = parts[0];
                String part2 = parts[1];
                command = part2;
                for(int i = 0;i < ITEMS;i++){
                    if(command.equals(itemName[i])){
                        if(room == itemRoom[i]){
                            itemRoom[i] = 1;
                            System.out.println("You take the " + itemName[i] + ".");
                            doWhat = false;
                        }
                    }
                }
            }
            
            if(command.toLowerCase().startsWith("use ")){
                String[] parts = command.split(" ",2);
                String part1 = parts[0];
                String part2 = parts[1];
                command = part2;
                for(int i = 0;i < ITEMS;i++){
                    if(command.equals(itemName[i])){
                        if(itemRoom[i] == 1){
                            //this script handles all of your item usage stuff
                            //replace it with all of your item usage stuff
                            switch(command){
                                case "thick golden key":
                                
                                if(room == 4){
                                    connections(4,0,0,0,2,10,0);
                                    System.out.println("You deftly unlock the padlock. Now you can go up to the second floor of the tower!");
                                    itemRoom[2] = 0;
                                } else {
                                    System.out.println("There's nothing you can unlock here. Not with a key, at least.");
                                }
                                break;
                                
                                case "pickaxe":
                                
                                if(room == 3){
                                    if(itemRoom[2] != 1){
                                        System.out.println("You break open the safe. Inside is a thick golden key.");
                                        itemRoom[2] = 3;
                                    } else {
                                        System.out.println("There's nothing to use that on here.");
                                    }
                                } else {
                                    if(room == 9){
                                        if(itemRoom[5] != 1){
                                            System.out.println("You knock loose one of the zenergy crystals.");
                                            itemRoom[5] = 9;
                                        } else {
                                            System.out.println("You don't need any more crystals.");
                                        }
                                    } else {
                                        if(room == 2){
                                        if(itemRoom[0] != 1){
                                            System.out.println("You pick away at the statue's base and... a stone heart falls out. How very, very disturbing.");
                                            itemRoom[0] = 2;
                                        } else {
                                            System.out.println("There's nothing to use that on here.");
                                        }
                                    } else {
                                        System.out.println("There's nothing to use that on here.");
                                    }
                                    }
                                }
                                break;
                                
                                case "stone heart":
                                
                                if(itemRoom[0] == 1){
                                    System.out.println("You place the stone heart in the circle.");
                                    itemRoom[0] = 10;
                                }
                                break;
                                
                                case "broken bone":
                                
                                if(itemRoom[3] == 1){
                                    System.out.println("You place the broken bone in the circle.");
                                    itemRoom[3] = 10;
                                }
                                break;
                                
                                case "zenergy crystal":
                                
                                if(itemRoom[5] == 1){
                                    System.out.println("You place the Zenergy crystal in the circle.");
                                    itemRoom[5] = 10;
                                }
                                break;
                            }
                        } else {
                            System.out.println("You can't use that item because it's not in your inventory.");
                        }
                        doWhat = false;
                    }
                }
            }
            
            switch(command.toLowerCase()){
                case "north":
                case "go north":
                case "n":
                
                move(0);
                break;
                
                case "east":
                case "go east":
                case "e":
                
                move(1);
                break;
                
                case "south":
                case "go south":
                case "s":
                
                move(2);
                break;
                
                case "west":
                case "go west":
                case "w":
                
                move(3);
                break;
                
                case "up":
                case "go up":
                case "u":
                
                move(4);
                break;
                
                case "down":
                case "go down":
                case "d":
                
                move(5);
                break;
                
                case "inventory":
                case "check inventory":
                case "look in inventory":
                case "look through inventory":
                case "i":
                
                System.out.println(desc[1]);
                for(int i = 0;i < ITEMS;i++){
                    if(itemRoom[i] == 1){
                        System.out.println(itemDesc[i][1]);
                    }
                }
                System.out.println("Well, maybe you have some items. You might not. I can't tell.");
                break;
                
                case "back":
                case "go back":
                
                if(lastRoom == 0){
                    System.out.println("You can't remember where you came from, so you decide to just stand still instead.");
                } else {
                    System.out.println("You take a step backwards.");
                    room = lastRoom;
                    lastRoom = 0;
                    System.out.println(desc[room]);
                    for(int i = 0;i < ITEMS;i++){
                        if(room == itemRoom[i]){
                            System.out.println(itemDesc[i][0]);
                        }
                    }
                }
                break;
                
                case "look around":
                System.out.println(desc[room]);
                for(int i = 0;i < ITEMS;i++){
                    if(room == itemRoom[i]){
                        System.out.println(itemDesc[i][0]);
                    }
                }
                break;
                
                case "weast":
                case "go weast":
                System.out.println("NO, YOU FOOL! ARE YOU TRYING TO KILL US ALL!?");
                break;
                
                case "nouth":
                case "go nouth":
                System.out.println("NO, YOU... uh, wait. No, that's fine, actually. But you don't have a Zingobulator, so you can't go that way.");
                break;
                
                case "sorth":
                case "go sorth":
                System.out.println("Don't be ridiculous. That's not a direction.");
                break;
                
                case "est":
                case "go est":
                System.out.println("Who the hell is Est?");
                break;
                
                default:
                if(doWhat == true){
                    System.out.println("What?");
                }
                break;
                
                //beyond this point are custom commands that are only needed for this specific text adventure
                case "statue":
                if(room == 2){
                    System.out.println("The statue is apparently dedicated to 'Sir Archibald Bartholomew Baxton Williamson Billiamson Jorgenschtlag Spike Jones\nJohnson F. Charles Nicholas Napo Deekins Isaac Liō Geoffrey \"Billy\" Fitzgerald Kitchener Parham Kravmaklamovich Bob\nQuixote Lalalianstrein Stüpiidhæd Cool Spot Diego José Francisco de Paula Juan Nepomuceno María de los Remedios Cipriano\nde la Santísima Trinidad Ruiz y Ferdinand Carl Ludwig Joseph Maria Batchlethnortnhoewlers Crushida Pepper Failure Darwin\nRaglan Caspian Ahab Poseidon Nicodemius Watterson Butthurst Funkmeister Maximus Ceasar Solomon Arthur Alexander Augustus\nZeus Constantine Henry Charlemagne Justinian Napoleon Dynamite Bonaparte Hill Ghrnglnflrg Arturo Benedetto Giovanni\nGuiseppe Pietro Archelangelo Alfredo Cartoffoli da Milano Ping Pin Pong Smarfx Adolph Blaine Charles David Earl Frederick\nGerald Hubert Irvim John Kenneth Loyd Martin Nero Oliver Paul Quincy Randolph Sherman Thomas Uncas Victor Willian Xerxes\nYancy Zeus Timm Hatfield McCoy Pilkey Wheatley Radmond Cruthers Mulligan Fafa Hodgman Barnaby Day-Knight Bouba Kiki Yanny\nLaurel Marmaduke Aloysius Benjy Cobweb Dartagnan Egbert Felix Gaspar Humbert Ignatius Jayden Kasper Leroy Maximilian\nNeddy Obiajulu Pepin Quilliam Rosencrantz Sexton Teddy Upwood Vivatma Wayland Xylon Yardley Zachary Usansky Mackenzie\nDisco Plumm Maddish John Wayne Gary Wayne Johnson Macklemore Minikrisniewsky Shaun Tobediah Bimothy Bromthonan Exodus\nCrustacean Jameson Thaddeus Smith Jr. III'. There’s a large crack in the base of it.");
                } else {
                    System.out.println("What statue?");
                }
                break;
            }
            //replace this with your win condition
            if(itemRoom[0] == 10 && itemRoom[3] == 10 && itemRoom[5] == 10){
                //replace this with your win message
                System.out.println("Suddenly, you are blinded by an intense light...");
                System.out.println("CONGRATULATIONS! YOU WIN!");
                haveYouLostYet = false;
            }
        }
    }

    /**
     * The setup
     *
     * @sets things up
     * @without it, nothing would get set up
     */
    public void setUp()
    {
        //replace these with your room descriptions
        desc[1] = "You look through your inventory. You have:";
        desc[2] = "You are in a quaint little town square. There are a lot of houses here, as well as a large statue of a guy, a path to the\nNorth, a path to the East, a path to the South, and a path to the West. It’s eerily silent.";
        desc[3] = "You're on the picturesque beach. There’s lots of sand here. There’s also a set of footprints leading from the East to the\nWest. To the East is the Get Lost Woods, and to the West is a run-down beachside cabin. For some reason, a small safe has\nwashed up on the shore.";
        desc[4] = "You're in the base of the tower. It’s dark, but not too dark to see. On a nearby table, you see a torn page. Behind the\ntable is a ladder leading up to a wooden trapdoor with a large metal lock on it. To the West is the door leading back to\nthe town square.";
        desc[5] = "You walk down the path, and suddenly find yourself blocked by an insurmountable wall made of stone bricks. On either side\nof you, houses have been cut in half by the wall, and since they look almost crushed, you can deduce that the wall must\nhave fallen from the sky. Somehow.\nThere is also the path leading North, back to the town square.";
        desc[6] = "You're in the town’s mine’s main shaft. Most of it is blocked by a cave-in, but there’s a large hole in the floor that\nleads down to a small cave. A little rope ladder has been set up to allow easy access to the cave. To the East is the\ntown square.";
        desc[7] = "You're in an old cabin.";
        desc[8] = "You’re lost in the woods. You have no idea where you are, but the beach is probably somewhere West of your current\nlocation.";
        desc[9] = "You’re in a small cave. There’s a small patch of blue crystals next to you, and the rope ladder leading up to the\nmineshaft.";
        desc[10] = "The top floor of the tower has a wonderful view of the town, and from up here you can see that it’s truly devoid of\npeople. On the floor is a strange circle painted on the floor with red paint, surrounded by strange symbols.";
        
        //replace these with your item names
        itemName[0] = "stone heart";
        itemName[1] = "torn page";
        itemName[2] = "thick golden key";
        itemName[3] = "broken bone";
        itemName[4] = "pickaxe";
        itemName[5] = "zenergy crystal";
        
        //replace these with your item descriptions
        itemDesc[0][0] = "There's a stone heart lying on the floor.";
        itemDesc[0][1] = "A heart of stone. For some reason, you found this thing inside of... a statue. You don't even want to think about how\nthat works.";
        itemDesc[1][0] = "On a nearby table, you see a torn page.";
        itemDesc[1][1] = "A torn page of slightly yellowed paper. It looks like a part of a book, and says something like \"ure not to mess it\nup. You will need a Zenergy crystal, a broken bone, and a stone heart. An experienced wizard should have a ready supply\nof these, but they are not rare ingredients, and a novice could easily find them in the wild. Arrange them in a standard\nritual circle, but make absolutely sure to n\". Hopefully, that last part wasn’t important.";
        itemDesc[2][0] = "There's a thick golden key lying on the sand in front of the open safe.";
        itemDesc[2][1] = "A thick, shiny key made from some gold-like metal. For some reason, it was hidden inside a safe that washed up on the\nshore.";
        itemDesc[3][0] = "There's also a broken bone lying on the floor.";
        itemDesc[3][1] = "Half of a bone. You're not sure what kind of bone it is.";
        itemDesc[4][0] = "There's also a pickaxe inside of an open cabinet next to the table.";
        itemDesc[4][1] = "A sturdy pickaxe. There’s a wooden bit, and a sharp metal bit. You could probably mine stuff with it.";
        itemDesc[5][0] = "There's also a Zenergy crystal lying on the floor.";
        itemDesc[5][1] = "A blue crystal that has an aura of intense energy.";
        
        //replace these with the rooms that your items will start out in
        itemRoom[0] = 0;
        itemRoom[1] = 4;
        itemRoom[2] = 0;
        itemRoom[3] = 7;
        itemRoom[4] = 7;
        itemRoom[5] = 0;
        
        //replace these with your room connections: connections(room,north,east,south,west,up,down);
        connections(2,3,4,5,6,0,0);
        
        connections(3,0,8,2,7,0,0);
        
        connections(4,0,0,0,2,0,0);
        
        connections(5,2,0,0,0,0,0);
        
        connections(6,0,2,0,0,0,9);
        
        connections(7,0,3,0,0,0,0);
        
        connections(8,8,8,8,3,0,0);
        
        connections(9,0,0,0,0,6,0);
        
        connections(10,0,0,0,0,0,4);
    }
    
    /**
     * This handles room connections, don't mess with it
     *
     * @makes room connections happen
     * @without it, rooms couldn't be connected
     */
    public void connections(int num,int zero,int one,int two,int three,int four,int five){
        connect[num][0] = zero;
        connect[num][1] = one;
        connect[num][2] = two;
        connect[num][3] = three;
        connect[num][4] = four;
        connect[num][5] = five;
    }
    
    /**
     * This handles movement, don't mess with it even if you want to add more directions
     *
     * @makes movement happen
     * @without it, movement couldn't happen
     */
    public void move(int num){
        if(connect[room][num] == 0){
            System.out.println("You can't go in that direction right now. Or maybe ever, depending on where you're trying to go.");
        } else {
            lastRoom = room;
            room = connect[room][num];
            System.out.println(desc[room]);
            for(int i = 0;i < ITEMS;i++){
                    if(room == itemRoom[i]){
                        System.out.println(itemDesc[i][0]);
                    }
                }
        }
    }
}