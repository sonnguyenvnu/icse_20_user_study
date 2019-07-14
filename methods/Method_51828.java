@Override public boolean isStatic(){
  if (isAnnotationMember() || isInterfaceMember()) {
    return true;
  }
  return super.isStatic();
}
