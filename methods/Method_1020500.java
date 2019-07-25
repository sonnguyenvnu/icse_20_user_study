private static boolean overrides(MemberDescriptor member,MemberDescriptor potentiallyOverriddenMember){
  if (!member.isMethod() || !potentiallyOverriddenMember.isMethod()) {
    return false;
  }
  MethodDescriptor method=(MethodDescriptor)member.getDeclarationDescriptor();
  MethodDescriptor potentiallyOverriddenMethod=(MethodDescriptor)potentiallyOverriddenMember;
  return method.isOverride(potentiallyOverriddenMethod) || method.isOverride(potentiallyOverriddenMethod.getDeclarationDescriptor());
}
