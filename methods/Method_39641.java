/** 
 * Adds a new CONSTANT_NameAndType_info to the constant pool of this symbol table.
 * @param index the constant pool index of the new Symbol.
 * @param name a field or method name.
 * @param descriptor a field or method descriptor.
 */
private void addConstantNameAndType(final int index,final String name,final String descriptor){
  final int tag=Symbol.CONSTANT_NAME_AND_TYPE_TAG;
  add(new Entry(index,tag,name,descriptor,hash(tag,name,descriptor)));
}
