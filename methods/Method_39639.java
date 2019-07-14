/** 
 * Adds a new CONSTANT_Integer_info or CONSTANT_Float_info to the constant pool of this symbol table.
 * @param index the constant pool index of the new Symbol.
 * @param tag one of {@link Symbol#CONSTANT_INTEGER_TAG} or {@link Symbol#CONSTANT_FLOAT_TAG}.
 * @param value an int or float.
 */
private void addConstantIntegerOrFloat(final int index,final int tag,final int value){
  add(new Entry(index,tag,value,hash(tag,value)));
}
