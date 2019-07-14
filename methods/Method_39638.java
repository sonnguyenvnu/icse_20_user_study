/** 
 * Adds a new CONSTANT_Fieldref_info, CONSTANT_Methodref_info or CONSTANT_InterfaceMethodref_info to the constant pool of this symbol table.
 * @param index the constant pool index of the new Symbol.
 * @param tag one of {@link Symbol#CONSTANT_FIELDREF_TAG},  {@link Symbol#CONSTANT_METHODREF_TAG}or  {@link Symbol#CONSTANT_INTERFACE_METHODREF_TAG}.
 * @param owner the internal name of a class.
 * @param name a field or method name.
 * @param descriptor a field or method descriptor.
 */
private void addConstantMemberReference(final int index,final int tag,final String owner,final String name,final String descriptor){
  add(new Entry(index,tag,owner,name,descriptor,0,hash(tag,owner,name,descriptor)));
}
