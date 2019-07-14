protected void parseAttribute(Element element,ParserContext parserContext,BeanDefinitionBuilder builder){
  ParserUtils.parseCustomAttributes(element,parserContext,builder,new ParserUtils.AttributeCallback(){
    public void process(    Element parent,    Attr attribute,    BeanDefinitionBuilder builder,    ParserContext parserContext){
      String name=attribute.getLocalName();
      if (!REF.equals(name)) {
        builder.addPropertyValue(Conventions.attributeNameToPropertyName(name),attribute.getValue());
      }
    }
  }
);
}
