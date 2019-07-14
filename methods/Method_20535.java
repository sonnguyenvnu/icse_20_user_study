@Provides @Singleton @NonNull static AndroidPayCapability provideAndroidPayCapability(final @NonNull PlayServicesCapability playServicesCapability,final @ApplicationContext @NonNull Context context){
  return new AndroidPayCapability(playServicesCapability,context);
}
