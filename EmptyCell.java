
/**
 * Write a description of class EmptyCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EmptyCell extends Cell
{
    /**
     * Text for spreadsheet cell display, must be exactly length 10
     * 
     * @return string
     */
    public String abbreviatedCellText() {
        return "          ";
    }

    /**
     * Text for individual cell inspection, not truncated or padded
     * 
     * @return string
     */
    public String fullCellText() {
        return "";
    }    
}