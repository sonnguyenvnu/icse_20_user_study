@OnUpdateState static void updateItems(StateValue<ImmutableList<Pair<String,Integer>>> items,@Param ImmutableList<Pair<String,Integer>> updatedItems){
  items.set(updatedItems);
}
