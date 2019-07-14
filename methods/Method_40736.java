private Map<Type,Type> compressArrows(Map<Type,Type> arrows){
  Map<Type,Type> ret=new HashMap<>();
  for (  Map.Entry<Type,Type> e1 : arrows.entrySet()) {
    boolean subsumed=false;
    for (    Map.Entry<Type,Type> e2 : arrows.entrySet()) {
      if (e1 != e2 && subsumed(e1.getKey(),e2.getKey())) {
        subsumed=true;
        break;
      }
    }
    if (!subsumed) {
      ret.put(e1.getKey(),e1.getValue());
    }
  }
  return ret;
}
