@Override protected Object convertFromInternal(Message<?> message,Class<?> targetClass,Object conversionHint){
  Object payload=message.getPayload();
  Object obj=null;
  if (payload instanceof byte[]) {
    obj=JSON.parseObject((byte[])payload,fastJsonConfig.getCharset(),targetClass,fastJsonConfig.getParserConfig(),fastJsonConfig.getParseProcess(),JSON.DEFAULT_PARSER_FEATURE,fastJsonConfig.getFeatures());
  }
 else   if (payload instanceof String) {
    obj=JSON.parseObject((String)payload,targetClass,fastJsonConfig.getParserConfig(),fastJsonConfig.getParseProcess(),JSON.DEFAULT_PARSER_FEATURE,fastJsonConfig.getFeatures());
  }
  return obj;
}
