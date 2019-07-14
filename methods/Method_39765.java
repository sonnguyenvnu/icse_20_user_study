/** 
 * Created empty default constructor.
 */
protected void createEmptyCtor(){
  final MethodVisitor mv=wd.dest.visitMethod(AsmUtil.ACC_PUBLIC,INIT,"()V",null,null);
  mv.visitCode();
  mv.visitVarInsn(Opcodes.ALOAD,0);
  mv.visitMethodInsn(Opcodes.INVOKESPECIAL,AsmUtil.SIGNATURE_JAVA_LANG_OBJECT,INIT,"()V",false);
  mv.visitInsn(Opcodes.RETURN);
  mv.visitMaxs(1,1);
  mv.visitEnd();
}
