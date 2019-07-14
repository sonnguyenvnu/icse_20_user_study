private Table getTable(TagNode node){
  String border=node.getAttributeByName("border");
  boolean drawBorder=!"0".equals(border);
  Table result=new Table(drawBorder);
  readNode(node,result);
  return result;
}
