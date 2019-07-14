/** 
 * Adds a new CONSTANT_Dynamic_info or CONSTANT_InvokeDynamic_info to the constant pool of this symbol table.
 * @param tag one of {@link Symbol#CONSTANT_DYNAMIC_TAG} or {@link Symbol#CONSTANT_INVOKE_DYNAMIC_TAG}.
 * @param index the constant pool index of the new Symbol.
 * @param name a method name.
 * @param descriptor a field descriptor for CONSTANT_DYNAMIC_TAG or a method descriptor forCONSTANT_INVOKE_DYNAMIC_TAG.
 * @param bootstrapMethodIndex the index of a bootstrap method in the BootstrapMethods attribute.
 */
private void addConstantDynamicOrInvokeDynamicReference(final int tag,final int index,final String name,final String descriptor,final int bootstrapMethodIndex){
  int hashCode=hash(tag,name,descriptor,bootstrapMethodIndex);
  add(new Entry(index,tag,null,name,descriptor,bootstrapMethodIndex,hashCode));
}
