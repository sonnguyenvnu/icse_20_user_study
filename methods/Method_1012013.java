private void ref(SNodeId id){
  if (!(id instanceof jetbrains.mps.smodel.SNodeId.StringBasedId)) {
    return;
  }
  myResult.put(new IdIndexEntry(((jetbrains.mps.smodel.SNodeId.StringBasedId)id).getId(),true),0);
}
