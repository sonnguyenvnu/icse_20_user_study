/** 
 * Sets the input frame from the given method description. This method is used to initialize the first frame of a method, which is implicit (i.e. not stored explicitly in the StackMapTable attribute).
 * @param symbolTable the type table to use to lookup and store type {@link Symbol}.
 * @param access the method's access flags.
 * @param descriptor the method descriptor.
 * @param maxLocals the maximum number of local variables of the method.
 */
final void setInputFrameFromDescriptor(final SymbolTable symbolTable,final int access,final String descriptor,final int maxLocals){
  inputLocals=new int[maxLocals];
  inputStack=new int[0];
  int inputLocalIndex=0;
  if ((access & Opcodes.ACC_STATIC) == 0) {
    if ((access & Constants.ACC_CONSTRUCTOR) == 0) {
      inputLocals[inputLocalIndex++]=REFERENCE_KIND | symbolTable.addType(symbolTable.getClassName());
    }
 else {
      inputLocals[inputLocalIndex++]=UNINITIALIZED_THIS;
    }
  }
  for (  Type argumentType : Type.getArgumentTypes(descriptor)) {
    int abstractType=getAbstractTypeFromDescriptor(symbolTable,argumentType.getDescriptor(),0);
    inputLocals[inputLocalIndex++]=abstractType;
    if (abstractType == LONG || abstractType == DOUBLE) {
      inputLocals[inputLocalIndex++]=TOP;
    }
  }
  while (inputLocalIndex < maxLocals) {
    inputLocals[inputLocalIndex++]=TOP;
  }
}
