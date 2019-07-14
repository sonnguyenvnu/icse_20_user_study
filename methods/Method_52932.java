private void dump(XmlNode node,String prefix){
  writer.print(prefix);
  writer.print(node.getXPathNodeName());
  String image=node.getImage();
  image=StringUtil.escapeWhitespace(image);
  List<String> extras=new ArrayList<>();
  Iterator<Attribute> iterator=node.getAttributeIterator();
  while (iterator.hasNext()) {
    Attribute attribute=iterator.next();
    extras.add(attribute.getName() + "=" + StringUtil.escapeWhitespace(attribute.getValue()));
  }
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
