public static List<ActionDTO> map(final Collection<? extends Action> actions){
  if (actions == null) {
    return null;
  }
  final List<ActionDTO> dtos=new ArrayList<ActionDTO>(actions.size());
  for (  final Action action : actions) {
    dtos.add(map(action));
  }
  return dtos;
}
