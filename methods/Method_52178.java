private static boolean containsWord(String name,String word){
  int index=name.indexOf(word);
  if (index >= 0 && name.length() > index + word.length()) {
    return Character.isUpperCase(name.charAt(index + word.length()));
  }
  return false;
}
