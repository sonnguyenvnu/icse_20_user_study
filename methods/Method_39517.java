/** 
 * Puts all the attributes of the attribute list that begins with this attribute, in the given byte vector. This includes the 6 header bytes (attribute_name_index and attribute_length) per attribute.
 * @param symbolTable where the constants used in the attributes must be stored.
 * @param output where the attributes must be written.
 */
final void putAttributes(final SymbolTable symbolTable,final ByteVector output){
  final byte[] code=null;
  final int codeLength=0;
  final int maxStack=-1;
  final int maxLocals=-1;
  putAttributes(symbolTable,code,codeLength,maxStack,maxLocals,output);
}
