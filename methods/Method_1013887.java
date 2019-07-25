public static RuleDTO map(final Rule rule){
  final RuleDTO ruleDto=new RuleDTO();
  fillProperties(rule,ruleDto);
  return ruleDto;
}
