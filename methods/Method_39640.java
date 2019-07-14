/** 
 * Adds a new CONSTANT_Long_info or CONSTANT_Double_info to the constant pool of this symbol table.
 * @param index the constant pool index of the new Symbol.
 * @param tag one of {@link Symbol#CONSTANT_LONG_TAG} or {@link Symbol#CONSTANT_DOUBLE_TAG}.
 * @param value a long or double.
 */
private void addConstantLongOrDouble(final int index,final int tag,final long value){
  add(new Entry(index,tag,value,hash(tag,value)));
}
