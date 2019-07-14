private static List<String> getStateNames(CollectableItem.Type type,Context context){
  return Functional.map(getStates(type),state -> state.getString(type,context));
}
