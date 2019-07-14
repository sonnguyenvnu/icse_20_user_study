/** 
 * Creates static initialization block that simply calls all advice static init methods in correct order.
 */
protected void makeStaticInitBlock(){
  if (wd.adviceClinits != null) {
    MethodVisitor mv=wd.dest.visitMethod(AsmUtil.ACC_STATIC,CLINIT,DESC_VOID,null,null);
    mv.visitCode();
    for (    String name : wd.adviceClinits) {
      mv.visitMethodInsn(INVOKESTATIC,wd.thisReference,name,DESC_VOID,false);
    }
    mv.visitInsn(RETURN);
    mv.visitMaxs(0,0);
    mv.visitEnd();
  }
}
