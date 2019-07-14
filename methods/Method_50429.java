private <T>T getByName(Class<T> type){
  T bean;
  String className=type.getSimpleName();
  bean=cfgContext.getBean(firstLowercase(firstDelete(className)),type);
  return bean;
}
