public void disconnect(){
  if (mChatService != null) {
    isServiceRunning=false;
    mChatService.stop();
    if (mChatService.getState() == BluetoothState.STATE_NONE) {
      isServiceRunning=true;
      mChatService.start(BluetoothSPP.this.isAndroid);
    }
  }
}
