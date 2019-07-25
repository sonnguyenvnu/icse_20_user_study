@Override public void dispose(){
  try {
    deviceLock.lock();
    if (device != null) {
      device.removeListener(this);
      device=null;
    }
  }
  finally {
    deviceLock.unlock();
  }
}
