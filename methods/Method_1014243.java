@Override @Modified protected void modified(@Nullable Map<@NonNull String,@Nullable Object> configProperties){
  super.modified(configProperties);
  if (isBackgroundDiscoveryEnabled()) {
    startScan();
  }
}
