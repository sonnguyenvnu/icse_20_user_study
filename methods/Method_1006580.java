private static List<String> extract(Iterable<String> generator){
  List<String> result=new ArrayList<String>();
  for (  String e : generator) {
    result.add(e);
  }
  return result;
}
