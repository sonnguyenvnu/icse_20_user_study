/** 
 * Prepares return value.
 */
public static void prepareReturnValue(final MethodVisitor mv,final MethodInfo methodInfo,int varOffset){
  varOffset+=methodInfo.getAllArgumentsSize();
switch (methodInfo.getReturnType().getOpcode()) {
case 'V':
    mv.visitInsn(ACONST_NULL);
  break;
case 'B':
AsmUtil.valueOfByte(mv);
break;
case 'C':
AsmUtil.valueOfCharacter(mv);
break;
case 'S':
AsmUtil.valueOfShort(mv);
break;
case 'I':
AsmUtil.valueOfInteger(mv);
break;
case 'Z':
AsmUtil.valueOfBoolean(mv);
break;
case 'J':
AsmUtil.valueOfLong(mv);
break;
case 'F':
AsmUtil.valueOfFloat(mv);
break;
case 'D':
AsmUtil.valueOfDouble(mv);
break;
}
}
