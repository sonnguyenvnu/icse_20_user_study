public static long compute(List<CommonSynonymDictionary.SynonymItem> synonymItemListA,List<CommonSynonymDictionary.SynonymItem> synonymItemListB){
  long[] arrayA=new long[synonymItemListA.size()];
  long[] arrayB=new long[synonymItemListB.size()];
  int i=0;
  for (  CommonSynonymDictionary.SynonymItem item : synonymItemListA) {
    arrayA[i++]=item.entry.id;
  }
  i=0;
  for (  CommonSynonymDictionary.SynonymItem item : synonymItemListB) {
    arrayB[i++]=item.entry.id;
  }
  return compute(arrayA,arrayB);
}
