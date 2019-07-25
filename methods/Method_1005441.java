public synchronized void stop(){
  if (mConnectThread != null) {
    mConnectThread.cancel();
    mConnectThread=null;
  }
  if (mConnectedThread != null) {
    mConnectedThread.cancel();
    mConnectedThread=null;
  }
  if (mSecureAcceptThread != null) {
    mSecureAcceptThread.cancel();
    mSecureAcceptThread.kill();
    mSecureAcceptThread=null;
  }
  setState(BluetoothState.STATE_NONE);
}
