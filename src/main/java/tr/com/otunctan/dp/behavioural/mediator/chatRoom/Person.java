package tr.com.otunctan.dp.behavioural.mediator.chatRoom;

import java.util.ArrayList;
import java.util.List;

public class Person {

    protected String name;
    protected ChatRoom room;
    private List<String> chatLog = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLog.add(s);
    }

    public void say(String message) {
        room.broadcast(name, message);
    }

    public void privateMessage(String who, String message) {
        room.message(name, who, message);
    }

}

class ChatRoom {

    private List<Person> people = new ArrayList<>();


    public void join(Person p) {
        String joinMsg = p.name + " joins the room";
        broadcast("room", joinMsg);
        p.room = this;
        people.add(p);
    }

    public void broadcast(String source, String message) {
        if (people.isEmpty()) return;
        for (Person p : people) {
            if (!p.name.equals(source)) {
                p.receive(source, message);
            }
        }
    }

    public void message(String source, String destination, String message) {
        people.stream()
                .filter(p -> p.name.equals(destination))
                .findFirst()
                .ifPresent(person -> person.receive(source, message));
    }
}


class Demo {
    public static void main(String[] args) {

        ChatRoom room = new ChatRoom();

        Person ada = new Person("ADA");
        Person ozay = new Person("Özay");

        room.join(ada);
        room.join(ozay);

        ada.say("Merhaba oda");
        ozay.say("oh, hey ada");


        Person simon = new Person("Simon");
        room.join(simon);
        simon.say("Herkes Merhaba !");

        ozay.privateMessage("Simon", "Hoş geldin.");


    }
}