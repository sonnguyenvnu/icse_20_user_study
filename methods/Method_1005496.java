/** 
 * Builds object representation of mappings based on content of Xml document
 * @return mapping container
 */
public MappingFileData read(Document document){
  DozerBuilder builder=new DozerBuilder(beanContainer,destBeanCreator,propertyDescriptorFactory);
  Element theRoot=document.getDocumentElement();
  NodeList nl=theRoot.getChildNodes();
  for (int i=0; i < nl.getLength(); i++) {
    Node node=nl.item(i);
    if (node instanceof Element) {
      Element ele=(Element)node;
      log.debug("name: {}",ele.getNodeName());
      if (CONFIGURATION_ELEMENT.equals(ele.getNodeName())) {
        parseConfiguration(ele,builder);
      }
 else       if (MAPPING_ELEMENT.equals(ele.getNodeName())) {
        parseMapping(ele,builder);
      }
    }
  }
  return builder.build();
}
