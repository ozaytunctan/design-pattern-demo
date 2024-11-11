package tr.com.otunctan.dp.creational.builder.v1;

public class Demo {
    public static void main(String[] args) {
        String hello = "Hello";

        System.out.println("<p>" + hello + "</p>");

        String[] words = {"hello", "world"};

        String text = "<ul>" + System.lineSeparator() + "<li>" + words[0] + "</li>" + System.lineSeparator() + "</ul>";
        System.out.println(text);

        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        sb.append(System.lineSeparator());
        for (String word : words) {
            sb.append("<li>");
            sb.append(word);
            sb.append("</li>");
            sb.append(System.lineSeparator());
        }
        sb.append("</ul>");

        System.out.println(sb);


    }
}
