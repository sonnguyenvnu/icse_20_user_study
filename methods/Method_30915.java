@NonNull public BundleBuilder putBinder(@Nullable String key,@Nullable IBinder value){
  BundleCompat.putBinder(mBundle,key,value);
  return this;
}
