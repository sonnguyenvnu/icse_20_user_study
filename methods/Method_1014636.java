public static NumbericRenderData build(String... text){
  if (null == text)   return null;
  List<TextRenderData> numbers=new ArrayList<TextRenderData>();
  for (  String txt : text) {
    numbers.add(new TextRenderData(txt));
  }
  return new NumbericRenderData(numbers);
}
