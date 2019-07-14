protected MethodCollector visitMethod(int access,String name,String desc){
  if (collector != null) {
    return null;
  }
  if (!name.equals(methodName)) {
    return null;
  }
  Type[] argTypes=Type.getArgumentTypes(desc);
  int longOrDoubleQuantity=0;
  for (  Type t : argTypes) {
    String className=t.getClassName();
    if (className.equals("long") || className.equals("double")) {
      longOrDoubleQuantity++;
    }
  }
  if (argTypes.length != this.parameterTypes.length) {
    return null;
  }
  for (int i=0; i < argTypes.length; i++) {
    if (!correctTypeName(argTypes[i],this.parameterTypes[i].getName())) {
      return null;
    }
  }
  return collector=new MethodCollector(Modifier.isStatic(access) ? 0 : 1,argTypes.length + longOrDoubleQuantity);
}
