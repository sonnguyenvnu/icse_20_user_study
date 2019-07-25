void stop(){
  if (lightSensor != null) {
    SensorManager sensorManager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
    sensorManager.unregisterListener(this);
    cameraManager=null;
    lightSensor=null;
  }
}
