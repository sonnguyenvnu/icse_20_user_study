public AUTH unregister(UpHandler handler){
  up_handlers.remove(handler);
  return this;
}
