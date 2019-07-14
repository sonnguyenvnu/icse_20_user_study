/** 
 * Concatenates last search term with new one.
 * @param searchTerm searchTerm {@link SearchTerm} concatenate.
 * @see #and(SearchTerm)
 * @see #or(SearchTerm)
 */
protected void concat(SearchTerm searchTerm){
  if (nextIsNot) {
    searchTerm=new NotTerm(searchTerm);
    nextIsNot=false;
  }
  if (operatorAnd) {
    and(searchTerm);
  }
 else {
    or(searchTerm);
  }
}
