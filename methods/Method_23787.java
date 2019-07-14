/** 
 * Return a list of rows that contain the String passed in. If there are no matches, a zero length array will be returned (not a null array).
 * @param value the String to match
 * @param column ID number of the column to search
 */
public int[] findRowIndices(String value,int column){
  int[] outgoing=new int[rowCount];
  int count=0;
  checkColumn(column);
  if (columnTypes[column] == STRING) {
    String[] stringData=(String[])columns[column];
    if (value == null) {
      for (int row=0; row < rowCount; row++) {
        if (stringData[row] == null) {
          outgoing[count++]=row;
        }
      }
    }
 else {
      for (int row=0; row < rowCount; row++) {
        if (stringData[row] != null && stringData[row].equals(value)) {
          outgoing[count++]=row;
        }
      }
    }
  }
 else {
    for (int row=0; row < rowCount; row++) {
      String str=getString(row,column);
      if (str == null) {
        if (value == null) {
          outgoing[count++]=row;
        }
      }
 else       if (str.equals(value)) {
        outgoing[count++]=row;
      }
    }
  }
  return PApplet.subset(outgoing,0,count);
}
