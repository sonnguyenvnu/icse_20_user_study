default boolean hasRev(String relation,String type){
  return has(relation,type,Relation.Direction.REVERSE);
}
