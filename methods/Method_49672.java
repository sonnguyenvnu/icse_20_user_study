@SuppressWarnings("SameReturnValue") @Provides @Singleton public FusedLocationProviderApi provideFusedLocationProviderApi(){
  return LocationServices.FusedLocationApi;
}
