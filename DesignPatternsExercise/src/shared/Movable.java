package shared;

public interface Movable {
    int getRow();
    int getCol();

    void increaseRow();
    void increaseCol();
    void decreaseRow();
    void decreaseCol();
}
