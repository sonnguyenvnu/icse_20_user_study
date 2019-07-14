@Override protected void doParse(Element element,ParserContext parserContext,BeanDefinitionBuilder builder){
  String cleanup=element.getAttribute("cleanupInterval");
  if (StringUtils.hasText(cleanup)) {
    try {
      builder.addPropertyValue("cleanupIntervalSeconds",Integer.parseInt(cleanup));
    }
 catch (    NumberFormatException e) {
      parserContext.getReaderContext().error("Invalid value " + cleanup + " for attribute cleanupIntervalSeconds.",element);
    }
  }
}
