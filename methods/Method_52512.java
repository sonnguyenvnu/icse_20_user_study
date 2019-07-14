public JavaTypeDefinition getArgTypeIncludingVararg(int index){
  if (index < argTypes.size() - 1) {
    return argTypes.get(index);
  }
 else {
    return getVarargComponentType();
  }
}
