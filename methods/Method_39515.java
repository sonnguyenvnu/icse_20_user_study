/** 
 * Returns the total size in bytes of all the attributes in the attribute list that begins with this attribute. This size includes the 6 header bytes (attribute_name_index and attribute_length) per attribute. Also adds the attribute type names to the constant pool.
 * @param symbolTable where the constants used in the attributes must be stored.
 * @return the size of all the attributes in this attribute list. This size includes the size ofthe attribute headers.
 */
final int computeAttributesSize(final SymbolTable symbolTable){
  final byte[] code=null;
  final int codeLength=0;
  final int maxStack=-1;
  final int maxLocals=-1;
  return computeAttributesSize(symbolTable,code,codeLength,maxStack,maxLocals);
}
