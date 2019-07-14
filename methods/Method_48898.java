@Override public GraphCentricQueryBuilder hasNot(String key,Object value){
  return has(key,Cmp.NOT_EQUAL,value);
}
