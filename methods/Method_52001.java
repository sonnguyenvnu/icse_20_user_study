@Override public boolean hasMatchingSig(JavaTypeQualifiedName qname,String fieldName,JavaFieldSigMask sigMask){
  ClassStats clazz=getClassStats(qname,false);
  return clazz != null && clazz.hasMatchingFieldSig(fieldName,sigMask);
}
