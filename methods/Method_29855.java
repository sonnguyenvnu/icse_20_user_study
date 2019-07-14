public void write(Broadcast broadcast,String text,Context context){
  add(new RebroadcastBroadcastWriter(broadcast,text,this),context);
}
