public static ActionDTO map(final Action action){
  final ActionDTO actionDto=new ActionDTO();
  fillProperties(action,actionDto);
  actionDto.inputs=action.getInputs();
  return actionDto;
}
