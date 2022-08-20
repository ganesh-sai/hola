package com.gs.hola;

public class BackendDTO {
    private String greeting;
    private long time;
    private String ipAddress;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "BackendDTO{" +
                "greeting='" + greeting + '\'' +
                ", time=" + time +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
