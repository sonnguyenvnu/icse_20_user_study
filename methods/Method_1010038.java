@Override public final <T>T invoke(@NotNull SNode operand,@NotNull SMethod<T> method,Object... parameters){
  checkDescriptorIsInitialized();
  checkNotStatic(method);
  checkForConcept(operand.getConcept(),myConcept);
  if (method.isVirtual()) {
    return invokeVirtual(operand,method,parameters);
  }
 else {
    return invokeNonVirtual(operand,method,parameters);
  }
}
