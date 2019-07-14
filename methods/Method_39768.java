/** 
 * Creates simple method wrapper without proxy.
 */
protected void createSimpleMethodWrapper(final MethodSignatureVisitor msign){
  int access=msign.getAccessFlags();
  access&=~ACC_ABSTRACT;
  access&=~ACC_NATIVE;
  MethodVisitor mv=wd.dest.visitMethod(access,msign.getMethodName(),msign.getDescription(),msign.getAsmMethodSignature(),msign.getExceptions());
  mv.visitCode();
  mv.visitVarInsn(ALOAD,0);
  mv.visitFieldInsn(GETFIELD,wd.thisReference,wd.wrapperRef,wd.wrapperType);
  loadVirtualMethodArguments(mv,msign);
  if (wd.wrapInterface) {
    mv.visitMethodInsn(INVOKEINTERFACE,wd.wrapperType.substring(1,wd.wrapperType.length() - 1),msign.getMethodName(),msign.getDescription(),true);
  }
 else {
    mv.visitMethodInsn(INVOKEVIRTUAL,wd.wrapperType.substring(1,wd.wrapperType.length() - 1),msign.getMethodName(),msign.getDescription(),false);
  }
  ProxettaAsmUtil.prepareReturnValue(mv,msign,0);
  visitReturn(mv,msign,true);
  mv.visitMaxs(0,0);
  mv.visitEnd();
}
