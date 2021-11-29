package sprint07.task03;

import java.io.*;

class Main {
    public static void writeFile(String filename, String text) {
        byte[] arr = text.getBytes();
        StringBuilder sb = new StringBuilder();
        String binary;
        for(int b : arr){
            binary = Integer.toBinaryString(b);
            if (binary.length() < 5) binary = "000" + binary;
            if (binary.length() < 6) binary = "00" + binary;
            if (binary.length() < 7) binary = "0" + binary;
            sb.append(binary);
        }
        try {
            java.nio.file.Files.write(java.nio.file.Path.of(filename), sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeFile("src/main/java/sprint07/task03/file.txt", "Hello!");
    }
}
