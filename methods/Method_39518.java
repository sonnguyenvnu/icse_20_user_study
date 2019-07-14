/** 
 * Puts all the attributes that correspond to the given field, method or class access flags and signature, in the given byte vector. This includes the 6 header bytes (attribute_name_index and attribute_length) per attribute.
 * @param symbolTable where the constants used in the attributes must be stored.
 * @param accessFlags some field, method or class access flags.
 * @param signatureIndex the constant pool index of a field, method of class signature.
 * @param output where the attributes must be written.
 */
static void putAttributes(final SymbolTable symbolTable,final int accessFlags,final int signatureIndex,final ByteVector output){
  if ((accessFlags & Opcodes.ACC_SYNTHETIC) != 0 && symbolTable.getMajorVersion() < Opcodes.V1_5) {
    output.putShort(symbolTable.addConstantUtf8(Constants.SYNTHETIC)).putInt(0);
  }
  if (signatureIndex != 0) {
    output.putShort(symbolTable.addConstantUtf8(Constants.SIGNATURE)).putInt(2).putShort(signatureIndex);
  }
  if ((accessFlags & Opcodes.ACC_DEPRECATED) != 0) {
    output.putShort(symbolTable.addConstantUtf8(Constants.DEPRECATED)).putInt(0);
  }
}
