package org.gab.plane.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface LoginService extends RemoteService {
    public String login(String usr, String pwd);
    public String getPiano(String id);
    public String getAtt(String att);
}
