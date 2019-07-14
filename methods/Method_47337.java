public static ArrayList<HybridFile> toHybridFileArrayList(LinkedList<String> a){
  ArrayList<HybridFile> b=new ArrayList<>();
  for (  String s : a) {
    HybridFile hFile=new HybridFile(OpenMode.UNKNOWN,s);
    hFile.generateMode(null);
    b.add(hFile);
  }
  return b;
}
