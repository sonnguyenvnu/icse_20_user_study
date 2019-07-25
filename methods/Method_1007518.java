public void cancel(){
  for (  Mailbox<?> mb : mbxs) {
    mb.removeMsgAvailableListener(this);
  }
}
