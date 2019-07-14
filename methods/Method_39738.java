public static void castToReturnType(final MethodVisitor mv,final MethodInfo methodInfo){
  final String returnType;
  char returnOpcodeType=methodInfo.getReturnType().getOpcode();
switch (returnOpcodeType) {
case 'I':
    returnType=AsmUtil.SIGNATURE_JAVA_LANG_INTEGER;
  break;
case 'J':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_LONG;
break;
case 'S':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_SHORT;
break;
case 'B':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_BYTE;
break;
case 'Z':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_BOOLEAN;
break;
case 'F':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_FLOAT;
break;
case 'D':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_DOUBLE;
break;
case 'C':
returnType=AsmUtil.SIGNATURE_JAVA_LANG_CHARACTER;
break;
case '[':
returnType=methodInfo.getReturnType().getRawName();
break;
default :
String rtname=methodInfo.getReturnType().getRawName();
returnType=rtname.length() == 0 ? AsmUtil.typeToSignature(methodInfo.getReturnType().getType()) : AsmUtil.typedesc2ClassName(rtname);
break;
}
mv.visitTypeInsn(CHECKCAST,returnType);
}
