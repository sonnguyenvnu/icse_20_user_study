@Override public Q hasNot(String key){
  return has(key,Cmp.EQUAL,null);
}
