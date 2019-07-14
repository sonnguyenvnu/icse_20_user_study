/** 
 * Puts this symbol table's constant_pool array in the given ByteVector, preceded by the constant_pool_count value.
 * @param output where the JVMS ClassFile's constant_pool array must be put.
 */
void putConstantPool(final ByteVector output){
  output.putShort(constantPoolCount).putByteArray(constantPool.data,0,constantPool.length);
}
