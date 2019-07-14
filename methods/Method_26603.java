Iterable<UTypeVar> typeVariables(Context context){
  ImmutableList<UTypeVar> ruleTypeVars=context.get(RefasterRule.RULE_TYPE_VARS);
  return Iterables.concat((ruleTypeVars == null) ? ImmutableList.<UTypeVar>of() : ruleTypeVars,templateTypeVariables());
}
