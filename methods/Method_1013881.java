public static ActionType map(CompositeActionTypeDTO actionTypeDto){
  if (actionTypeDto.children == null || actionTypeDto.children.isEmpty()) {
    return new ActionType(actionTypeDto.uid,ConfigDescriptionDTOMapper.map(actionTypeDto.configDescriptions),actionTypeDto.label,actionTypeDto.description,actionTypeDto.tags,actionTypeDto.visibility,actionTypeDto.inputs,actionTypeDto.outputs);
  }
 else {
    return new CompositeActionType(actionTypeDto.uid,ConfigDescriptionDTOMapper.map(actionTypeDto.configDescriptions),actionTypeDto.label,actionTypeDto.description,actionTypeDto.tags,actionTypeDto.visibility,actionTypeDto.inputs,actionTypeDto.outputs,ActionDTOMapper.mapDto(actionTypeDto.children));
  }
}
