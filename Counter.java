// Aryeh Bruce
/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This class holds the and keeps track of the number of whatever you are counting.
 */
public class Counter {

    private int count;

    /**
     * This method represents the constructor for the count class.
     */
    public Counter() {
        this.count = 0;
    }
    /**
     * This method will add a received number to the count.
     *
     * @param number the amount to add to the count.
     */
    public void increase(int number) {
        count = count + number;
    }
    /**
     * This method will subtract a number from the current count.
     *
     * @param number the amount to subtract from the count.
     */
    public void decrease(int number) {
        count = count - number;
    }
    /**
     * This method will return the count.
     *
     * @return the count number.
     */
    public int getValue() {
        return count;
    }
}
