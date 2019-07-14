public void write(Broadcast broadcast,Context context){
  add(new DeleteBroadcastWriter(broadcast,this),context);
}
