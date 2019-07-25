/** 
 * check if an entry exists
 * @param id the id of the entry
 * @return true if the entry exist and is non-empty, false otherwise
 */
@Override public boolean exist(String id){
  return this.map.containsKey(id);
}
