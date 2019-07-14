@Override public EntryList loadRelations(SliceQuery query,Retriever<SliceQuery,EntryList> lookup){
  return super.loadRelations(query,accessCheck.retrieveSliceQuery());
}
