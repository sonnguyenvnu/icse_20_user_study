protected void createEmptyCtorThatCreatesTarget(){
  final MethodVisitor mv=wd.dest.visitMethod(AsmUtil.ACC_PUBLIC,"<init>","()V",null,null);
  mv.visitCode();
  mv.visitVarInsn(ALOAD,0);
  mv.visitMethodInsn(Opcodes.INVOKESPECIAL,AsmUtil.SIGNATURE_JAVA_LANG_OBJECT,INIT,"()V",false);
  mv.visitVarInsn(ALOAD,0);
  mv.visitTypeInsn(Opcodes.NEW,wd.superReference);
  mv.visitInsn(Opcodes.DUP);
  mv.visitMethodInsn(Opcodes.INVOKESPECIAL,wd.superReference,INIT,"()V",false);
  mv.visitFieldInsn(Opcodes.PUTFIELD,wd.thisReference,wd.wrapperRef,wd.wrapperType);
  mv.visitInsn(Opcodes.RETURN);
  mv.visitMaxs(3,1);
  mv.visitEnd();
}
