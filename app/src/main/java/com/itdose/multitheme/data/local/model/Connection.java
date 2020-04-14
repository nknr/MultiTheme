package com.itdose.multitheme.data.local.model;

public class Connection {
    private int type;
    private boolean isConnected;

    public Connection(int type, boolean isConnected) {
        this.type = type;
        this.isConnected = isConnected;
    }

    public int getType() {
        return type;
    }

    public boolean getIsConnected() {
        return isConnected;
    }
}
