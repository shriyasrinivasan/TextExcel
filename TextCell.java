
public class TextCell extends Cell implements Comparable <TextCell>
{
    String text;

    public TextCell(String text)
    {
        text = text.trim();
        int startPos = 0;
        int endPos = text.length();
        // Check if the string starts/ends with a quote. 
        // If so, adjust startPos and endPos
        if (text.startsWith("\"")) {
            startPos += 1;
        }
        if (text.endsWith("\"")) {
            endPos -= 1;
        }
        this.text = text.substring(startPos, endPos);
    }
    
    public String abbreviatedCellText() {
        // Truncate to a maximum of 10 characters
        int length = text.length();
        if (text.length() > 10) {
            length = 10;
        }
        return String.format("%-10s", text.substring(0, length));
    }
    
    public String fullCellText() {
        return "\"" + text + "\"";
    }

    public String getText(){ return text;}
    
    @Override
    public int compareTo(TextCell compareToCell) {
        String compareCell =((TextCell)compareToCell).getText();
        int value = getText().compareTo(compareCell);
        return value;
    }
}
