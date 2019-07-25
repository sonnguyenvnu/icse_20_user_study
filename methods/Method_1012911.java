/** 
 * Builds the search document.
 */
public Document build(){
  prepareData();
  return toDocument();
}
