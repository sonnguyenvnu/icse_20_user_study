/** 
 * Converts the entries from this query result into actual  {@link JanusGraphRelation}.
 * @return
 */
public Iterable<JanusGraphRelation> relations(){
  return RelationConstructor.readRelation(vertex,this,tx);
}
