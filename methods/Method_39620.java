/** 
 * Returns whether the attributes of this method can be copied from the attributes of the given method (assuming there is no method visitor between the given ClassReader and this MethodWriter). This method should only be called just after this MethodWriter has been created, and before any content is visited. It returns true if the attributes corresponding to the constructor arguments (at most a Signature, an Exception, a Deprecated and a Synthetic attribute) are the same as the corresponding attributes in the given method.
 * @param source the source ClassReader from which the attributes of this method might be copied.
 * @param hasSyntheticAttribute whether the method_info JVMS structure from which the attributesof this method might be copied contains a Synthetic attribute.
 * @param hasDeprecatedAttribute whether the method_info JVMS structure from which the attributesof this method might be copied contains a Deprecated attribute.
 * @param descriptorIndex the descriptor_index field of the method_info JVMS structure from whichthe attributes of this method might be copied.
 * @param signatureIndex the constant pool index contained in the Signature attribute of themethod_info JVMS structure from which the attributes of this method might be copied, or 0.
 * @param exceptionsOffset the offset in 'source.b' of the Exceptions attribute of the method_infoJVMS structure from which the attributes of this method might be copied, or 0.
 * @return whether the attributes of this method can be copied from the attributes of themethod_info JVMS structure in 'source.b', between 'methodInfoOffset' and 'methodInfoOffset' + 'methodInfoLength'.
 */
boolean canCopyMethodAttributes(final ClassReader source,final boolean hasSyntheticAttribute,final boolean hasDeprecatedAttribute,final int descriptorIndex,final int signatureIndex,final int exceptionsOffset){
  if (source != symbolTable.getSource() || descriptorIndex != this.descriptorIndex || signatureIndex != this.signatureIndex || hasDeprecatedAttribute != ((accessFlags & Opcodes.ACC_DEPRECATED) != 0)) {
    return false;
  }
  boolean needSyntheticAttribute=symbolTable.getMajorVersion() < Opcodes.V1_5 && (accessFlags & Opcodes.ACC_SYNTHETIC) != 0;
  if (hasSyntheticAttribute != needSyntheticAttribute) {
    return false;
  }
  if (exceptionsOffset == 0) {
    if (numberOfExceptions != 0) {
      return false;
    }
  }
 else   if (source.readUnsignedShort(exceptionsOffset) == numberOfExceptions) {
    int currentExceptionOffset=exceptionsOffset + 2;
    for (int i=0; i < numberOfExceptions; ++i) {
      if (source.readUnsignedShort(currentExceptionOffset) != exceptionIndexTable[i]) {
        return false;
      }
      currentExceptionOffset+=2;
    }
  }
  return true;
}
