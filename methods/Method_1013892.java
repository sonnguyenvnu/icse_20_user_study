public static TriggerType map(final CompositeTriggerTypeDTO triggerTypeDto){
  if (triggerTypeDto.children == null || triggerTypeDto.children.isEmpty()) {
    return new TriggerType(triggerTypeDto.uid,ConfigDescriptionDTOMapper.map(triggerTypeDto.configDescriptions),triggerTypeDto.label,triggerTypeDto.description,triggerTypeDto.tags,triggerTypeDto.visibility,triggerTypeDto.outputs);
  }
 else {
    return new CompositeTriggerType(triggerTypeDto.uid,ConfigDescriptionDTOMapper.map(triggerTypeDto.configDescriptions),triggerTypeDto.label,triggerTypeDto.description,triggerTypeDto.tags,triggerTypeDto.visibility,triggerTypeDto.outputs,TriggerDTOMapper.mapDto(triggerTypeDto.children));
  }
}
