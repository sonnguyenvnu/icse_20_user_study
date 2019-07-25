public FastMethod fast(Method method){
  return methods.get(method.getName() + "$" + Type.getMethodDescriptor(method));
}
