public <T>T getByType(Class<T> type){
  return spring.getBean(type);
}
