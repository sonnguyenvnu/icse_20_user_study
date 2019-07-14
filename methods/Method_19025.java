private static <T>List<T> removeBlacklistedItems(SectionContext c,List<T> data,HashSet blacklist,EventHandler<GetUniqueIdentifierEvent> getItemUniqueIdentifierHandler){
  ArrayList<T> builder=new ArrayList<>();
  final int size=data.size();
  for (int i=0; i < size; i++) {
    final T model=data.get(i);
    if (!blacklist.contains(HideableDataDiffSection.dispatchGetUniqueIdentifierEvent(getItemUniqueIdentifierHandler,model))) {
      builder.add(model);
    }
  }
  return builder;
}
