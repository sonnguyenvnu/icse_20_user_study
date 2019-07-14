default boolean hasPos(String relation,String type,String to){
  return has(relation,type,to,Relation.Direction.POSITIVE);
}
