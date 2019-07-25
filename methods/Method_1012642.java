Predicate parse(String rule){
  rule=rule.trim();
  if (rule.length() == 0) {
    return null;
  }
  for (int i=0; i < rule.length(); i++) {
    if (IP_FILTER_RULE_CHAR.indexOf(rule.charAt(i)) == -1) {
      return new HostNamePredicate(rule);
    }
  }
  String[] tags=rule.split("\\.");
  return new IpPredicate(tags);
}
