/** 
 * @deprecated Use {@link #deepCopy()} to create verbatim copies of rules.
 */
@Deprecated public void deepCopyValuesTo(AbstractRule otherRule){
  otherRule.language=language;
  otherRule.minimumLanguageVersion=minimumLanguageVersion;
  otherRule.maximumLanguageVersion=maximumLanguageVersion;
  otherRule.deprecated=deprecated;
  otherRule.name=name;
  otherRule.since=since;
  otherRule.ruleClass=ruleClass;
  otherRule.ruleSetName=ruleSetName;
  otherRule.message=message;
  otherRule.description=description;
  otherRule.examples=copyExamples();
  otherRule.externalInfoUrl=externalInfoUrl;
  otherRule.priority=priority;
  otherRule.propertyDescriptors=new ArrayList<>(getPropertyDescriptors());
  otherRule.propertyValuesByDescriptor=copyPropertyValues();
  otherRule.usesDFA=usesDFA;
  otherRule.usesTypeResolution=usesTypeResolution;
  otherRule.usesMultifile=usesMultifile;
  otherRule.ruleChainVisits=copyRuleChainVisits();
}
