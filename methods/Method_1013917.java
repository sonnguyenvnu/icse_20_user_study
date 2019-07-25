@Override public void receive(Event event){
  if (callback != null) {
    logger.trace("Received Event: Source: {} Topic: {} Type: {}  Payload: {}",event.getSource(),event.getTopic(),event.getType(),event.getPayload());
    Map<String,Object> values=new HashMap<>();
    if (event instanceof ItemCommandEvent) {
      Command command=((ItemCommandEvent)event).getItemCommand();
      if (this.command == null || this.command.equals(command.toFullString())) {
        values.put("command",command);
        values.put("event",event);
        ((TriggerHandlerCallback)callback).triggered(this.module,values);
      }
    }
  }
}
