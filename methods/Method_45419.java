public static Iterable<RestSetting> asRestSetting(final List<? extends RestBaseSetting> setting){
  if (setting == null || setting.isEmpty()) {
    return ImmutableList.of();
  }
  return from(setting).transform(toSetting());
}
