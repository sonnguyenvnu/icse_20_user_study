public String getKey(String word){
  if (word.length() <= 2) {
    return word;
  }
  return word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
}
