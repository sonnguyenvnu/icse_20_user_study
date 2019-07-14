/** 
 * Adds a CONSTANT_MethodType_info to the constant pool of this symbol table. Does nothing if the constant pool already contains a similar item.
 * @param methodDescriptor a method descriptor.
 * @return a new or already existing Symbol with the given value.
 */
Symbol addConstantMethodType(final String methodDescriptor){
  return addConstantUtf8Reference(Symbol.CONSTANT_METHOD_TYPE_TAG,methodDescriptor);
}
