/** 
 * Modifies the map in way that it matches the entries of the given childIDs.
 * @param future A future that completes as soon as all children have their added-action performed.
 * @param childIDs The list of IDs that should be in the map. Everything else currently in the map will be removed.
 * @param addedAction A function where the newly added child is given as an argument to perform any actions on it.A future is expected as a return value that completes as soon as said action is performed.
 * @param supplyNewChild A function where the ID of a new child is given and the created child isexpected as a result.
 * @param removedCallback A callback, that is called whenever a child got removed by the{@link #apply(CompletableFuture,String[],Function)} method.
 * @return Complete successfully if all "addedAction" complete successfully, otherwise complete exceptionally.
 */
public CompletableFuture<@Nullable Void> apply(String[] childIDs,final Function<TYPE,CompletableFuture<Void>> addedAction,final Function<String,TYPE> supplyNewChild,final Consumer<TYPE> removedCallback){
  Set<String> arrayValues=Stream.of(childIDs).collect(Collectors.toSet());
  final Map<String,TYPE> newSubnodes=arrayValues.stream().filter(entry -> !this.map.containsKey(entry)).collect(Collectors.toMap(k -> k,k -> supplyNewChild.apply(k)));
  this.map.putAll(newSubnodes);
  this.map.entrySet().removeIf(entry -> {
    if (!arrayValues.contains(entry.getKey())) {
      removedCallback.accept(entry.getValue());
      return true;
    }
    return false;
  }
);
  return CompletableFuture.allOf(newSubnodes.values().stream().map(v -> addedAction.apply(v)).toArray(CompletableFuture[]::new));
}
