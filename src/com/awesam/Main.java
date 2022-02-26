package com.awesam;

import java.util.*;

public class Main {
    private static ArrayList<Album> albums = new ArrayList<Album>();

    private static    Scanner scanner=new Scanner(System.in);
    private static   MobilePhone mobilePhone = new MobilePhone("0718-709-549");
    public static void main(String[] args) {
        Album album = new Album("Stormbringer", "Deep Purple");
        album.addSong("Stormbringer", 4.6);
        album.addSong("Love don't mean a thing", 4.22);
        album.addSong("Holy man", 4.3);
        album.addSong("Hold on", 5.6);
        album.addSong("Lady double dealer", 3.21);
        album.addSong("You can't do it right", 6.23);
        album.addSong("High ball shooter", 4.27);
        album.addSong("The gypsy", 4.2);
        album.addSong("Soldier of fortune", 3.13);
        albums.add(album);

        album = new Album("For those about to rock", "AC/DC");
        album.addSong("For those about to rock", 5.44);
        album.addSong("I put the finger on you", 3.25);
        album.addSong("Lets go", 3.45);
        album.addSong("Inject the venom", 3.33);
        album.addSong("Snowballed", 4.51);
        album.addSong("Evil walks", 3.45);
        album.addSong("C.O.D.", 5.25);
        album.addSong("Breaking the rules", 5.32);
        album.addSong("Night of the long knives", 5.12);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlayList("You can't do it right", playList);
        albums.get(0).addToPlayList("Holy man", playList);
        albums.get(0).addToPlayList("Speed king", playList);  // Does not exist
        albums.get(0).addToPlayList(9, playList);
        albums.get(1).addToPlayList(8, playList);
        albums.get(1).addToPlayList(3, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(24, playList);  // There is no track 24

        play(playList);




        boolean quite = false;
        startPhone();
        printAction();
        while (!quite) {
            System.out.println("\nEnter action: (6 to show available actions))");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("\n Shutting down....");
                    quite = true;
                    break;
                case 1:
                    mobilePhone.printContacts();
                    break;
                case 2:
                    addNewContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    queryContact();
                    break;
                case 6:
                    printAction();
                    break;
            }
        }
        Bank bank = new Bank("KCB");
        bank.addBranch("Ruiru");
        bank.addCustomer("Ruiru","John",50.05);
        bank.addCustomer("Ruiru","Sam",12.20);
        bank.addCustomer("Ruiru","Ruth",30.5);

        bank.addBranch("TRM");
        bank.addCustomer("TRM","Faith",40.54);

        bank.addCustomerTransaction("Ruiru","John",43.56);
        bank.addCustomerTransaction("Ruiru","John",43.16);
        bank.addCustomerTransaction("Ruiru","John",41.56);

        System.out.println(bank.listCustomers("Ruiru",true));


        Bank bank2 = new Bank("National Australia Bank");

        bank2.addBranch("Adelaide");

        bank2.addCustomer("Adelaide", "Tim", 50.05);
        bank2.addCustomer("Adelaide", "Mike", 175.34);
        bank2.addCustomer("Adelaide", "Percy", 220.12);

        bank2.addCustomerTransaction("Adelaide", "Tim", 44.22);
        bank2.addCustomerTransaction("Adelaide", "Tim", 12.44);
        bank2.addCustomerTransaction("Adelaide", "Mike", 1.65);

        bank2.listCustomers("Adelaide", true);


    }
    private static void play(LinkedList<Song>playList){
        Scanner scanner = new Scanner(System.in);
        boolean forward = true;
        boolean quit = false;
        ListIterator<Song>listIterator=playList.listIterator();
        if (playList.size()==0){
            System.out.println("No song in playlist");
            return;
        }else {
            System.out.println("Now playing "+listIterator.next().toString());
        }
        while (!quit) {
            System.out.println("Select option(5 to print menu): ");
            int action = scanner.nextInt();
            scanner.nextLine();
            switch (action) {
                case 0:
                    System.out.println("Playlist complete. ");
                    quit=true;
                    break;
                case 1:
                    if (!forward){
                        if (listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward=true;
                    }
                    if (listIterator.hasNext()){
                        System.out.println("Now playing "+ listIterator.next().toString());
                    }else {
                        System.out.println("We have reached the end of the playlist");
                        forward=false;
                    }
                    break;
                case 2:
                    if (forward){
                        if (listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward=false;
                    }
                    if (listIterator.hasPrevious()){
                        System.out.println("Now playing "+ listIterator.previous().toString());
                    }else {
                        System.out.println("We have reached the start of the playlist");
                        forward=true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    }else {
                        if (listIterator.hasNext()){
                            System.out.println("Now replaying "+ listIterator.next().toString());
                            forward =true;
                        }else {
                            System.out.println("We are at the end of the list");
                        }
                    }
                    break;
                case 4:
                   printList(playList);
                    break;
                case 5:
                   printMenu();
                    break;
                case 6:
                    if (playList.size()>0){
                        listIterator.remove();
                        if (listIterator.hasNext()){
                            System.out.println("Now playing "+listIterator.next());
                        }else if(listIterator.hasPrevious()) {
                            System.out.println(("Now playing "+listIterator.previous()));
                        }
                    }
                    break;
            }
        }
    }
    private static void printMenu(){
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n"+
                            "1- to play the next song\n"+
                            "2- to play the previous song\n"+
                            "3- to replay the current song\n"+
                            "4- list songs in the playlist\n"+
                            "5- print available actions.\n"+
                            "6- delete current song from playlist\n");
    }
    private static void printList(LinkedList<Song>playList){
        Iterator<Song> iterator= playList.iterator();
        System.out.println("=================================================");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("=================================================");
    }
    private static void startPhone(){
        System.out.println("\nphone starting.....");
    }
    private static void printAction() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0- to shutdown\n" +
                "1- to print contacts\n" +
                "2- to add new contacts\n" +
                "3- to update existing contact\n" +
                "4- to remove an existing contact\n" +
                "5- Query if an existing contact exist\n"+
                "6- to print a list of available option\n"
        );
        System.out.println("Choose your action");
    }
    private static void addNewContact(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new contact name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone Number: ");
        String phone = scanner.nextLine();
        Contact newContact= Contact.createContact(name,phone);
        if (mobilePhone.addNewContact(newContact)){
            System.out.println("New contact added name = "+name+",phone = "+phone);
        }else System.out.println("Cannot add, "+name+" already on file");


    }
    private static void updateContact(){
        System.out.println("Enter existing contact name: ");
        String name=scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord==null){
            System.out.println("Contact not found");
            return;
        }
        System.out.println("Enter new contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter new contact phone number: ");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName,newNumber);
        if (mobilePhone.updateContact(existingContactRecord,newContact)){
            System.out.println("successfully updated record");
        }else{
            System.out.println("Error updating the record");
        }
    }

    private static void removeContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
        if (mobilePhone.removeContact(existingContactRecord)){
            System.out.println("Successfully deleted ");
        }else{
            System.out.println("Error deleting contact ");
        }
    }
    private static void queryContact() {
        System.out.println("Enter existing contact name: ");
        String name = scanner.nextLine();
        Contact existingContactRecord = mobilePhone.queryContact(name);
        if (existingContactRecord == null) {
            System.out.println("Contact not found");
            return;
        }
        System.out.println("name: "+existingContactRecord.getName()+" phone number is "+existingContactRecord.getPhoneNumber());
    }

}
