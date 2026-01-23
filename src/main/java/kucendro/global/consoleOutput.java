package kucendro.global;

import kucendro.global.interfaces.consoleHeadline;

public class consoleOutput implements consoleHeadline {

    public static void printHeadline(String content) {
        System.out.println(LINE);
        System.out.println(content);
        System.out.println(LINE);
    }

    public static void printLine() {
        System.out.println(LINE);
    }

}
