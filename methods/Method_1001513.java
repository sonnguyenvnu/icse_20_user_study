public static List<BrailleChar> build(String s){
  final List<BrailleChar> result=new ArrayList<BrailleChar>();
  for (int i=0; i < s.length(); i++) {
    result.add(BrailleChar.fromChar(s.charAt(i)));
  }
  return Collections.unmodifiableList(result);
}
