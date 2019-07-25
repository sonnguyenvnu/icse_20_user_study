@Override public List<Class<RuntimeException>> build(BeanContainer beanContainer){
  List<String> resolved=new ArrayList<>();
  if (exceptions != null) {
    for (    String current : exceptions) {
      resolved.add(elEngine.resolve(current));
    }
  }
  setExceptions(resolved);
  return super.build(beanContainer);
}
