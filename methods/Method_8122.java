public void setScaled(boolean value){
  scaled=value;
  lastUpdateTime=System.currentTimeMillis();
  invalidate();
}
