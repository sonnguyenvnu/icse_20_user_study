private void dump(ApexNode<?> node,String prefix){
  writer.print(prefix);
  writer.print(node.getXPathNodeName());
  String image=node.getImage();
  image=StringUtil.escapeWhitespace(image);
  List<String> extras=new ArrayList<>();
  if (image != null || !extras.isEmpty()) {
    writer.print(':');
    if (image != null) {
      writer.print(image);
    }
    for (    String extra : extras) {
      writer.print('(');
      writer.print(extra);
      writer.print(')');
    }
  }
  writer.println();
}
