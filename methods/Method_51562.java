/** 
 * Determine if the specified rule element will represent a Rule with the given name.
 * @param ruleElement The rule element.
 * @param ruleName    The Rule name.
 * @return {@code true} if the Rule would have the given name, {@code false} otherwise.
 */
private boolean isRuleName(Element ruleElement,String ruleName){
  if (ruleElement.hasAttribute("name")) {
    return ruleElement.getAttribute("name").equals(ruleName);
  }
 else   if (ruleElement.hasAttribute("ref")) {
    RuleSetReferenceId ruleSetReferenceId=RuleSetReferenceId.parse(ruleElement.getAttribute("ref")).get(0);
    return ruleSetReferenceId.getRuleName() != null && ruleSetReferenceId.getRuleName().equals(ruleName);
  }
 else {
    return false;
  }
}
