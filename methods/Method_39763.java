/** 
 * Starts creation of first chain delegate.
 */
protected void createFirstChainDelegate_Start(){
  int access=msign.getAccessFlags();
  if (!wd.allowFinalMethods) {
    if ((access & AsmUtil.ACC_FINAL) != 0) {
      throw new ProxettaException("Unable to create proxy for final method: " + msign + ". Remove final modifier or change the pointcut definition.");
    }
  }
  tmd=new TargetMethodData(msign,aspectList);
  access&=~ACC_NATIVE;
  access&=~ACC_ABSTRACT;
  methodVisitor=wd.dest.visitMethod(access,tmd.msign.getMethodName(),tmd.msign.getDescription(),tmd.msign.getAsmMethodSignature(),null);
}
