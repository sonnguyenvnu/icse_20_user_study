@Override public GraphCentricQueryBuilder hasNot(String key){
  return has(key,Cmp.EQUAL,null);
}
