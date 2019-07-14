/** 
 * Overrides the rule's properties with the values defined in the element.
 * @param rule          The rule
 * @param propertiesElt The {@literal <properties>} element
 */
private void setPropertyValues(Rule rule,Element propertiesElt){
  Map<String,String> overridden=getPropertyValuesFrom(propertiesElt);
  for (  Entry<String,String> e : overridden.entrySet()) {
    PropertyDescriptor<?> descriptor=rule.getPropertyDescriptor(e.getKey());
    if (descriptor == null) {
      throw new IllegalArgumentException("Cannot set non-existent property '" + e.getKey() + "' on Rule " + rule.getName());
    }
    setRulePropertyCapture(rule,descriptor,e.getValue());
  }
}
