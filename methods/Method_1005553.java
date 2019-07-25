@Override public void start(){
  String action=MqttServiceConstants.PING_SENDER + comms.getClient().getClientId();
  Log.d(TAG,"Register alarmreceiver to MqttService" + action);
  service.registerReceiver(alarmReceiver,new IntentFilter(action));
  pendingIntent=PendingIntent.getBroadcast(service,0,new Intent(action),PendingIntent.FLAG_UPDATE_CURRENT);
  schedule(comms.getKeepAlive());
  hasStarted=true;
}
