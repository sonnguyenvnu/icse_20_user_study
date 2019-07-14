private ImageReceiver getFreeReceiver(){
  ImageReceiver receiver;
  if (unusedReceivers.isEmpty()) {
    receiver=new ImageReceiver(this);
  }
 else {
    receiver=unusedReceivers.get(0);
    unusedReceivers.remove(0);
  }
  imagesToDraw.add(receiver);
  receiver.setCurrentAccount(delegate.getCurrentAccount());
  return receiver;
}
