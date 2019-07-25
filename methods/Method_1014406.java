@Override public void deactivate(){
  removeOlderResults(new Date().getTime(),bridgeHandler.getThing().getUID());
  super.deactivate();
}
