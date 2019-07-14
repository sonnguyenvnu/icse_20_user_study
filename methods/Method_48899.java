@Override public <T extends Comparable<?>>GraphCentricQueryBuilder interval(String s,T t1,T t2){
  has(s,Cmp.GREATER_THAN_EQUAL,t1);
  return has(s,Cmp.LESS_THAN,t2);
}
