/** 
 * @webref table:method
 * @brief Removes a row from a table
 * @param row ID number of the row to remove
 * @see Table#addRow()
 * @see Table#clearRows()
 */
public void removeRow(int row){
  for (int col=0; col < columns.length; col++) {
switch (columnTypes[col]) {
case CATEGORY:
case INT:
{
        int[] intTemp=new int[rowCount - 1];
        System.arraycopy(columns[col],0,intTemp,0,row);
        System.arraycopy(columns[col],row + 1,intTemp,row,(rowCount - row) - 1);
        columns[col]=intTemp;
        break;
      }
case LONG:
{
      long[] longTemp=new long[rowCount - 1];
      System.arraycopy(columns[col],0,longTemp,0,row);
      System.arraycopy(columns[col],row + 1,longTemp,row,(rowCount - row) - 1);
      columns[col]=longTemp;
      break;
    }
case FLOAT:
{
    float[] floatTemp=new float[rowCount - 1];
    System.arraycopy(columns[col],0,floatTemp,0,row);
    System.arraycopy(columns[col],row + 1,floatTemp,row,(rowCount - row) - 1);
    columns[col]=floatTemp;
    break;
  }
case DOUBLE:
{
  double[] doubleTemp=new double[rowCount - 1];
  System.arraycopy(columns[col],0,doubleTemp,0,row);
  System.arraycopy(columns[col],row + 1,doubleTemp,row,(rowCount - row) - 1);
  columns[col]=doubleTemp;
  break;
}
case STRING:
{
String[] stringTemp=new String[rowCount - 1];
System.arraycopy(columns[col],0,stringTemp,0,row);
System.arraycopy(columns[col],row + 1,stringTemp,row,(rowCount - row) - 1);
columns[col]=stringTemp;
}
}
}
rowCount--;
}
