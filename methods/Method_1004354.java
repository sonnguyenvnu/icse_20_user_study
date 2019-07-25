@Override public void flush(){
  checkpointManager.saveSyncActionCheckpointSnapshot();
}
