@Provides @Singleton @PackageNameString static String providePackageName(final @NonNull Application application){
  return application.getPackageName();
}
