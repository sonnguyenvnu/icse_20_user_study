/** 
 * @see this#relations(Relation.Direction, String)
 * @see Relation.Direction#POSITIVE
 */
default C relationsPos(String relation){
  return relations(Relation.Direction.POSITIVE,relation);
}
