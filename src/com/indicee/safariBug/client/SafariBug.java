package com.indicee.safariBug.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * This module is reproduces what looks like a Safari/JavaScriptCore bug when the module
 * is compiled without -draftCompile. Compilation style (i.e obfuscated/pretty/detailed) does
 * make a difference - obfuscated fails faster than detailed but both fail.
 *
 * This problem manifests itself on Safari/Lion 5.1.4, 5.1.4 but apparently not 5.1.2
 */
public class SafariBug implements EntryPoint {

    public static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }

    public void onModuleLoad() {
        final Label label = new Label("Hello");
        RootPanel.get("slot1").add(label);
        int count = 0;
        try {
            for (; count < 1000; count++) {
                // the problem seems to be a side effect of populating the stack trace in the exception
                // base constructor.
                Exception e = new TestException("Hello");
            }
            label.setText("Completed OK");
        } catch (Exception e) {
            // seems to fail at 222 with DETAILED & PRETTY, 67 with OBFUSCATED
            label.setText("Failed at count " + count + " msg: " + e.getMessage());
        }
    }
}
