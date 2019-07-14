/** 
 * @param columnName title of the column to process
 */
public void removeTokens(String tokens,String columnName){
  removeTokens(tokens,getColumnIndex(columnName));
}
