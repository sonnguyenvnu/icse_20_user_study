@Override public void onAccuracyChanged(Sensor sensor,int accuracy){
  if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
    Log.i(TAG,"Rotation sensor accuracy changed to: " + accuracy);
  }
}
