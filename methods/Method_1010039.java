@Override public final <T>T invoke(@NotNull SAbstractConcept operand,@NotNull SMethod<T> method,Object... parameters){
  checkDescriptorIsInitialized();
  checkStatic(method);
  checkForConcept(operand,myConcept);
  if (method.isVirtual()) {
    return invokeVirtual(operand,method,parameters);
  }
 else {
    return invokeNonVirtual(operand,method,parameters);
  }
}
