
/**
 * Write a description of class RealCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RealCell extends Cell
{
    protected double value;
    
    protected RealCell(double value) {
        this.value = value;
    }
    
    public double getDoubleValue() {
        return value;
    }
}
