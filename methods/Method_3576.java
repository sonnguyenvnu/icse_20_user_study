@Override public void addSentence(String sentence){
  T key=generateKey(sentence);
  if (key == null)   return;
  Set<String> set=storage.get(key);
  if (set == null) {
    set=new TreeSet<String>();
    storage.put(key,set);
  }
  set.add(sentence);
}
