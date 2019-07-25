public static CompositeActionTypeDTO map(final CompositeActionType actionType){
  final CompositeActionTypeDTO actionTypeDto=map(actionType,new CompositeActionTypeDTO());
  actionTypeDto.children=ActionDTOMapper.map(actionType.getChildren());
  return actionTypeDto;
}
