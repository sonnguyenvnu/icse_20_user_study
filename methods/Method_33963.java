@Override protected void doParse(Element element,ParserContext parserContext,BeanDefinitionBuilder builder){
  String cleanup=element.getAttribute("verifierLengthBytes");
  if (StringUtils.hasText(cleanup)) {
    try {
      builder.addPropertyValue("verifierLengthBytes",Integer.parseInt(cleanup));
    }
 catch (    NumberFormatException e) {
      parserContext.getReaderContext().error("Invalid value " + cleanup + " for attribute verifierLengthBytes.",element);
    }
  }
}
