private static DoubleArrayTrie<Integer> loadDictionary(String trainingFile,String dictionaryFile) throws IOException {
  FrequencyMap dictionaryMap=new FrequencyMap();
  if (dictionaryFile == null) {
    out.printf("?????%s?????...\n",trainingFile);
    loadWordFromFile(trainingFile,dictionaryMap,true);
  }
 else {
    out.printf("?????%s?????...\n",trainingFile);
    loadWordFromFile(dictionaryFile,dictionaryMap,false);
  }
  DoubleArrayTrie<Integer> dat=new DoubleArrayTrie<Integer>();
  dat.build(dictionaryMap);
  out.printf("???????????%d?????%d\n",dictionaryMap.size(),dictionaryMap.totalFrequency);
  return dat;
}
