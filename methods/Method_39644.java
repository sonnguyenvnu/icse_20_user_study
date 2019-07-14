/** 
 * Adds a CONSTANT_Dynamic_info to the constant pool of this symbol table. Also adds the related bootstrap method to the BootstrapMethods of this symbol table. Does nothing if the constant pool already contains a similar item.
 * @param name a method name.
 * @param descriptor a field descriptor.
 * @param bootstrapMethodHandle a bootstrap method handle.
 * @param bootstrapMethodArguments the bootstrap method arguments.
 * @return a new or already existing Symbol with the given value.
 */
Symbol addConstantDynamic(final String name,final String descriptor,final Handle bootstrapMethodHandle,final Object... bootstrapMethodArguments){
  Symbol bootstrapMethod=addBootstrapMethod(bootstrapMethodHandle,bootstrapMethodArguments);
  return addConstantDynamicOrInvokeDynamicReference(Symbol.CONSTANT_DYNAMIC_TAG,name,descriptor,bootstrapMethod.index);
}
