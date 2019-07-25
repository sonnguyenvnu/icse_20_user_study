public static List<StringLocated> convert(List<String> strings){
  final List<StringLocated> result=new ArrayList<StringLocated>();
  LineLocationImpl location=new LineLocationImpl("block",null);
  for (  String s : strings) {
    location=location.oneLineRead();
    result.add(new StringLocated(s,location));
  }
  return result;
}
