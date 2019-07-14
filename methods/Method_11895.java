private static boolean isTestRule(FrameworkMember<?> member){
  return TestRule.class.isAssignableFrom(member.getType());
}
