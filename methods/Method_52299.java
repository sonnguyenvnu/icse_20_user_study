private boolean checkForViolation(ScopedNode node){
  MethodScope meth=node.getScope().getEnclosingScope(MethodScope.class);
  if (meth != null && "finalize".equals(meth.getName())) {
    return false;
  }
  if (meth != null && checked.contains(meth)) {
    return false;
  }
  if (meth != null) {
    checked.add(meth);
  }
  return true;
}
