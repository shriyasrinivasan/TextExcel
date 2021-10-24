import java.text.DecimalFormat;

/**
 * Write a description of class ValueCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ValueCell extends RealCell
{
    private String originalValue;

    public ValueCell(String value) {
        super(Double.parseDouble(value));
        this.originalValue = value;
    }

    protected ValueCell(String value, double doubleValue) {
        super(doubleValue);
        this.originalValue = value;
    }

    public String abbreviatedCellText() {
        String fullVal = (new DecimalFormat("##########.##########")).format(value);
        if (fullVal.indexOf(".") < 0) {
            fullVal = fullVal + ".0";
        }
        int length = fullVal.length();
        if (length > 10) {
            length = 10;
        }
        return String.format("%-10s", fullVal.substring(0, length));
    }

    public String fullCellText() {
        return originalValue;
    }
}
