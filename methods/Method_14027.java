@Override public List<ItemUpdate> schedule(List<ItemUpdate> updates){
  List<ItemUpdate> result=new ArrayList<>();
  pointerFreeUpdates=new UpdateSequence();
  pointerFullUpdates=new UpdateSequence();
  allPointers=new HashSet<>();
  for (  ItemUpdate update : updates) {
    splitUpdate(update);
  }
  result.addAll(pointerFreeUpdates.getUpdates());
  Set<ItemIdValue> unseenPointers=new HashSet<>(allPointers);
  unseenPointers.removeAll(pointerFreeUpdates.getSubjects());
  result.addAll(unseenPointers.stream().map(e -> new ItemUpdateBuilder(e).build()).collect(Collectors.toList()));
  result.addAll(pointerFullUpdates.getUpdates());
  return result;
}
