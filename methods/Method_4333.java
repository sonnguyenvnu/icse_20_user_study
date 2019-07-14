/** 
 * Notifies the decode loop if there exists a queued input buffer and an available output buffer to decode into. <p> Should only be called whilst synchronized on the lock object.
 */
private void maybeNotifyDecodeLoop(){
  if (canDecodeBuffer()) {
    lock.notify();
  }
}
