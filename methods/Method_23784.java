/** 
 * @param column ID number of the column to search
 */
public String[] getStringColumn(int column){
  String[] outgoing=new String[rowCount];
  for (int i=0; i < rowCount; i++) {
    outgoing[i]=getString(i,column);
  }
  return outgoing;
}
