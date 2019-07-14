/** 
 * Reads a numeric or string constant pool entry in this  {@link ClassReader}. <i>This method is intended for  {@link Attribute} sub classes, and is normally not needed by class generators oradapters.</i>
 * @param constantPoolEntryIndex the index of a CONSTANT_Integer, CONSTANT_Float, CONSTANT_Long,CONSTANT_Double, CONSTANT_Class, CONSTANT_String, CONSTANT_MethodType, CONSTANT_MethodHandle or CONSTANT_Dynamic entry in the class's constant pool.
 * @param charBuffer the buffer to be used to read strings. This buffer must be sufficientlylarge. It is not automatically resized.
 * @return the {@link Integer},  {@link Float},  {@link Long},  {@link Double},  {@link String}, {@link Type},  {@link Handle} or {@link ConstantDynamic} corresponding to the specifiedconstant pool entry.
 */
public Object readConst(final int constantPoolEntryIndex,final char[] charBuffer){
  int cpInfoOffset=cpInfoOffsets[constantPoolEntryIndex];
switch (classFileBuffer[cpInfoOffset - 1]) {
case Symbol.CONSTANT_INTEGER_TAG:
    return readInt(cpInfoOffset);
case Symbol.CONSTANT_FLOAT_TAG:
  return Float.intBitsToFloat(readInt(cpInfoOffset));
case Symbol.CONSTANT_LONG_TAG:
return readLong(cpInfoOffset);
case Symbol.CONSTANT_DOUBLE_TAG:
return Double.longBitsToDouble(readLong(cpInfoOffset));
case Symbol.CONSTANT_CLASS_TAG:
return Type.getObjectType(readUTF8(cpInfoOffset,charBuffer));
case Symbol.CONSTANT_STRING_TAG:
return readUTF8(cpInfoOffset,charBuffer);
case Symbol.CONSTANT_METHOD_TYPE_TAG:
return Type.getMethodType(readUTF8(cpInfoOffset,charBuffer));
case Symbol.CONSTANT_METHOD_HANDLE_TAG:
int referenceKind=readByte(cpInfoOffset);
int referenceCpInfoOffset=cpInfoOffsets[readUnsignedShort(cpInfoOffset + 1)];
int nameAndTypeCpInfoOffset=cpInfoOffsets[readUnsignedShort(referenceCpInfoOffset + 2)];
String owner=readClass(referenceCpInfoOffset,charBuffer);
String name=readUTF8(nameAndTypeCpInfoOffset,charBuffer);
String descriptor=readUTF8(nameAndTypeCpInfoOffset + 2,charBuffer);
boolean isInterface=classFileBuffer[referenceCpInfoOffset - 1] == Symbol.CONSTANT_INTERFACE_METHODREF_TAG;
return new Handle(referenceKind,owner,name,descriptor,isInterface);
case Symbol.CONSTANT_DYNAMIC_TAG:
return readConstantDynamic(constantPoolEntryIndex,charBuffer);
default :
throw new IllegalArgumentException();
}
}
