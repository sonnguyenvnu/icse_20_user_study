protected String getText(Element element){
  StringBuilder accum=new StringBuilder();
  for (  Node node : element.childNodes()) {
    if (node instanceof TextNode) {
      TextNode textNode=(TextNode)node;
      accum.append(textNode.text());
    }
  }
  return accum.toString();
}
