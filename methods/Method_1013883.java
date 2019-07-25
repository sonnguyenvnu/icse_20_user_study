private static <T extends ActionTypeDTO>T map(final ActionType actionType,final T actionTypeDto){
  fillProperties(actionType,actionTypeDto);
  actionTypeDto.inputs=actionType.getInputs();
  actionTypeDto.outputs=actionType.getOutputs();
  return actionTypeDto;
}
