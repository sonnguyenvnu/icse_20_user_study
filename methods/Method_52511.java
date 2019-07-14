public JavaTypeDefinition getVarargComponentType(){
  if (!isVararg()) {
    throw new IllegalStateException("Method is not vararg: " + method.toString() + "!");
  }
  return argTypes.get(argTypes.size() - 1).getComponentType();
}
