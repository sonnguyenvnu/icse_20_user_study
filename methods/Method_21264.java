/** 
 * Gets a parceled project from the intent data, may return `null`.
 */
private static @Nullable Project projectFromIntent(final @NonNull Intent intent){
  return intent.getParcelableExtra(IntentKey.PROJECT);
}
