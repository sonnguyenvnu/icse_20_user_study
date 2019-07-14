@Override public boolean isProtected(){
  if (isAnnotationMember() || isInterfaceMember()) {
    return false;
  }
  return super.isProtected();
}
