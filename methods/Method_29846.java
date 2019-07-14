private void prependBroadcast(Broadcast broadcast){
  List<Broadcast> broadcastList=get();
  broadcastList.add(0,broadcast);
  getListener().onBroadcastInserted(getRequestCode(),0,broadcast);
}
