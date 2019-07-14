@Override public EntryList loadRelations(SliceQuery query,Retriever<SliceQuery,EntryList> lookup){
  return (isNew()) ? EntryList.EMPTY_LIST : lookup.get(query);
}
