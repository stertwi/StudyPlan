package org.gab.plane.client;

import org.gab.plane.client.widgets.RegisterWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Register implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		RootPanel content = RootPanel.get("content");
        if (content != null) {
            content.add(new RegisterWidget());
        }
	}

}
