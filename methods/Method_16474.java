/** 
 * @see this#relations(Relation.Direction, String, String)
 * @see Relation.Direction#POSITIVE
 */
default C relationsPos(String type,String relation){
  return relations(Relation.Direction.POSITIVE,type,relation);
}
