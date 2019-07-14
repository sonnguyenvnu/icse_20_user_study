public static String posOf(String tag){
  int index=tag.indexOf('-');
  if (index == -1) {
    return tag;
  }
  return tag.substring(index + 1);
}
