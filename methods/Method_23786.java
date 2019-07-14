/** 
 * Return the row that contains the first String that matches.
 * @param value the String to match
 * @param column ID number of the column to search
 */
public int findRowIndex(String value,int column){
  checkColumn(column);
  if (columnTypes[column] == STRING) {
    String[] stringData=(String[])columns[column];
    if (value == null) {
      for (int row=0; row < rowCount; row++) {
        if (stringData[row] == null)         return row;
      }
    }
 else {
      for (int row=0; row < rowCount; row++) {
        if (stringData[row] != null && stringData[row].equals(value)) {
          return row;
        }
      }
    }
  }
 else {
    for (int row=0; row < rowCount; row++) {
      String str=getString(row,column);
      if (str == null) {
        if (value == null) {
          return row;
        }
      }
 else       if (str.equals(value)) {
        return row;
      }
    }
  }
  return -1;
}
