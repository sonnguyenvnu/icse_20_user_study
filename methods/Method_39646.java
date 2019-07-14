/** 
 * Adds a CONSTANT_Dynamic or a CONSTANT_InvokeDynamic_info to the constant pool of this symbol table. Does nothing if the constant pool already contains a similar item.
 * @param tag one of {@link Symbol#CONSTANT_DYNAMIC_TAG} or {@link Symbol#CONSTANT_INVOKE_DYNAMIC_TAG}.
 * @param name a method name.
 * @param descriptor a field descriptor for CONSTANT_DYNAMIC_TAG) or a method descriptor forCONSTANT_INVOKE_DYNAMIC_TAG.
 * @param bootstrapMethodIndex the index of a bootstrap method in the BootstrapMethods attribute.
 * @return a new or already existing Symbol with the given value.
 */
private Symbol addConstantDynamicOrInvokeDynamicReference(final int tag,final String name,final String descriptor,final int bootstrapMethodIndex){
  int hashCode=hash(tag,name,descriptor,bootstrapMethodIndex);
  Entry entry=get(hashCode);
  while (entry != null) {
    if (entry.tag == tag && entry.hashCode == hashCode && entry.data == bootstrapMethodIndex && entry.name.equals(name) && entry.value.equals(descriptor)) {
      return entry;
    }
    entry=entry.next;
  }
  constantPool.put122(tag,bootstrapMethodIndex,addConstantNameAndType(name,descriptor));
  return put(new Entry(constantPoolCount++,tag,null,name,descriptor,bootstrapMethodIndex,hashCode));
}
