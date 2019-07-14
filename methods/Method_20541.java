@Provides @Singleton @NonNull static Build provideBuild(final @NonNull PackageInfo packageInfo){
  return new Build(packageInfo);
}
