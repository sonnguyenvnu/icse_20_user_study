/** 
 * Returns <code>true</code> if there is a next page, i.e. we are not at the last page.
 */
public boolean hasNextPage(){
  return currentPage < totalPages - 1;
}
