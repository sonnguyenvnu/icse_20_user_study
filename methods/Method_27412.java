private void readNode(Object node,Table table){
  if (node instanceof TagNode) {
    TagNode tagNode=(TagNode)node;
    if (tagNode.getName().equals("td") || tagNode.getName().equals("th")) {
      Spanned result=this.getSpanner().fromTagNode(tagNode);
      table.addCell(result);
      return;
    }
    if (tagNode.getName().equals("tr")) {
      table.addRow();
    }
    for (    Object child : tagNode.getChildTags()) {
      readNode(child,table);
    }
  }
}
