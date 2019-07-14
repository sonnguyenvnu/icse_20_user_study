@Override public boolean covers(JavaOperationSignature sig){
  return super.covers(sig) && roleMask.contains(sig.role) && (coverAbstract || !sig.isAbstract);
}
