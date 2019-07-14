public void onBind(ResourceWriterService service){
  mService=service;
  movePendingWritersToRunning();
}
