@Override public void receive(Event event){
  if (callback != null) {
    logger.trace("Received Event: Source: {} Topic: {} Type: {}  Payload: {}",event.getSource(),event.getTopic(),event.getType(),event.getPayload());
    if (!event.getTopic().contains(source)) {
      return;
    }
    Map<String,Object> values=new HashMap<>();
    values.put("event",event);
    ((TriggerHandlerCallback)callback).triggered(this.module,values);
  }
}
