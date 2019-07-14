@Provides @Singleton @NonNull static KSString provideKSString(final @PackageNameString @NonNull String packageName,final @NonNull Resources resources){
  return new KSString(packageName,resources);
}
