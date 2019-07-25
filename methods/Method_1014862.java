public void emit(String event,Bundle data){
  Intent intent=new Intent(Utils.EVENT_INTENT);
  intent.putExtra("event",event);
  if (data != null)   intent.putExtra("data",data);
  LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
}
