@Override public <T extends Comparable<?>>Q interval(String key,T start,T end){
  addConstraint(key,Cmp.GREATER_THAN_EQUAL,start);
  return addConstraint(key,Cmp.LESS_THAN,end);
}
