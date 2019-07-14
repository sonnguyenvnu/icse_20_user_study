/** 
 * Removed any of the specified characters from a column. For instance, the following code removes dollar signs and commas from column 2: <pre> table.removeTokens(",$", 2); </pre>
 * @param column ID number of the column to process
 */
public void removeTokens(String tokens,int column){
  for (int row=0; row < rowCount; row++) {
    String s=getString(row,column);
    if (s != null) {
      char[] c=s.toCharArray();
      int index=0;
      for (int j=0; j < c.length; j++) {
        if (tokens.indexOf(c[j]) == -1) {
          if (index != j) {
            c[index]=c[j];
          }
          index++;
        }
      }
      if (index != c.length) {
        setString(row,column,new String(c,0,index));
      }
    }
  }
}
