public static void storeValue(final MethodVisitor mv,final int offset,final int type){
switch (type) {
case 'V':
    break;
case 'B':
  AsmUtil.byteValue(mv);
mv.visitVarInsn(ISTORE,offset);
break;
case 'C':
AsmUtil.charValue(mv);
mv.visitVarInsn(ISTORE,offset);
break;
case 'S':
AsmUtil.shortValue(mv);
mv.visitVarInsn(ISTORE,offset);
break;
case 'I':
AsmUtil.intValue(mv);
mv.visitVarInsn(ISTORE,offset);
break;
case 'Z':
AsmUtil.booleanValue(mv);
mv.visitVarInsn(ISTORE,offset);
break;
case 'J':
AsmUtil.longValue(mv);
mv.visitVarInsn(LSTORE,offset);
break;
case 'F':
AsmUtil.floatValue(mv);
mv.visitVarInsn(FSTORE,offset);
break;
case 'D':
AsmUtil.doubleValue(mv);
mv.visitVarInsn(DSTORE,offset);
break;
default :
mv.visitVarInsn(ASTORE,offset);
}
}
