
public enum Ship {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5,null,null, null, null),
    BATTLESHIP("Battleship", 4, null,null, null,null),
    SUBMARINE("Submarine", 3, null,null, null,null),
    CRUISER("Cruiser", 3, null,null, null,null),
    DESTROYER("Destroyer", 2, null,null, null,null);

    private final String name;
    private final int size;
    private int[] c1;
    private int[] c2;
    private int[] c3;
    private int[] c4;

    Ship(String name, int size, int[] c1, int[] c2, int[] c3, int[] c4) {
        this.name = name;
        this.size = size;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }


    public int[] getC1() {
        return c1;
    }

    public void setC1(int[] c1) {
        this.c1 = c1;
    }

    public int[] getC2() {
        return c2;
    }

    public void setC2(int[] c2) {
        this.c2 = c2;
    }

    public int[] getC3() {
        return c3;
    }

    public void setC3(int[] c3) {
        this.c3 = c3;
    }

    public int[] getC4() {
        return c4;
    }

    public void setC4(int[] c4) {
        this.c4 = c4;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
