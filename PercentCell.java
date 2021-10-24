import java.text.DecimalFormat;

/**
 * Write a description of class PercentCell here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class PercentCell extends RealCell {

   public PercentCell(String percent) {
        super(Double.parseDouble(percent.substring(0 , percent.length() - 1))/100);
   }

   public String abbreviatedCellText() {
        return String.format("%-10s", String.format("%d%%", (int)(value * 100)));
   }
   
   public String fullCellText() {
        return (new DecimalFormat("##########.##########")).format(value);
   }
}

