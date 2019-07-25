@Override public void deactivate(){
  removeOlderResults(new Date().getTime());
}
