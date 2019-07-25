/** 
 * The index the search failed on.
 */
@Override public String index(){
  if (shardTarget != null) {
    return shardTarget.getIndex();
  }
  return null;
}
