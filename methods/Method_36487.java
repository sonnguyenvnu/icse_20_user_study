@Override protected void parserSubElement(Element element,ParserContext parserContext,BeanDefinitionBuilder builder){
  NodeList nl=element.getChildNodes();
  for (int i=0; i < nl.getLength(); i++) {
    Node node=nl.item(i);
    if (node instanceof Element) {
      Element subElement=(Element)node;
      if (CONTENT.equals(subElement.getLocalName())) {
        builder.addPropertyValue(CONTENT,subElement);
      }
    }
  }
}
