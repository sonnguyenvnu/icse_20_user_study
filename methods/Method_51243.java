private void setSuppression(Rule rule,T node){
  String regex=rule.getProperty(Rule.VIOLATION_SUPPRESS_REGEX_DESCRIPTOR);
  if (regex != null && description != null) {
    if (Pattern.matches(regex,description)) {
      suppressed=true;
    }
  }
  if (!suppressed) {
    String xpath=rule.getProperty(Rule.VIOLATION_SUPPRESS_XPATH_DESCRIPTOR);
    if (xpath != null) {
      suppressed=node.hasDescendantMatchingXPath(xpath);
    }
  }
}
