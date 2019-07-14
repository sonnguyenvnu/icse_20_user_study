/** 
 * Adds a merged type in the type table of this symbol table. Does nothing if the type table already contains a similar type.
 * @param typeTableIndex1 a {@link Symbol#TYPE_TAG} type, specified by its index in the typetable.
 * @param typeTableIndex2 another {@link Symbol#TYPE_TAG} type, specified by its index in the typetable.
 * @return the index of a new or already existing {@link Symbol#TYPE_TAG} type Symbol,corresponding to the common super class of the given types.
 */
int addMergedType(final int typeTableIndex1,final int typeTableIndex2){
  long data=typeTableIndex1 < typeTableIndex2 ? typeTableIndex1 | (((long)typeTableIndex2) << 32) : typeTableIndex2 | (((long)typeTableIndex1) << 32);
  int hashCode=hash(Symbol.MERGED_TYPE_TAG,typeTableIndex1 + typeTableIndex2);
  Entry entry=get(hashCode);
  while (entry != null) {
    if (entry.tag == Symbol.MERGED_TYPE_TAG && entry.hashCode == hashCode && entry.data == data) {
      return entry.info;
    }
    entry=entry.next;
  }
  String type1=typeTable[typeTableIndex1].value;
  String type2=typeTable[typeTableIndex2].value;
  int commonSuperTypeIndex=addType(classWriter.getCommonSuperClass(type1,type2));
  put(new Entry(typeCount,Symbol.MERGED_TYPE_TAG,data,hashCode)).info=commonSuperTypeIndex;
  return commonSuperTypeIndex;
}
