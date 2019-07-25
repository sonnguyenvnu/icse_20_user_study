/** 
 * Check if the given data page is in this trunk page.
 * @param dataPageId the page id
 * @return true if it is
 */
boolean contains(int dataPageId){
  for (int i=0; i < pageCount; i++) {
    if (pageIds[i] == dataPageId) {
      return true;
    }
  }
  return false;
}
