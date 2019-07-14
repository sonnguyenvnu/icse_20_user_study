protected SparseVector toVector(List<String> wordList){
  SparseVector vector=new SparseVector();
  for (  String word : wordList) {
    int id=id(word);
    Double f=vector.get(id);
    if (f == null) {
      f=1.;
      vector.put(id,f);
    }
 else {
      vector.put(id,++f);
    }
  }
  return vector;
}
