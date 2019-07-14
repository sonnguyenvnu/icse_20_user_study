private static List<StateUpdate> createStateUpdatesList(@Nullable List<StateUpdate> copyFrom){
  List<StateUpdate> list=new ArrayList<>(copyFrom == null ? INITIAL_STATE_UPDATE_LIST_CAPACITY : copyFrom.size());
  if (copyFrom != null) {
    list.addAll(copyFrom);
  }
  return list;
}
