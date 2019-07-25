public void send(String data,boolean CRLF){
  if (mChatService.getState() == BluetoothState.STATE_CONNECTED) {
    if (CRLF)     data+="\r\n";
    mChatService.write(data.getBytes());
  }
}
