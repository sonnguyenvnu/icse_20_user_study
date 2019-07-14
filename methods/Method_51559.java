/** 
 * Check whether the given ruleName is contained in the given ruleset.
 * @param ruleSetReferenceId the ruleset to check
 * @param ruleName           the rule name to search for
 * @return {@code true} if the ruleName exists
 */
private boolean containsRule(RuleSetReferenceId ruleSetReferenceId,String ruleName){
  boolean found=false;
  try (InputStream ruleSet=ruleSetReferenceId.getInputStream(resourceLoader)){
    DocumentBuilder builder=createDocumentBuilder();
    Document document=builder.parse(ruleSet);
    Element ruleSetElement=document.getDocumentElement();
    NodeList rules=ruleSetElement.getElementsByTagName("rule");
    for (int i=0; i < rules.getLength(); i++) {
      Element rule=(Element)rules.item(i);
      if (rule.hasAttribute("name") && rule.getAttribute("name").equals(ruleName)) {
        found=true;
        break;
      }
    }
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
  return found;
}
