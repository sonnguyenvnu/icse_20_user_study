@Override public void register(final BeanDefinition beanDefinition,final Object bean){
  HttpServletRequest servletRequest=getCurrentHttpRequest();
  Map<String,TransientBeanData> map=getRequestMap(servletRequest);
  if (map == null) {
    map=createRequestMap(servletRequest);
  }
  map.put(beanDefinition.name(),new TransientBeanData(new BeanData(pc,beanDefinition,bean)));
}
