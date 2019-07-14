/** 
 * @webref table:method
 * @brief Finds multiple rows that match the given expression
 * @param regexp the regular expression to match
 * @param column ID number of the column to search
 * @see Table#getRow(int)
 * @see Table#rows()
 * @see Table#findRow(String,int)
 * @see Table#findRows(String,int)
 * @see Table#matchRow(String,int)
 */
public Iterable<TableRow> matchRows(final String regexp,final int column){
  return new Iterable<TableRow>(){
    public Iterator<TableRow> iterator(){
      return matchRowIterator(regexp,column);
    }
  }
;
}
