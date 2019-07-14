protected void fixText(){
  for (  Text fosterText : fosterTexts) {
    Element lastTable=findLastTable(fosterText);
    fosterText.detachFromParent();
    Node tablesPreviousNode=lastTable.getPreviousSibling();
    if (tablesPreviousNode.getNodeType() == Node.NodeType.TEXT) {
      Text textNode=(Text)tablesPreviousNode;
      String text=textNode.getNodeValue();
      textNode.setNodeValue(text + fosterText.getNodeValue());
    }
 else {
      lastTable.getParentNode().insertBefore(fosterText,lastTable);
    }
  }
}
