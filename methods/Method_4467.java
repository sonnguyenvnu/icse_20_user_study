private void stopInternal(boolean forceResetRenderers,boolean resetPositionAndState,boolean acknowledgeStop){
  resetInternal(forceResetRenderers || !foregroundMode,true,resetPositionAndState,resetPositionAndState);
  playbackInfoUpdate.incrementPendingOperationAcks(pendingPrepareCount + (acknowledgeStop ? 1 : 0));
  pendingPrepareCount=0;
  loadControl.onStopped();
  setState(Player.STATE_IDLE);
}
