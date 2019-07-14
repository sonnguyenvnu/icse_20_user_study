@Provides @Singleton @NonNull static DeviceRegistrarType provideDeviceRegistrar(final @NonNull PlayServicesCapability playServicesCapability,final @ApplicationContext @NonNull Context context){
  return new DeviceRegistrar(playServicesCapability,context);
}
