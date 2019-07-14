@Override public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
  converters.add(new BufferedImageHttpMessageConverter());
}
