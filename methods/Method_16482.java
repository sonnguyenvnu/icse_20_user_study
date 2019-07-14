default boolean hasRev(String relation,String type,String to){
  return has(relation,type,to,Relation.Direction.REVERSE);
}
