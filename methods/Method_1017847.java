void start(CameraManager cameraManager){
  this.cameraManager=cameraManager;
  SharedPreferences sharedPrefs=PreferenceManager.getDefaultSharedPreferences(context);
  if (FrontLightMode.readPref(sharedPrefs) == FrontLightMode.AUTO) {
    SensorManager sensorManager=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
    lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    if (lightSensor != null) {
      sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
  }
}
