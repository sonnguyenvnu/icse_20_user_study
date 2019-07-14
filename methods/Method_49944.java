private void renewMmsConnectivity(){
  mServiceHandler.sendMessageDelayed(mServiceHandler.obtainMessage(EVENT_CONTINUE_MMS_CONNECTIVITY),APN_EXTENSION_WAIT);
}
