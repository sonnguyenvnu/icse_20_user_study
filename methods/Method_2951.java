private static int getId(String word,HashMap<String,Integer> wordMap,int defaultValue){
  Integer id=wordMap.get(word);
  if (id == null)   return defaultValue;
  return id;
}
