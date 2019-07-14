private boolean ruleReferenceTargetExists(String ruleReference){
  Matcher ruleRefMatcher=RULE_REFERENCE.matcher(ruleReference);
  if (ruleRefMatcher.matches()) {
    String language=ruleRefMatcher.group(1);
    String category=ruleRefMatcher.group(2);
    String rule=ruleRefMatcher.group(3);
    Path ruleDocPage=pagesDirectory.resolve("pmd/rules/" + language + "/" + category.toLowerCase(Locale.ROOT) + ".md");
    Set<String> rules=getRules(ruleDocPage);
    return rules.contains(rule);
  }
  return false;
}
