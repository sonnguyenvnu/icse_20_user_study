@NonNull public static Intent makeSyncSettings(@Nullable String[] authorities,@Nullable String[] accountTypes){
  Intent intent=new Intent(Settings.ACTION_SYNC_SETTINGS);
  if (!ArrayUtils.isEmpty(authorities)) {
    intent.putExtra(Settings.EXTRA_AUTHORITIES,authorities);
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
    if (!ArrayUtils.isEmpty(accountTypes)) {
      intent.putExtra(Settings.EXTRA_ACCOUNT_TYPES,accountTypes);
    }
  }
  return intent;
}
