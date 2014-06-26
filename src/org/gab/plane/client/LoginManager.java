package org.gab.plane.client;


import org.gab.plane.client.widgets.LoginWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LoginManager implements EntryPoint {

    public void onModuleLoad() {
        // set widget on "content" element
        RootPanel content = RootPanel.get("content");
        if (content != null) {
            content.add(new LoginWidget());
            //modifica da desktop
        }
        //modifica da notebook
        //modifca not 2801
    }
}
