@ReactMethod public void start(ReadableMap options,Callback callback){
  Log.d(LOG_TAG,"start");
  if (getBluetoothAdapter() == null) {
    Log.d(LOG_TAG,"No bluetooth support");
    callback.invoke("No bluetooth support");
    return;
  }
  forceLegacy=false;
  if (options.hasKey("forceLegacy")) {
    forceLegacy=options.getBoolean("forceLegacy");
  }
  if (Build.VERSION.SDK_INT >= LOLLIPOP && !forceLegacy) {
    scanManager=new LollipopScanManager(reactContext,this);
  }
 else {
    scanManager=new LegacyScanManager(reactContext,this);
  }
  IntentFilter filter=new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
  filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
  context.registerReceiver(mReceiver,filter);
  callback.invoke();
  Log.d(LOG_TAG,"BleManager initialized");
}
