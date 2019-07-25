protected static synchronized FastClass create(Class<?> klass){
  Map<String,FastMethod> constructors=new HashMap<String,FastMethod>();
  Map<String,FastMethod> methods=new HashMap<String,FastMethod>();
  Map<String,FastMethod> fields=new HashMap<String,FastMethod>();
  for (  Constructor<?> constructor : klass.getConstructors()) {
    String key=Type.getConstructorDescriptor(constructor);
    FastMethod fm=FastMethodFactory.make(constructor);
    constructors.put(key,fm);
  }
  for (  Method method : klass.getMethods()) {
    if (method.getName().contains("$"))     continue;
    String key=method.getName() + "$" + Type.getMethodDescriptor(method);
    FastMethod fm=FastMethodFactory.make(method);
    methods.put(key,fm);
  }
  return new FastClassImpl(klass,constructors,methods,fields);
}
