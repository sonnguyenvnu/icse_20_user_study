@Override public void process(){
  if (listener != null) {
    listener.commandAction(command,displayable);
  }
 else   if (itemlistener != null) {
    itemlistener.commandAction(command,item);
  }
}
