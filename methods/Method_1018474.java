@Override public void stop(){
  eventBus=null;
  clientFactory.getTaskEditView().setLocked(false);
}
