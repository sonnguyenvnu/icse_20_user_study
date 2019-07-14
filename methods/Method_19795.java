public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
  converters.add(0,new PropertiesHttpMessageConverter());
}
