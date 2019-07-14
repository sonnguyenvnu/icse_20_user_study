/** 
 * A query is considered 'simple' if it is comprised of just one sub-query and that query is fitted (i.e. does not require an in-memory filtering).
 * @return
 */
public boolean isSimple(){
  return queries.size() == 1 && queries.get(0).isFitted() && queries.get(0).isSorted();
}
