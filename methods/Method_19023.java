@OnUpdateState public static void onBlacklistUpdate(StateValue<HashSet> blacklistState,@Param Object modelObject,@Param EventHandler<GetUniqueIdentifierEvent> getUniqueIdentifierHandlerParam){
  HashSet<Object> newSet=new HashSet<>(blacklistState.get());
  newSet.add(HideableDataDiffSection.dispatchGetUniqueIdentifierEvent(getUniqueIdentifierHandlerParam,modelObject));
  blacklistState.set(newSet);
}
