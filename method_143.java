/** 
 * Add a content type rule to this set.
 * @param rule the rule to add
 */
public void _XXXXX_(ContentTypeRule rule){
  rules.add(rule);
  if (defaultContentTypeProperty == null) {
    defaultContentTypeProperty=rule.getExpectedContentTypeProperty();
  }
}