void dispatchOnExitRangeIfNeeded(WorkingRangeStatusHandler stateHandler){
  if (mWorkingRangeContainer == null) {
    return;
  }
  mWorkingRangeContainer.dispatchOnExitedRangeIfNeeded(stateHandler);
}
