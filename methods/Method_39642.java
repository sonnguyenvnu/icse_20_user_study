/** 
 * Adds a new CONSTANT_String_info to the constant pool of this symbol table.
 * @param index the constant pool index of the new Symbol.
 * @param value a string.
 */
private void addConstantUtf8(final int index,final String value){
  add(new Entry(index,Symbol.CONSTANT_UTF8_TAG,value,hash(Symbol.CONSTANT_UTF8_TAG,value)));
}
