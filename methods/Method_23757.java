/** 
 * Set the entire table to a specific data type.
 */
public void setTableType(String type){
  for (int col=0; col < getColumnCount(); col++) {
    setColumnType(col,type);
  }
}
