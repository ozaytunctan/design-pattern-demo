package tr.com.otunctan.dp.behavioural.state.v1;

public class State {

    void on(LightSwitch ls) {
        System.out.println("Light is already on...");
    }

    void off(LightSwitch ls) {
        System.out.println("Light is already off...");
    }
}

class OnState extends State {

    public OnState() {
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching light off... ");
        ls.setState(new OffState());
    }
}

class OffState extends State {

    public OffState() {
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching light on... ");
        ls.setState(new OnState());
    }

}

class LightSwitch {

    private State state;//OnState, OffState

    public LightSwitch() {
        this.state = new OffState();
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }

    public void setState(State state) {
        this.state=state;
    }
}

class Demo {

    public static void main(String[] args) {
        LightSwitch ls = new LightSwitch();
        ls.off();
        ls.on();
        ls.on();
        ls.off();
        ls.off();

    }
}
