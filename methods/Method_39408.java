@Override public Object lookup(final String name){
  HttpServletRequest servletRequest=getCurrentHttpRequest();
  Map<String,TransientBeanData> map=getRequestMap(servletRequest);
  if (map == null) {
    return null;
  }
  BeanData beanData=map.get(name).get();
  if (beanData == null) {
    return null;
  }
  return beanData.bean();
}
