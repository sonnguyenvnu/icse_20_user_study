@Override public void destroy(){
  stopOrientationProvider();
  mOrientationConsumer=null;
  mSensorManager=null;
}
