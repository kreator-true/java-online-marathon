package sprint03.task05;

enum ClientType {
    NEW(1),
    SILVER(12),
    GOLD(30),
    PLATINUM(60);

    private int months;

    ClientType(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    private double getCoefficient() {
        if(this == SILVER) return 0.25;
        if(this == GOLD) return 0.3;
        if(this == PLATINUM) return 0.35;
        return 0;
    }

    public double discount(){
        return (100 - getMonths() * getCoefficient()) / 100;
    }
}
