@Override protected IdVector generateKey(String sentence){
  IdVector idVector=new IdVector(sentence);
  if (idVector.idArrayList.size() == 0)   return null;
  return idVector;
}
