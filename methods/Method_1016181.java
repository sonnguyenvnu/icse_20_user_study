/** 
 * Returns whether filter accepts specified node text or not.
 * @param nodeText        node text
 * @param searchRequest   single search request
 * @param searchFromStart whether should start searching from the beginning of the node text
 * @return true if filter accepts specified node text, false otherwise
 */
protected boolean accept(final String nodeText,final String searchRequest,final boolean searchFromStart){
  return searchFromStart ? nodeText.startsWith(searchRequest) : nodeText.contains(searchRequest);
}
