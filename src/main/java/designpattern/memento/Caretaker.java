package designpattern.memento;

import java.util.ArrayList;
import java.util.Scanner;

public class Caretaker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor textEditor = new TextEditor();
        ArrayList<TextEditor.Memento> savePoints = new ArrayList<>();
        int nowPoint = -1;
        String input = "";

        printBanner();

        while(true) {
            printStringBetweenTwoLines(textEditor.getText());
            System.out.print(">> ");
            input = scanner.nextLine();

            switch (input) {
                case "quit":
                    return;
                case "undo":
                    textEditor.restoreFromMemento(savePoints.get(--nowPoint));
                    break;
                case "redo":
                    textEditor.restoreFromMemento(savePoints.get(++nowPoint));
                    break;
                default:  // save
                    textEditor.setText(textEditor.text + "\n" + input);
                    savePoints.add(textEditor.saveToMemento());
                    nowPoint++;
                    break;
            }
            System.out.println();
        }

    }

    private static void printStringBetweenTwoLines(String string) {
        if(string.isEmpty()) return;
        System.out.println("======================");
        System.out.println(string + "\n");
        System.out.println("======================");
        System.out.println();
    }

    private static void printBanner() {
        System.out.println("******************************");
        System.out.println("save the content automatically when hit enter.");
        System.out.println("commands: \'undo\', \'redo\', \'quit\'");
        System.out.println("******************************");
        System.out.println();
    }

}
