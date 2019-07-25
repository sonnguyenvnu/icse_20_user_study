@SuppressWarnings("unchecked") @Override public void receive(Event event){
  receiveTypedEvent((T)event);
}
