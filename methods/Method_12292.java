@Override public void invalidateSelf(){
  super.invalidateSelf();
  scheduleNextRender();
}
