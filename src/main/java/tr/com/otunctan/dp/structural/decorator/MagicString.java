package tr.com.otunctan.dp.structural.decorator;

import java.util.regex.Pattern;

public class MagicString {
    private String str;

    public MagicString(String str) {
        this.str = str;
    }

    public int length() {
        return this.str.length();
    }

    public boolean isEmpty() {
        return this.str.isEmpty();
    }

    public boolean matches(String regex) {
        return Pattern.matches(regex, this.str);
    }

    public boolean contains(CharSequence s) {
        return str.contains(s.toString());
    }

    public String concat(String val) {
        return str.concat(val);
    }
}

class DemoStringDecorator {

    public static void main(String[] args) {
        MagicString string = new MagicString("hello");
        string.concat(" world");

    }
}
