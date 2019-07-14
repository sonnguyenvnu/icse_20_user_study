@Provides @Singleton public LocationManager provideLocationManager(){
  return (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
}
