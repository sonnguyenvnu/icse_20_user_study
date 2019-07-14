/** 
 * Returns the "initialized" abstract type corresponding to the given abstract type.
 * @param symbolTable the type table to use to lookup and store type {@link Symbol}.
 * @param abstractType an abstract type.
 * @return the REFERENCE_KIND abstract type corresponding to abstractType if it isUNINITIALIZED_THIS or an UNINITIALIZED_KIND abstract type for one of the types on which a constructor is invoked in the basic block. Otherwise returns abstractType.
 */
private int getInitializedType(final SymbolTable symbolTable,final int abstractType){
  if (abstractType == UNINITIALIZED_THIS || (abstractType & (DIM_MASK | KIND_MASK)) == UNINITIALIZED_KIND) {
    for (int i=0; i < initializationCount; ++i) {
      int initializedType=initializations[i];
      int dim=initializedType & DIM_MASK;
      int kind=initializedType & KIND_MASK;
      int value=initializedType & VALUE_MASK;
      if (kind == LOCAL_KIND) {
        initializedType=dim + inputLocals[value];
      }
 else       if (kind == STACK_KIND) {
        initializedType=dim + inputStack[inputStack.length - value];
      }
      if (abstractType == initializedType) {
        if (abstractType == UNINITIALIZED_THIS) {
          return REFERENCE_KIND | symbolTable.addType(symbolTable.getClassName());
        }
 else {
          return REFERENCE_KIND | symbolTable.addType(symbolTable.getType(abstractType & VALUE_MASK).value);
        }
      }
    }
  }
  return abstractType;
}
