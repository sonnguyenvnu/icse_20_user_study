/** 
 * Returns the total size in bytes of all the attributes that correspond to the given field, method or class access flags and signature. This size includes the 6 header bytes (attribute_name_index and attribute_length) per attribute. Also adds the attribute type names to the constant pool.
 * @param symbolTable where the constants used in the attributes must be stored.
 * @param accessFlags some field, method or class access flags.
 * @param signatureIndex the constant pool index of a field, method of class signature.
 * @return the size of all the attributes in bytes. This size includes the size of the attributeheaders.
 */
static int computeAttributesSize(final SymbolTable symbolTable,final int accessFlags,final int signatureIndex){
  int size=0;
  if ((accessFlags & Opcodes.ACC_SYNTHETIC) != 0 && symbolTable.getMajorVersion() < Opcodes.V1_5) {
    symbolTable.addConstantUtf8(Constants.SYNTHETIC);
    size+=6;
  }
  if (signatureIndex != 0) {
    symbolTable.addConstantUtf8(Constants.SIGNATURE);
    size+=8;
  }
  if ((accessFlags & Opcodes.ACC_DEPRECATED) != 0) {
    symbolTable.addConstantUtf8(Constants.DEPRECATED);
    size+=6;
  }
  return size;
}
