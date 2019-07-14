/** 
 * Remove any of the specified characters from the entire table.
 * @webref table:method
 * @brief Removes characters from the table
 * @param tokens a list of individual characters to be removed
 * @see Table#trim()
 */
public void removeTokens(String tokens){
  for (int col=0; col < getColumnCount(); col++) {
    removeTokens(tokens,col);
  }
}
