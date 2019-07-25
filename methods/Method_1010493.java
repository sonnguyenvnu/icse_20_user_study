private static void fill(HashSet<String> usedIndexes,BaseInfo bi,IndexEncoder indexEncoder){
  int v=bi.internalKey();
  String s;
  do {
    s=indexEncoder.index(v);
    v++;
  }
 while (!usedIndexes.add(s));
  bi.setIndex(s);
}
