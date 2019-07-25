public static List<TriggerDTO> map(final Collection<? extends Trigger> triggers){
  if (triggers == null) {
    return null;
  }
  final List<TriggerDTO> dtos=new ArrayList<TriggerDTO>(triggers.size());
  for (  final Trigger trigger : triggers) {
    dtos.add(map(trigger));
  }
  return dtos;
}
