import java.util.ArrayList;
import java.util.Collections;
public class Spreadsheet
{
    private static final int MAX_ROWS = 20;
    private static final int MAX_COLS = 12;

    Cell [][] cells;

    public Spreadsheet(){
        cells = new Cell [MAX_ROWS][MAX_COLS];
        clearAll();
    }

    public String processCommand(String command)
    {
        if(command.equals("")){
            return "";
        }
        String[] tokens = command.split(" ");
        //System.out.println("Tokens:" + tokens);
        if (tokens.length == 1) {
            // It can either be clear, or a cell inspection
            if (tokens[0].equalsIgnoreCase("clear")) {
                clearAll();
                return getGridText();
            } else {
                // This is a cell inspection.
                return cellInspec(tokens[0]);
            }
        } else if (tokens.length == 2) {
            // It is clear a particular cell.
            if (tokens[0].equalsIgnoreCase("clear")) {
                clearParticularCell(tokens[1]);
                return getGridText();
            }
            if (tokens[0].equalsIgnoreCase("sorta")){
                sorta(tokens[1]);
            }
        } else {
            // Assignment.
            if (tokens[1].equals("=")) {
                //Location is token[0], and value is everything after =
                assignCell(tokens[0], command.substring(command.indexOf("=")+1));
                return getGridText();
            }
        }
        //throw new IllegalArgumentException("Unknown command " + command);
        return "";
    }
    // public void printArrayList(ArrayList<TextCell> list) {
        // System.out.println("Start========");
        // for(TextCell cell : list) {
            // System.out.println(cell.getText());
        // }
        // System.out.println("End=============");
    // }
    
    //sorta
    public String sorta (String cellRange){
        ArrayList<TextCell> storeStrings = new ArrayList<TextCell>();
        String[] tokens = cellRange.split("-");
        SpreadsheetLocation firstSL = new SpreadsheetLocation(tokens[0]);
        int firstRow = firstSL.getRow();
        int firstCol = firstSL.getCol();
        SpreadsheetLocation secondSL = new SpreadsheetLocation(tokens[1]);
        int secondRow = secondSL.getRow();
        int secondCol = secondSL.getCol();
        //System.out.println("start Rows Cols:" + firstRow + "," + firstCol);
        //System.out.println("end Rows Cols:" + secondRow + "," + secondCol);
        for (int i = firstRow; i <= secondRow; i++) {
            for (int j = firstCol; j <= secondCol; j++) {
                //System.out.println((TextCell)cells[i][j]);
                storeStrings.add((TextCell)cells[i][j]);
            }
        }
        
        //printArrayList(storeStrings);
        Collections.sort(storeStrings); 
        //printArrayList(storeStrings);
        for (int i = firstRow; i <= secondRow; i++) {
            for (int j = firstCol; j <= secondCol; j++) {
                cells[i][j] = storeStrings.get(0);
                storeStrings.remove(0);
            }
        }
        return getGridText();
    }

    public String cellInspec(String locationString){
        SpreadsheetLocation location = new SpreadsheetLocation(locationString);
        return cells[location.getRow()][location.getCol()].fullCellText();
    }

    public void clearAll(){
        for(int i = 0; i<getRows();i++) {
            for(int j = 0; j< getCols(); j++) {
                cells[i][j] = new EmptyCell();
            }
        }
    }

    public void clearParticularCell(String locationString){
        SpreadsheetLocation location = new SpreadsheetLocation(locationString);
        cells[location.getRow()][location.getCol()] = new EmptyCell();
    }

    public void assignCell(String locationString, String value){
        SpreadsheetLocation location = new SpreadsheetLocation(locationString);
        value = value.trim();
        if(value.startsWith("\"")) {
            //it has a quote
            cells[location.getRow()][location.getCol()] = new TextCell(value);
        } else if(value.endsWith("%")) {
            //it has a percent
            cells[location.getRow()][location.getCol()] = new PercentCell(value);
        } else {
            //it doesn't have anything so it is a real cell
            cells[location.getRow()][location.getCol()] = new ValueCell(value);
        }
    }

    public int getRows()
    {
        return MAX_ROWS;
    }

    public int getCols()
    {
        return MAX_COLS;
    }

    public Cell getCell(Location loc)
    {
        int row = loc.getRow();
        int col = loc.getCol();
        return cells[row][col];
    }

    public String getGridText()
    {
        String output = "   |A         |B         |C         |D         |E         |F         |G         |H         |I         |J         |K         |L         |\n";
        for(int i = 0; i < MAX_ROWS; i++) {
            output += String.format("%-2d |", i + 1);
            for (int j = 0; j < MAX_COLS; j++) {
                output += cells[i][j].abbreviatedCellText() + "|";
            }
            output += "\n";
        }
        return output;
    }

}
