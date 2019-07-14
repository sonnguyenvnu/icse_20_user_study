public List<EntryList> execute(final BackendTransaction tx){
  int total=0;
  final List<EntryList> result=new ArrayList<>(Math.min(getLimit(),queries.size()));
  for (  KeySliceQuery ksq : queries) {
    EntryList next=tx.indexQuery(ksq.updateLimit(getLimit() - total));
    result.add(next);
    total+=next.size();
    if (total >= getLimit())     break;
  }
  return result;
}
