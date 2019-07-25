public static Rainbow build(HtmlColorAndStyle color){
  if (color == null) {
    throw new IllegalArgumentException();
  }
  final Rainbow result=new Rainbow(0);
  result.colors.add(color);
  return result;
}
