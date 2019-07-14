/** 
 * Return the row that contains the first String that matches.
 * @param regexp the String to match
 * @param column ID number of the column to search
 */
public int matchRowIndex(String regexp,int column){
  checkColumn(column);
  if (columnTypes[column] == STRING) {
    String[] stringData=(String[])columns[column];
    for (int row=0; row < rowCount; row++) {
      if (stringData[row] != null && PApplet.match(stringData[row],regexp) != null) {
        return row;
      }
    }
  }
 else {
    for (int row=0; row < rowCount; row++) {
      String str=getString(row,column);
      if (str != null && PApplet.match(str,regexp) != null) {
        return row;
      }
    }
  }
  return -1;
}
