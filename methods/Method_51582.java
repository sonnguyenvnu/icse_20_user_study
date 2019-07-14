private Element createRuleElement(Rule rule){
  if (rule instanceof RuleReference) {
    RuleReference ruleReference=(RuleReference)rule;
    RuleSetReference ruleSetReference=ruleReference.getRuleSetReference();
    if (ruleSetReference.isAllRules()) {
      if (!ruleSetFileNames.contains(ruleSetReference.getRuleSetFileName())) {
        ruleSetFileNames.add(ruleSetReference.getRuleSetFileName());
        return createRuleSetReferenceElement(ruleSetReference);
      }
 else {
        return null;
      }
    }
 else {
      Language language=ruleReference.getOverriddenLanguage();
      LanguageVersion minimumLanguageVersion=ruleReference.getOverriddenMinimumLanguageVersion();
      LanguageVersion maximumLanguageVersion=ruleReference.getOverriddenMaximumLanguageVersion();
      Boolean deprecated=ruleReference.isOverriddenDeprecated();
      String name=ruleReference.getOverriddenName();
      String ref=ruleReference.getRuleSetReference().getRuleSetFileName() + '/' + ruleReference.getRule().getName();
      String message=ruleReference.getOverriddenMessage();
      String externalInfoUrl=ruleReference.getOverriddenExternalInfoUrl();
      String description=ruleReference.getOverriddenDescription();
      RulePriority priority=ruleReference.getOverriddenPriority();
      List<PropertyDescriptor<?>> propertyDescriptors=ruleReference.getOverriddenPropertyDescriptors();
      Map<PropertyDescriptor<?>,Object> propertiesByPropertyDescriptor=ruleReference.getOverriddenPropertiesByPropertyDescriptor();
      List<String> examples=ruleReference.getOverriddenExamples();
      return createSingleRuleElement(language,minimumLanguageVersion,maximumLanguageVersion,deprecated,name,null,ref,message,externalInfoUrl,null,null,null,null,description,priority,propertyDescriptors,propertiesByPropertyDescriptor,examples);
    }
  }
 else {
    return createSingleRuleElement(rule instanceof ImmutableLanguage ? null : rule.getLanguage(),rule.getMinimumLanguageVersion(),rule.getMaximumLanguageVersion(),rule.isDeprecated(),rule.getName(),rule.getSince(),null,rule.getMessage(),rule.getExternalInfoUrl(),rule.getRuleClass(),rule.isDfa(),rule.isTypeResolution(),rule.isMultifile(),rule.getDescription(),rule.getPriority(),rule.getPropertyDescriptors(),rule.getPropertiesByPropertyDescriptor(),rule.getExamples());
  }
}
