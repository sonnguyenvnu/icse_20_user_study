@Bean @Primary @ConfigurationProperties(prefix="fastjson") public FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
  FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
  converter.setFeatures(SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullBooleanAsFalse);
  converter.setConverters(converters);
  return converter;
}
