private ObjectFormatter initFormatter(Class<? extends ObjectFormatter> formatterClazz,String[] params){
  try {
    ObjectFormatter objectFormatter=formatterClazz.newInstance();
    objectFormatter.initParam(params);
    return objectFormatter;
  }
 catch (  InstantiationException e) {
    throw new RuntimeException(e);
  }
catch (  IllegalAccessException e) {
    throw new RuntimeException(e);
  }
}
