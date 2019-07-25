public static List<ConditionDTO> map(final List<? extends Condition> conditions){
  if (conditions == null) {
    return null;
  }
  final List<ConditionDTO> dtos=new ArrayList<ConditionDTO>(conditions.size());
  for (  final Condition action : conditions) {
    dtos.add(map(action));
  }
  return dtos;
}
