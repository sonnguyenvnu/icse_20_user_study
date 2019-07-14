public static void loadMethodArgumentAsObject(final MethodVisitor mv,final MethodInfo methodInfo,final int index){
  int offset=methodInfo.getArgumentOffset(index);
  int type=methodInfo.getArgument(index).getOpcode();
switch (type) {
case 'V':
    break;
case 'B':
  mv.visitVarInsn(ILOAD,offset);
AsmUtil.valueOfByte(mv);
break;
case 'C':
mv.visitVarInsn(ILOAD,offset);
AsmUtil.valueOfCharacter(mv);
break;
case 'S':
mv.visitVarInsn(ILOAD,offset);
AsmUtil.valueOfShort(mv);
break;
case 'I':
mv.visitVarInsn(ILOAD,offset);
AsmUtil.valueOfInteger(mv);
break;
case 'Z':
mv.visitVarInsn(ILOAD,offset);
AsmUtil.valueOfBoolean(mv);
break;
case 'J':
mv.visitVarInsn(LLOAD,offset);
AsmUtil.valueOfLong(mv);
break;
case 'F':
mv.visitVarInsn(FLOAD,offset);
AsmUtil.valueOfFloat(mv);
break;
case 'D':
mv.visitVarInsn(DLOAD,offset);
AsmUtil.valueOfDouble(mv);
break;
default :
mv.visitVarInsn(ALOAD,offset);
}
}
