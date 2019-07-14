protected JavaBeanDeserializer getSeeAlso(ParserConfig config,JavaBeanInfo beanInfo,String typeName){
  if (beanInfo.jsonType == null) {
    return null;
  }
  for (  Class<?> seeAlsoClass : beanInfo.jsonType.seeAlso()) {
    ObjectDeserializer seeAlsoDeser=config.getDeserializer(seeAlsoClass);
    if (seeAlsoDeser instanceof JavaBeanDeserializer) {
      JavaBeanDeserializer seeAlsoJavaBeanDeser=(JavaBeanDeserializer)seeAlsoDeser;
      JavaBeanInfo subBeanInfo=seeAlsoJavaBeanDeser.beanInfo;
      if (subBeanInfo.typeName.equals(typeName)) {
        return seeAlsoJavaBeanDeser;
      }
      JavaBeanDeserializer subSeeAlso=getSeeAlso(config,subBeanInfo,typeName);
      if (subSeeAlso != null) {
        return subSeeAlso;
      }
    }
  }
  return null;
}
