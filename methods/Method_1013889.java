public static TriggerDTO map(final Trigger trigger){
  final TriggerDTO triggerDto=new TriggerDTO();
  fillProperties(trigger,triggerDto);
  return triggerDto;
}
