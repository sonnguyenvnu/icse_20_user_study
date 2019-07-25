public static ArrayList<String> grep(final ArrayList<String> list,final int afterContext,final String pattern){
  final Iterator<String> i=list.iterator();
  int ac=0;
  String line;
  final ArrayList<String> result=new ArrayList<String>();
  while (i.hasNext()) {
    line=i.next();
    if (line.indexOf(pattern) >= 0) {
      result.add(line);
      ac=afterContext + 1;
    }
 else     if (ac > 0) {
      result.add(line);
    }
    ac--;
  }
  return result;
}
