@Bean @Primary @ConfigurationProperties(prefix="fastjson") public FastJsonGenericHttpMessageConverter fastJsonGenericHttpMessageConverter(EntityFactory entityFactory){
  JSON.DEFAULT_PARSER_FEATURE|=Feature.DisableFieldSmartMatch.getMask();
  FastJsonGenericHttpMessageConverter converter=new FastJsonGenericHttpMessageConverter();
  converter.setFeatures(SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullBooleanAsFalse);
  converter.setConverters(converters);
  ParserConfig.global=new ParserConfig(){
    @Override public ObjectDeserializer getDeserializer(    Type type){
      ObjectDeserializer derializer=getDeserializers().get(type);
      if (derializer != null) {
        return derializer;
      }
      if (type instanceof Class) {
        Class classType=((Class)type);
        if (classType.isEnum()) {
          return super.getDeserializer(type);
        }
        checkAutoType(type.getTypeName(),((Class)type));
        if (Modifier.isAbstract(classType.getModifiers()) || Modifier.isInterface(classType.getModifiers())) {
          Class realType;
          if (entityFactory != null && (realType=entityFactory.getInstanceType(classType)) != null) {
            return new JavaBeanDeserializer(this,realType,type);
          }
        }
 else {
          return new JavaBeanDeserializer(this,classType);
        }
      }
      return super.getDeserializer(type);
    }
  }
;
  ParserConfig.global.addAccept("org.hswebframework.web.entity.");
  ParserConfig.global.addDeny("org.hswebframework.ezorm.core.param.SqlTerm");
  return converter;
}
