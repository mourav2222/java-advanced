package com.mkm.yroki;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by papa on 25.03.2022
 */

class Browser {

    Collection<Page> pages = new ArrayList<>();

    synchronized Page openPage() {

        Page page = new Page(this);
        pages.add(page);
        return page;

    }

    synchronized void removePage(Page p) {

        pages.remove(p);

    }

    synchronized void close() {

        pages.stream().forEach((p) -> p.close());

//        for (Page page : pages) {
//            page.close();
//        }

    }

}

class Page {

    Browser browser;

    public Page(Browser browser) {
        this.browser = browser;
    }

    synchronized void close() {
//        System.out.println("Closed page");
        browser.removePage(this);
    }
}

public class DeadLock2 {

    public static void main(String[] args) {
        Browser browser = new Browser();

        for (int i = 0; i < 10; i++) {
            System.out.println("Page " + i);
            Page page = browser.openPage();
            new Thread(() -> {

                page.close();
                System.out.println("Closed page");

            }).start();
        }

        browser.close();
        System.out.println("finished");
    }
}
