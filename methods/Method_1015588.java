public void unregister(EventHandler handler){
  if (handler != null) {
    event_handlers.remove(handler);
    num_event_handlers=event_handlers.size();
  }
}
