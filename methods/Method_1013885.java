public static CompositeConditionTypeDTO map(final CompositeConditionType conditionType){
  final CompositeConditionTypeDTO conditionTypeDto=map(conditionType,new CompositeConditionTypeDTO());
  conditionTypeDto.children=ConditionDTOMapper.map(conditionType.getChildren());
  return conditionTypeDto;
}
