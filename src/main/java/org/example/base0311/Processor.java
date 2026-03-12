package org.example.base0311;

public class Processor {
    private final String callId;

    public Processor(String callId) {
        this.callId = callId;
        System.out.println("创建Processor：" + callId + ", thread=" + Thread.currentThread().getName());
    }
    public String getCallId() {
        return callId;
    }
}
