/** 
 * Reads a JVMS 'verification_type_info' structure and stores it at the given index in the given array.
 * @param verificationTypeInfoOffset the start offset of the 'verification_type_info' structure toread.
 * @param frame the array where the parsed type must be stored.
 * @param index the index in 'frame' where the parsed type must be stored.
 * @param charBuffer the buffer used to read strings in the constant pool.
 * @param labels the labels of the method currently being parsed, indexed by their offset. If theparsed type is an ITEM_Uninitialized, a new label for the corresponding NEW instruction is stored in this array if it does not already exist.
 * @return the end offset of the JVMS 'verification_type_info' structure.
 */
private int readVerificationTypeInfo(final int verificationTypeInfoOffset,final Object[] frame,final int index,final char[] charBuffer,final Label[] labels){
  int currentOffset=verificationTypeInfoOffset;
  int tag=classFileBuffer[currentOffset++] & 0xFF;
switch (tag) {
case Frame.ITEM_TOP:
    frame[index]=Opcodes.TOP;
  break;
case Frame.ITEM_INTEGER:
frame[index]=Opcodes.INTEGER;
break;
case Frame.ITEM_FLOAT:
frame[index]=Opcodes.FLOAT;
break;
case Frame.ITEM_DOUBLE:
frame[index]=Opcodes.DOUBLE;
break;
case Frame.ITEM_LONG:
frame[index]=Opcodes.LONG;
break;
case Frame.ITEM_NULL:
frame[index]=Opcodes.NULL;
break;
case Frame.ITEM_UNINITIALIZED_THIS:
frame[index]=Opcodes.UNINITIALIZED_THIS;
break;
case Frame.ITEM_OBJECT:
frame[index]=readClass(currentOffset,charBuffer);
currentOffset+=2;
break;
case Frame.ITEM_UNINITIALIZED:
frame[index]=createLabel(readUnsignedShort(currentOffset),labels);
currentOffset+=2;
break;
default :
throw new IllegalArgumentException();
}
return currentOffset;
}
