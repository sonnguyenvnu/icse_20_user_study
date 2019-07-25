protected Node wrap(AbstractWmlConversionContext context,String result,Document doc){
  RPr rPr=null;
  Node node=null;
  if (result != null) {
    node=createNode(doc);
    if (result.length() > 0) {
      node.setTextContent(result);
    }
  }
  return node;
}
