/** 
 * Invoked on INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC, INVOKEINTERFACE or INVOKEDYNAMIC.
 */
@Override public void visitMethodInsn(final int opcode,String owner,String name,String desc,final boolean isInterface){
  if ((newInvokeReplacer != null) && (opcode == INVOKESPECIAL)) {
    String exOwner=owner;
    owner=newInvokeReplacer.getOwner();
    name=newInvokeReplacer.getMethodName();
    desc=changeReturnType(desc,'L' + exOwner + ';');
    super.visitMethodInsn(INVOKESTATIC,owner,name,desc,isInterface);
    newInvokeReplacer=null;
    return;
  }
  InvokeInfo invokeInfo=new InvokeInfo(owner,name,desc);
  if (methodInfo.getMethodName().equals(INIT)) {
    if ((!firstSuperCtorInitCalled) && (opcode == INVOKESPECIAL) && name.equals(INIT) && owner.equals(wd.nextSupername)) {
      firstSuperCtorInitCalled=true;
      owner=wd.superReference;
      super.visitMethodInsn(opcode,owner,name,desc,isInterface);
      return;
    }
  }
  if ((opcode == INVOKESPECIAL) && (owner.equals(wd.nextSupername) && (!name.equals(INIT)))) {
    throw new ProxettaException("Super call detected in class " + methodInfo.getClassname() + " method: " + methodInfo.getSignature() + "\nProxetta can't handle super calls due to VM limitations.");
  }
  InvokeReplacer ir=null;
  for (  InvokeAspect aspect : aspects) {
    ir=aspect.pointcut(invokeInfo);
    if (ir != null) {
      break;
    }
  }
  if (ir == null || ir.isNone()) {
    if (ProxettaAsmUtil.isCreateArgumentsArrayMethod(name,desc)) {
      ProxyTargetReplacement.createArgumentsArray(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isCreateArgumentsClassArrayMethod(name,desc)) {
      ProxyTargetReplacement.createArgumentsClassArray(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isArgumentsCountMethod(name,desc)) {
      ProxyTargetReplacement.argumentsCount(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isTargetMethodNameMethod(name,desc)) {
      ProxyTargetReplacement.targetMethodName(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isTargetMethodDescriptionMethod(name,desc)) {
      ProxyTargetReplacement.targetMethodDescription(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isTargetMethodSignatureMethod(name,desc)) {
      ProxyTargetReplacement.targetMethodSignature(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isReturnTypeMethod(name,desc)) {
      ProxyTargetReplacement.returnType(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (ProxettaAsmUtil.isTargetClassMethod(name,desc)) {
      ProxyTargetReplacement.targetClass(mv,methodInfo);
      wd.proxyApplied=true;
      return;
    }
    if (isArgumentTypeMethod(name,desc)) {
      int argIndex=this.getArgumentIndex();
      ProxyTargetReplacement.argumentType(mv,methodInfo,argIndex);
      wd.proxyApplied=true;
      return;
    }
    if (isArgumentMethod(name,desc)) {
      int argIndex=this.getArgumentIndex();
      ProxyTargetReplacement.argument(mv,methodInfo,argIndex);
      wd.proxyApplied=true;
      return;
    }
    if (isInfoMethod(name,desc)) {
      proxyInfoRequested=true;
      wd.proxyApplied=true;
      return;
    }
    if (isTargetMethodAnnotationMethod(name,desc)) {
      String[] args=getLastTwoStringArguments();
      mv.visitInsn(POP);
      mv.visitInsn(POP);
      ProxyTargetReplacement.targetMethodAnnotation(mv,methodInfo,args);
      wd.proxyApplied=true;
      return;
    }
    if (isTargetClassAnnotationMethod(name,desc)) {
      String[] args=getLastTwoStringArguments();
      mv.visitInsn(POP);
      mv.visitInsn(POP);
      ProxyTargetReplacement.targetClassAnnotation(mv,methodInfo.getClassInfo(),args);
      wd.proxyApplied=true;
      return;
    }
    super.visitMethodInsn(opcode,owner,name,desc,isInterface);
    return;
  }
  wd.proxyApplied=true;
  String exOwner=owner;
  owner=ir.getOwner();
  name=ir.getMethodName();
switch (opcode) {
case INVOKEINTERFACE:
    desc=prependArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_OBJECT);
  break;
case INVOKEVIRTUAL:
desc=prependArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_OBJECT);
break;
case INVOKESTATIC:
break;
default :
throw new ProxettaException("Unsupported opcode: " + opcode);
}
if (ir.isPassOwnerName()) {
desc=appendArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_STRING);
super.visitLdcInsn(exOwner);
}
if (ir.isPassMethodName()) {
desc=appendArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_STRING);
super.visitLdcInsn(methodInfo.getMethodName());
}
if (ir.isPassMethodSignature()) {
desc=appendArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_STRING);
super.visitLdcInsn(methodInfo.getSignature());
}
if (ir.isPassTargetClass()) {
desc=appendArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_CLASS);
super.mv.visitLdcInsn(Type.getType('L' + wd.superReference + ';'));
}
if (ir.isPassThis()) {
desc=appendArgument(desc,AsmUtil.L_SIGNATURE_JAVA_LANG_OBJECT);
super.mv.visitVarInsn(ALOAD,0);
}
super.visitMethodInsn(INVOKESTATIC,owner,name,desc,false);
}
