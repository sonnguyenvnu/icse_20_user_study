@Override public void visitTypeInsn(final int opcode,final String type){
  if (opcode == NEW) {
    InvokeInfo invokeInfo=new InvokeInfo(type,INIT,StringPool.EMPTY);
    for (    InvokeAspect aspect : aspects) {
      InvokeReplacer ir=aspect.pointcut(invokeInfo);
      if (ir != null && !ir.isNone()) {
        newInvokeReplacer=ir;
        return;
      }
    }
  }
  super.visitTypeInsn(opcode,type);
}
