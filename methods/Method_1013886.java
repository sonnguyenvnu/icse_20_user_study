public static List<ConditionTypeDTO> map(final Collection<ConditionType> types){
  if (types == null) {
    return null;
  }
  final List<ConditionTypeDTO> dtos=new ArrayList<ConditionTypeDTO>(types.size());
  for (  final ConditionType type : types) {
    if (type instanceof CompositeConditionType) {
      dtos.add(map((CompositeConditionType)type));
    }
 else {
      dtos.add(map(type));
    }
  }
  return dtos;
}
