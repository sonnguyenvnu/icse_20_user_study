public static Iterable<RestSetting> asSubRestSetting(final List<RestSubResourceSetting> setting){
  if (setting == null || setting.isEmpty()) {
    return ImmutableList.of();
  }
  return from(setting).transform(toSubResourceSetting());
}
