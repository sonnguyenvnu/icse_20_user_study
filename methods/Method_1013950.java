public static List<ConfigDescriptionParameter> map(List<ConfigDescriptionParameterDTO> parameters){
  if (parameters == null) {
    return null;
  }
  final List<ConfigDescriptionParameter> result=new ArrayList<>(parameters.size());
  for (  ConfigDescriptionParameterDTO parameter : parameters) {
    result.add(ConfigDescriptionParameterBuilder.create(parameter.name,parameter.type).withContext(parameter.context).withDefault(parameter.defaultValue).withDescription(parameter.description).withLabel(parameter.label).withRequired(parameter.required).withMinimum(parameter.min).withMaximum(parameter.max).withStepSize(parameter.stepsize).withPattern(parameter.pattern).withReadOnly(parameter.readOnly).withMultiple(parameter.multiple).withMultipleLimit(parameter.multipleLimit).withGroupName(parameter.groupName).withAdvanced(parameter.advanced).withVerify(parameter.verify).withLimitToOptions(parameter.limitToOptions).withUnit(parameter.unitLabel).withUnitLabel(parameter.unitLabel).withOptions(mapOptionsDTO(parameter.options)).withFilterCriteria(mapFilterCriteriaDTO(parameter.filterCriteria)).build());
  }
  return result;
}
