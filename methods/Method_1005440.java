public synchronized void start(boolean isAndroid){
  if (mConnectThread != null) {
    mConnectThread.cancel();
    mConnectThread=null;
  }
  if (mConnectedThread != null) {
    mConnectedThread.cancel();
    mConnectedThread=null;
  }
  setState(BluetoothState.STATE_LISTEN);
  if (mSecureAcceptThread == null) {
    mSecureAcceptThread=new AcceptThread(isAndroid);
    mSecureAcceptThread.start();
    BluetoothService.this.isAndroid=isAndroid;
  }
}
