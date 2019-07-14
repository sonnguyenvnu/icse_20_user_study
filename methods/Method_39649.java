/** 
 * Adds a new CONSTANT_Class_info, CONSTANT_String_info, CONSTANT_MethodType_info, CONSTANT_Module_info or CONSTANT_Package_info to the constant pool of this symbol table.
 * @param index the constant pool index of the new Symbol.
 * @param tag one of {@link Symbol#CONSTANT_CLASS_TAG},  {@link Symbol#CONSTANT_STRING_TAG},  {@link Symbol#CONSTANT_METHOD_TYPE_TAG},  {@link Symbol#CONSTANT_MODULE_TAG} or {@link Symbol#CONSTANT_PACKAGE_TAG}.
 * @param value an internal class name, an arbitrary string, a method descriptor, a module or apackage name, depending on tag.
 */
private void addConstantUtf8Reference(final int index,final int tag,final String value){
  add(new Entry(index,tag,value,hash(tag,value)));
}
