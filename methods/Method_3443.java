private static boolean isNer(IWord word,String nerTag[]){
  for (  String tag : nerTag) {
    if (word.getLabel().startsWith(tag)) {
      word.setLabel(tag);
      return true;
    }
  }
  return false;
}
