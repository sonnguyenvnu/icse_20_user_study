private List<HttpMessageConverter<?>> geDefaultMessageConverters(){
  List<HttpMessageConverter<?>> result=new ArrayList<HttpMessageConverter<?>>();
  result.addAll(new RestTemplate().getMessageConverters());
  result.add(new JaxbOAuth2ExceptionMessageConverter());
  return result;
}
