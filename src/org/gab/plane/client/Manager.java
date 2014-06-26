package org.gab.plane.client;

import org.gab.plane.client.widgets.LoginWidget;
import org.gab.plane.client.widgets.ManagerWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Manager implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		RootPanel content = RootPanel.get("content");
        if (content != null) {
            content.add(new ManagerWidget());
        }
	}

}
