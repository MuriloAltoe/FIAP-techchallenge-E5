package com.fiap.sus_triage.enums;

public enum RiskLevel {

    RED(1),
    ORANGE(2),
    YELLOW(3),
    GREEN(4),
    BLUE(5);

    private final int priority;

    RiskLevel(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
