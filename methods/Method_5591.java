@SuppressWarnings("unchecked") @Override public boolean handleMessage(Message msg){
switch (msg.what) {
case MSG_UPDATE_OUTPUT:
    invokeUpdateOutputInternal((List<Cue>)msg.obj);
  return true;
default :
throw new IllegalStateException();
}
}
