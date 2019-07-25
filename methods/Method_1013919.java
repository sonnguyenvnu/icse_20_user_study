@Override public void receive(Event event){
  if (callback != null) {
    logger.trace("Received Event: Source: {} Topic: {} Type: {}  Payload: {}",event.getSource(),event.getTopic(),event.getType(),event.getPayload());
    Map<String,Object> values=new HashMap<>();
    if (event instanceof ItemStateEvent && UPDATE_MODULE_TYPE_ID.equals(module.getTypeUID())) {
      State state=((ItemStateEvent)event).getItemState();
      if ((this.state == null || this.state.equals(state.toFullString()))) {
        values.put("state",state);
      }
    }
 else     if (event instanceof ItemStateChangedEvent && CHANGE_MODULE_TYPE_ID.equals(module.getTypeUID())) {
      State state=((ItemStateChangedEvent)event).getItemState();
      State oldState=((ItemStateChangedEvent)event).getOldItemState();
      if (stateMatches(this.state,state) && stateMatches(this.previousState,oldState)) {
        values.put("oldState",oldState);
        values.put("newState",state);
      }
    }
    if (!values.isEmpty()) {
      values.put("event",event);
      ((TriggerHandlerCallback)callback).triggered(this.module,values);
    }
  }
}
