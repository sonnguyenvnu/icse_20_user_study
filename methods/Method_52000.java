@Override public boolean hasMatchingSig(JavaOperationQualifiedName qname,JavaOperationSigMask sigMask){
  ClassStats clazz=getClassStats(qname.getClassName(),false);
  return clazz != null && clazz.hasMatchingOpSig(qname.getOperation(),sigMask);
}
