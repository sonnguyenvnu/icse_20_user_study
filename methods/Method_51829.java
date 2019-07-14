@Override public boolean isFinal(){
  if (isAnnotationMember() || isInterfaceMember()) {
    return true;
  }
  return super.isFinal();
}
