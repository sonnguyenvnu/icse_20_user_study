private static void addPair(String from,String to,String label,DictionaryMaker dictionaryMaker){
  dictionaryMaker.add(new Word(from + "@" + to,label));
  dictionaryMaker.add(new Word(from + "@","??"));
}
