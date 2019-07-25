public void finish(int id,Intent intent,String message){
  mBuilder.setContentTitle(message).setProgress(0,0,false).setOngoing(false);
  mNotificationManager.notify(id,mBuilder.build());
}
