@Override public boolean isShadowedBy(FrameworkField otherMember){
  return otherMember.getName().equals(getName());
}
