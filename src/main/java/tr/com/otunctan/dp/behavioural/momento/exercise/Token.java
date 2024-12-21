package tr.com.otunctan.dp.behavioural.momento.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token {
    public int value = 0;

    public Token(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Token{" +
                "value=" + value +
                '}';
    }
}

class Memento {
    private final List<Token> tokens;

    public Memento(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Token> getTokens() {
        return tokens;
    }
}

class TokenMachine {
    public List<Token> tokens = new ArrayList<>();

    public Memento addToken(int value) {
        return addToken(new Token(value));
    }

    public Memento addToken(Token token) {
        tokens.add(token);
        return new Memento(new ArrayList<>(tokens));
    }

    public void revert(Memento m) {
        tokens = new ArrayList<>(m.getTokens());
    }

    @Override
    public String toString() {
        return "TokenMachine{" +
                "tokens=" + tokens +
                '}';
    }
}


class Demo{
    public static void main(String[] args) {
        TokenMachine tm = new TokenMachine();
        Memento m1 = tm.addToken(10);
        Memento m2 = tm.addToken(20);
        Memento m3 = tm.addToken(new Token(30));
        System.out.println(tm);

        tm.revert(m1);

        System.out.println(tm);
    }
}