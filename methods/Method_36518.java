public static int generateBindingHashCode(Reference reference){
  Collection<Binding> bindings=reference.getBindings();
  int result=1;
  for (  Binding binding : bindings) {
    result=result * 31 + binding.getBindingHashCode();
  }
  ClassLoader cl=reference.getInterfaceType().getClassLoader();
  if (cl != null) {
    result+=reference.getInterfaceType().getClassLoader().hashCode();
  }
  return result;
}
