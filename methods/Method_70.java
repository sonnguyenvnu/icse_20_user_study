public boolean isUnique(String word){
  return !map.containsKey(getKey(word)) || map.get(getKey(word)).equals(word);
}
