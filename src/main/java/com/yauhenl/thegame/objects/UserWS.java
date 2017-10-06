package com.yauhenl.thegame.objects;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserWS {
    private Integer worldId;
    private Integer bloopId;

    public Integer getWorldId() {
        return worldId;
    }

    public void setWorldId(Integer worldId) {
        this.worldId = worldId;
    }

    public Integer getBloopId() {
        return bloopId;
    }

    public void setBloopId(Integer bloopId) {
        this.bloopId = bloopId;
    }
}
