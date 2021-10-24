
//Update this file with your own code.
/**
 * Represents a location in the spreadsheet
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpreadsheetLocation extends Location
{
    private int row;
    private int col;
    
    public SpreadsheetLocation(String location) {
        char strCol = location.toUpperCase().charAt(0);
        this.col = strCol - 'A';
        String strRow = location.substring(1, location.length());
        this.row = Integer.parseInt(strRow) - 1;
    }
    
    @Override
    public int getRow()
    {
        return row;
    }

    @Override
    public int getCol()
    {
        return col;
    }
}
