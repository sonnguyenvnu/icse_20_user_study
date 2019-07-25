public static RuleBuilder create(RuleTemplate template,String uid,@Nullable String name,Configuration configuration,Visibility visibility){
  return create(uid).withActions(template.getActions()).withConditions(template.getConditions()).withTriggers(template.getTriggers()).withConfiguration(configuration).withConfigurationDescriptions(template.getConfigurationDescriptions()).withDescription(template.getDescription()).withName(name).withTags(template.getTags());
}
