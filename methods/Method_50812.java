private String getNestedRuleSetFiles(){
  final StringBuilder sb=new StringBuilder();
  for (Iterator<RuleSetWrapper> it=nestedRules.iterator(); it.hasNext(); ) {
    RuleSetWrapper rs=it.next();
    sb.append(rs.getFile());
    if (it.hasNext()) {
      sb.append(',');
    }
  }
  return sb.toString();
}
