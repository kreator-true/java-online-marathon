package sprint07.task04;

import java.io.IOException;

class Main {
    public static String readFile(String filename){
        /*StringBuilder result = new StringBuilder();
        for (String s : java.nio.file.Files.readString(java.nio.file.Path.of(filename)).split("(?<=\\G.{7})")){
            result.append((char) Integer.parseInt(s, 2));
        }*/

        try {
            return java.util.Arrays.stream(java.nio.file.Files.readString(java.nio.file.Path.of(filename)).split("(?<=\\G.{7})"))
                                        .map(s -> String.valueOf((char) Integer.parseInt(s, 2)))
                                        .reduce((s, s2) -> s + s2).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(readFile("src/main/java/sprint07/task03/file.txt"));
    }
}
