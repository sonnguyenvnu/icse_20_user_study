private static String extract(String text){
  final int idx=text.indexOf('^');
  if (idx == -1) {
    return text;
  }
  return text.substring(0,idx);
}
