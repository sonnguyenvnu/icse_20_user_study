@NonNull @RequiresApi(Build.VERSION_CODES.LOLLIPOP) public BundleBuilder putAll(@NonNull PersistableBundle bundle){
  mBundle.putAll(bundle);
  return this;
}
