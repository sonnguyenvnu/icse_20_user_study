/** 
 * Release the test slot. Free the resource on the slot itself and the registry. If also invokes the  {@link org.openqa.grid.internal.listeners.TestSessionListener#afterSession(TestSession)} ifapplicable.
 * @param testSlot The slot to release
 */
private void _release(TestSlot testSlot,SessionTerminationReason reason){
  if (!testSlot.startReleaseProcess()) {
    return;
  }
  if (!testSlot.performAfterSessionEvent()) {
    return;
  }
  final String internalKey=testSlot.getInternalKey();
  try {
    lock.lock();
    testSlot.finishReleaseProcess();
    release(internalKey,reason);
  }
  finally {
    lock.unlock();
  }
}
