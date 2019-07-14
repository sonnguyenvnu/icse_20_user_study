/** 
 * @param column ID number of the column to trim
 */
public void trim(int column){
  if (columnTypes[column] == STRING) {
    String[] stringData=(String[])columns[column];
    for (int row=0; row < rowCount; row++) {
      if (stringData[row] != null) {
        stringData[row]=PApplet.trim(stringData[row]);
      }
    }
  }
}
