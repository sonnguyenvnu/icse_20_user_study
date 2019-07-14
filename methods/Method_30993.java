@NonNull public static Intent makeSyncSettingsWithAccountType(@Nullable String accountType){
  return makeSyncSettings(null,accountType != null ? new String[]{accountType} : null);
}
