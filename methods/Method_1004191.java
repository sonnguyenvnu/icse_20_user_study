@Override public void unlock(){
  checkOnEachPublicOperation.checkOnEachLockOperation();
switch (s.localLockState) {
case UNLOCKED:
case READ_LOCKED:
    return;
case UPDATE_LOCKED:
  entry.closeDelayedUpdateChecksum();
if (s.decrementUpdate() == 0 && s.writeZero()) {
  s.segmentHeader.downgradeUpdateToReadLock(s.segmentHeaderAddress);
}
break;
case WRITE_LOCKED:
entry.closeDelayedUpdateChecksum();
if (s.decrementWrite() == 0) {
if (!s.updateZero()) {
s.segmentHeader.downgradeWriteToUpdateLock(s.segmentHeaderAddress);
}
 else {
s.segmentHeader.downgradeWriteToReadLock(s.segmentHeaderAddress);
}
}
}
s.incrementRead();
s.setLocalLockState(READ_LOCKED);
}
