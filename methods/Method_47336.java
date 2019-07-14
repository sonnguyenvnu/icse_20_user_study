public static ArrayList<HybridFile> toHybridFileConcurrentRadixTree(ConcurrentRadixTree<VoidValue> a){
  ArrayList<HybridFile> b=new ArrayList<>();
  for (  CharSequence o : a.getKeysStartingWith("")) {
    HybridFile hFile=new HybridFile(OpenMode.UNKNOWN,o.toString());
    hFile.generateMode(null);
    b.add(hFile);
  }
  return b;
}
