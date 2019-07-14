@NonNull public static Intent makeSyncSettingsWithAuthority(@Nullable String authority){
  return makeSyncSettings(authority != null ? new String[]{authority} : null,null);
}
