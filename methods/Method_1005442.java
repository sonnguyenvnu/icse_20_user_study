public void write(byte[] out){
  ConnectedThread r;
synchronized (this) {
    if (mState != BluetoothState.STATE_CONNECTED)     return;
    r=mConnectedThread;
  }
  r.write(out);
}
