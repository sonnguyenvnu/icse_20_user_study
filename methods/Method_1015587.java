public void register(EventHandler handler){
  if (handler != null) {
    event_handlers.add(handler);
    num_event_handlers=event_handlers.size();
  }
}
