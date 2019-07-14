private boolean subsumed(Type type1,Type type2){
  return subsumedInner(type1,type2,new TypeStack());
}
