@Override public WriteSet _XXXXX_(long entryId){
  return WriteSetImpl.create(ensembleSize,writeQuorumSize,entryId);
}