public static List<ActionTypeDTO> map(final Collection<ActionType> types){
  if (types == null) {
    return null;
  }
  final List<ActionTypeDTO> dtos=new ArrayList<ActionTypeDTO>(types.size());
  for (  final ActionType type : types) {
    if (type instanceof CompositeActionType) {
      dtos.add(map((CompositeActionType)type));
    }
 else {
      dtos.add(map(type));
    }
  }
  return dtos;
}
