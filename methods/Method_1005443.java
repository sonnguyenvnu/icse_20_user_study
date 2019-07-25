public void connect(Intent data){
  String address=data.getExtras().getString(BluetoothState.EXTRA_DEVICE_ADDRESS);
  BluetoothDevice device=mBluetoothAdapter.getRemoteDevice(address);
  mChatService.connect(device);
}
