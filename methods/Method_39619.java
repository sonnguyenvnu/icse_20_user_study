/** 
 * Puts the given public API frame element type in  {@link #stackMapTableEntries} , using the JVMSverification_type_info format used in StackMapTable attributes.
 * @param type a frame element type described using the same format as in {@link MethodVisitor#visitFrame}, i.e. either  {@link Opcodes#TOP},  {@link Opcodes#INTEGER},  {@link Opcodes#FLOAT},  {@link Opcodes#LONG},  {@link Opcodes#DOUBLE},  {@link Opcodes#NULL}, or {@link Opcodes#UNINITIALIZED_THIS}, or the internal name of a class, or a Label designating a NEW instruction (for uninitialized types).
 */
private void putFrameType(final Object type){
  if (type instanceof Integer) {
    stackMapTableEntries.putByte(((Integer)type).intValue());
  }
 else   if (type instanceof String) {
    stackMapTableEntries.putByte(Frame.ITEM_OBJECT).putShort(symbolTable.addConstantClass((String)type).index);
  }
 else {
    stackMapTableEntries.putByte(Frame.ITEM_UNINITIALIZED).putShort(((Label)type).bytecodeOffset);
  }
}
