/** 
 * Initializes a reference to the local Bluetooth adapter.
 * @return Return true if the initialization is successful.
 */
public boolean initialize(){
  if (mBluetoothManager == null) {
    mBluetoothManager=(BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
    if (mBluetoothManager == null) {
      Log.e(TAG,"Unable to initialize BluetoothManager.");
      return false;
    }
  }
  mBluetoothAdapter=mBluetoothManager.getAdapter();
  if (mBluetoothAdapter == null) {
    Log.e(TAG,"Unable to obtain a BluetoothAdapter.");
    return false;
  }
  return true;
}
