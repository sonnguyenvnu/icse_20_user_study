/** 
 * @webref table:method
 * @brief Finds multiple rows that contain the given value
 * @param value the value to match
 * @param column ID number of the column to search
 * @see Table#getRow(int)
 * @see Table#rows()
 * @see Table#findRow(String,int)
 * @see Table#matchRow(String,int)
 * @see Table#matchRows(String,int)
 */
public Iterable<TableRow> findRows(final String value,final int column){
  return new Iterable<TableRow>(){
    public Iterator<TableRow> iterator(){
      return findRowIterator(value,column);
    }
  }
;
}
