private static boolean isRuleType(FrameworkMember<?> member){
  return isMethodRule(member) || isTestRule(member);
}
