private static @Nullable DiscoveryParams paramsFromIntent(final @NonNull Intent intent){
  return intent.getParcelableExtra(IntentKey.DISCOVERY_PARAMS);
}
