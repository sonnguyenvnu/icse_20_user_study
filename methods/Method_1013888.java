public static Rule map(final RuleDTO ruleDto){
  return RuleBuilder.create(ruleDto.uid).withActions(ActionDTOMapper.mapDto(ruleDto.actions)).withConditions(ConditionDTOMapper.mapDto(ruleDto.conditions)).withTriggers(TriggerDTOMapper.mapDto(ruleDto.triggers)).withConfiguration(new Configuration(ruleDto.configuration)).withConfigurationDescriptions(ConfigDescriptionDTOMapper.map(ruleDto.configDescriptions)).withTemplateUID(ruleDto.templateUID).withVisibility(ruleDto.visibility).withTags(ruleDto.tags).withName(ruleDto.name).withDescription(ruleDto.description).build();
}
