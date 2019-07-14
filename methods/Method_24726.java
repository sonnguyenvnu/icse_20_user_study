static private int howManyInts(List<List<Handle>> handles){
  int count=0;
  for (  List<Handle> list : handles) {
    for (    Handle n : list) {
      if ("int".equals(n.type) || "hex".equals(n.type) || "webcolor".equals(n.type)) {
        count++;
      }
    }
  }
  return count;
}
