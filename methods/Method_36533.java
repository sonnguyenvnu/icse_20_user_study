@Override public boolean resolve(){
  if (componentStatus != ComponentStatus.REGISTERED) {
    return false;
  }
  componentStatus=ComponentStatus.RESOLVED;
  return true;
}
