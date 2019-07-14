/** 
 * Adds a new key as a deletion to this mutation
 * @param key
 */
public void deletion(K key){
  if (deletions == null)   deletions=new ArrayList<>();
  deletions.add(key);
}
