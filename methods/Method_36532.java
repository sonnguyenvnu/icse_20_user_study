@Override public boolean isResolved(){
  return componentStatus == ComponentStatus.ACTIVATED || componentStatus == ComponentStatus.RESOLVED;
}
