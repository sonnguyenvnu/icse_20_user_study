/** 
 * This method is used to update the provided rules configuration.
 * @param uid specifies the rule for updating by UID
 * @param template specifies the rule template by UID
 * @param config gives the new configuration of the rule
 */
public void update(String uid,String template,Configuration config){
  Rule oldelement=rules.get(uid);
  Rule element=RuleBuilder.create(uid).withTemplateUID(template).withConfiguration(config).build();
  rules.put(uid,element);
  for (  ProviderChangeListener<Rule> listener : listeners) {
    listener.updated(this,oldelement,element);
  }
}
