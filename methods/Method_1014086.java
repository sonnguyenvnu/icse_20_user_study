@Override protected ChannelGroupType localize(Bundle bundle,ChannelGroupType channelGroupType,Locale locale){
  if (channelGroupTypeI18nLocalizationService == null) {
    return null;
  }
  return channelGroupTypeI18nLocalizationService.createLocalizedChannelGroupType(bundle,channelGroupType,locale);
}
