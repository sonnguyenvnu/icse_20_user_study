@Override public GraphCentricQueryBuilder has(String key){
  return has(key,Cmp.NOT_EQUAL,null);
}
