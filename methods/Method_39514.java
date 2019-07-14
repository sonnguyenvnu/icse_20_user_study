/** 
 * Returns the byte array form of the content of this attribute. The 6 header bytes (attribute_name_index and attribute_length) must <i>not</i> be added in the returned ByteVector.
 * @param classWriter the class to which this attribute must be added. This parameter can be usedto add the items that corresponds to this attribute to the constant pool of this class.
 * @param code the bytecode of the method corresponding to this code attribute, or {@literal null}if this attribute is not a code attribute. Corresponds to the 'code' field of the Code attribute.
 * @param codeLength the length of the bytecode of the method corresponding to this codeattribute, or 0 if this attribute is not a code attribute. Corresponds to the 'code_length' field of the Code attribute.
 * @param maxStack the maximum stack size of the method corresponding to this code attribute, or-1 if this attribute is not a code attribute.
 * @param maxLocals the maximum number of local variables of the method corresponding to this codeattribute, or -1 if this attribute is not a code attribute.
 * @return the byte array form of this attribute.
 */
protected ByteVector write(final ClassWriter classWriter,final byte[] code,final int codeLength,final int maxStack,final int maxLocals){
  return new ByteVector(content);
}
