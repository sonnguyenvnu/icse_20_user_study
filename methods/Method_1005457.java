@SuppressWarnings("unchecked") public List<Class<RuntimeException>> build(BeanContainer beanContainer){
  List<Class<RuntimeException>> answer=new ArrayList<>();
  for (  String current : exceptions) {
    Class runtimeException=MappingUtils.loadClass(current,beanContainer);
    if (!RuntimeException.class.isAssignableFrom(runtimeException)) {
      MappingUtils.throwMappingException("allowed-exceptions must extend java.lang.RuntimeException. Found: " + runtimeException.getName());
    }
    answer.add(runtimeException);
  }
  return answer;
}
