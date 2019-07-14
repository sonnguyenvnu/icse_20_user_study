public static void loadClass(final MethodVisitor mv,final int type,final String typeName){
switch (type) {
case 'V':
    mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_VOID,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
  break;
case 'B':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_BYTE,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'C':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_CHARACTER,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'S':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_SHORT,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'I':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_INTEGER,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'Z':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_BOOLEAN,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'J':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_LONG,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'F':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_FLOAT,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
case 'D':
mv.visitFieldInsn(GETSTATIC,AsmUtil.SIGNATURE_JAVA_LANG_DOUBLE,"TYPE",AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
break;
default :
mv.visitLdcInsn(Type.getType(typeName));
break;
}
}
