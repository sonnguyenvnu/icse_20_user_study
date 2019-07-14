public boolean write(Broadcast broadcast,boolean like,Context context){
  if (shouldWrite(broadcast,context)) {
    write(broadcast.id,like,context);
    return true;
  }
 else {
    return false;
  }
}
