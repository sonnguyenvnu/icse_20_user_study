public Bundler put(@NonNull String key,Parcelable value){
  Bundle safeBundle=new Bundle();
  safeBundle.putParcelable(key,value);
  if (isValidBundleSize(safeBundle)) {
    bundle.putParcelable(key,value);
  }
  clearBundle(safeBundle);
  return this;
}
