/** 
 * Add from and size to the ES query based on the 'LIMIT' clause
 * @param from starts from document at position from
 * @param size number of documents to return.
 */
private void setLimit(int from,int size){
  request.setFrom(from);
  if (size > -1) {
    request.setSize(size);
  }
}
