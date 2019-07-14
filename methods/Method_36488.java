@Override protected void parserSubElement(Element element,ParserContext parserContext,BeanDefinitionBuilder builder){
  Object target=null;
  if (element.hasAttribute(REF)) {
    target=new RuntimeBeanReference(element.getAttribute(REF));
  }
  NodeList nl=element.getChildNodes();
  final Set<String> contributions=new LinkedHashSet<String>();
  for (int i=0; i < nl.getLength(); i++) {
    Node node=nl.item(i);
    if (node instanceof Element) {
      Element subElement=(Element)node;
      if (OBJECT.equals(subElement.getLocalName())) {
        ParserUtils.parseCustomAttributes(subElement,parserContext,builder,new ParserUtils.AttributeCallback(){
          public void process(          Element parent,          Attr attribute,          BeanDefinitionBuilder builder,          ParserContext parserContext){
            String name=attribute.getLocalName();
            if (CLASS.equals(name)) {
              contributions.add(attribute.getValue());
            }
 else {
              builder.addPropertyValue(Conventions.attributeNameToPropertyName(name),attribute.getValue());
            }
          }
        }
);
      }
 else {
        if (element.hasAttribute(REF)) {
          SofaLogger.error("nested bean definition/reference cannot be used when attribute 'ref' is specified");
        }
        target=parserContext.getDelegate().parsePropertySubElement(subElement,builder.getBeanDefinition());
      }
    }
  }
  builder.addPropertyValue(CONTRIBUTION,contributions);
  if (target instanceof RuntimeBeanReference) {
    builder.addPropertyValue("targetBeanName",((RuntimeBeanReference)target).getBeanName());
  }
 else {
    builder.addPropertyValue("target",target);
  }
}
