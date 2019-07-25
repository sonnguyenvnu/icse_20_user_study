private @Nullable ChannelType localize(Bundle bundle,ChannelType channelType,Locale locale){
  if (channelTypeI18nLocalizationService == null) {
    return null;
  }
  return channelTypeI18nLocalizationService.createLocalizedChannelType(bundle,channelType,locale);
}
