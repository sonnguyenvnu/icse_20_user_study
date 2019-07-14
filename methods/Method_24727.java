static private int howManyFloats(List<List<Handle>> handles){
  int count=0;
  for (  List<Handle> list : handles) {
    for (    Handle n : list) {
      if ("float".equals(n.type)) {
        count++;
      }
    }
  }
  return count;
}
