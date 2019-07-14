@Provides @Singleton public SensorManager provideSensorManager(){
  return (SensorManager)mContext.getSystemService(Context.SENSOR_SERVICE);
}
