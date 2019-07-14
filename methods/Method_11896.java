private static boolean isMethodRule(FrameworkMember<?> member){
  return MethodRule.class.isAssignableFrom(member.getType());
}
