/** 
 * Return a list of rows that contain the String passed in. If there are no matches, a zero length array will be returned (not a null array).
 * @param regexp the String to match
 * @param column ID number of the column to search
 */
public int[] matchRowIndices(String regexp,int column){
  int[] outgoing=new int[rowCount];
  int count=0;
  checkColumn(column);
  if (columnTypes[column] == STRING) {
    String[] stringData=(String[])columns[column];
    for (int row=0; row < rowCount; row++) {
      if (stringData[row] != null && PApplet.match(stringData[row],regexp) != null) {
        outgoing[count++]=row;
      }
    }
  }
 else {
    for (int row=0; row < rowCount; row++) {
      String str=getString(row,column);
      if (str != null && PApplet.match(str,regexp) != null) {
        outgoing[count++]=row;
      }
    }
  }
  return PApplet.subset(outgoing,0,count);
}
