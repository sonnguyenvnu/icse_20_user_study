private static Charset guess(String html,String patten){
  int idx=html.indexOf(patten);
  if (idx != -1) {
    int start=idx + patten.length();
    int end=html.indexOf('"',start);
    if (end != -1) {
      try {
        return Charset.forName(html.substring(start,end));
      }
 catch (      Exception ignore) {
      }
    }
  }
  return null;
}
