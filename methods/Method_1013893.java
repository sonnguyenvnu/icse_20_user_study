public static List<TriggerTypeDTO> map(final Collection<TriggerType> types){
  if (types == null) {
    return null;
  }
  final List<TriggerTypeDTO> dtos=new ArrayList<TriggerTypeDTO>(types.size());
  for (  final TriggerType type : types) {
    if (type instanceof CompositeTriggerType) {
      dtos.add(map((CompositeTriggerType)type));
    }
 else {
      dtos.add(map(type));
    }
  }
  return dtos;
}
