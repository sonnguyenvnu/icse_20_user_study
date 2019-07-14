/** 
 * Stores a Qid associated to a new cell
 * @param id : the internal reconciliation id of the new cell
 * @param qid : the associated Qid returned by Wikibase
 */
public void setQid(long id,String qid){
  map.put(id,qid);
}
