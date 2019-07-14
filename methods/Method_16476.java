/** 
 * @see this#relations(Relation.Direction, String, String)
 * @see Relation.Direction#ALL
 */
default C relations(String type,String relation){
  return relations(Relation.Direction.ALL,type,relation);
}
