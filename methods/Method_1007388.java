private void override(String thisClassname,Method meth,String prefix,int index,String desc,ClassFile cf,ConstPool cp,List<Find2MethodsArgs> forwarders) throws CannotCompileException {
  Class<?> declClass=meth.getDeclaringClass();
  String delegatorName=prefix + index + meth.getName();
  if (Modifier.isAbstract(meth.getModifiers()))   delegatorName=null;
 else {
    MethodInfo delegator=makeDelegator(meth,desc,cp,declClass,delegatorName);
    delegator.setAccessFlags(delegator.getAccessFlags() & ~AccessFlag.BRIDGE);
    cf.addMethod(delegator);
  }
  MethodInfo forwarder=makeForwarder(thisClassname,meth,desc,cp,declClass,delegatorName,index,forwarders);
  cf.addMethod(forwarder);
}
