public static CompositeTriggerTypeDTO map(final CompositeTriggerType triggerType){
  final CompositeTriggerTypeDTO triggerTypeDto=map(triggerType,new CompositeTriggerTypeDTO());
  triggerTypeDto.children=TriggerDTOMapper.map(triggerType.getChildren());
  return triggerTypeDto;
}
