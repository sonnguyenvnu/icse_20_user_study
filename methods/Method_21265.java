/** 
 * Gets a project param from the intent data, may return `null`.
 */
private static @Nullable String paramFromIntent(final @NonNull Intent intent){
  if (intent.hasExtra(IntentKey.PROJECT_PARAM)) {
    return intent.getStringExtra(IntentKey.PROJECT_PARAM);
  }
  return paramFromUri(IntentMapper.uri(intent));
}
