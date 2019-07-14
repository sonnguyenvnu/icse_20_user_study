private KCVMutation convert(KCVEntryMutation mutation){
  assert !mutation.isEmpty();
  if (!mutation.hasDeletions())   return new KCVMutation(mutation.getAdditions(),KeyColumnValueStore.NO_DELETIONS);
 else   return new KCVMutation(mutation.getAdditions(),Lists.newArrayList(Iterables.transform(mutation.getDeletions(),KCVEntryMutation.ENTRY2COLUMN_FCT)));
}
