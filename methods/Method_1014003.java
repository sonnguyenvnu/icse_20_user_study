@Override public void receive(Event event){
  if (event instanceof ItemStateEvent) {
    receiveUpdate((ItemStateEvent)event);
  }
 else   if (event instanceof ItemCommandEvent) {
    receiveCommand((ItemCommandEvent)event);
  }
}
