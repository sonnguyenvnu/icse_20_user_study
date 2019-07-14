@Provides @Singleton static PackageInfo providePackageInfo(final @NonNull Application application){
  try {
    return application.getPackageManager().getPackageInfo(application.getPackageName(),0);
  }
 catch (  PackageManager.NameNotFoundException e) {
    e.printStackTrace();
    throw new RuntimeException();
  }
}
