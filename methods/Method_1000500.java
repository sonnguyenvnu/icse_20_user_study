public void register(String name,Method method){
  if (!Modifier.isStatic(method.getModifiers())) {
    throw new ElException("must be static method");
  }
  runms.put(name,new StaticMethodRunMethod(method));
}
