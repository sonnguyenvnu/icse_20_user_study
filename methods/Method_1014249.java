@Override public void characters(char[] ch,int start,int length) throws SAXException {
  logger.trace("{}: Text data during {}: '{}'",handler.getDeviceName(),state,new String(ch,start,length));
  super.characters(ch,start,length);
switch (state) {
case INIT:
case Msg:
case MsgHeader:
case MsgBody:
case Bass:
case BassUpdated:
case Updates:
case Volume:
case VolumeUpdated:
case Info:
case Preset:
case Presets:
case NowPlaying:
case NowPlayingRateEnabled:
case NowPlayingSkipEnabled:
case NowPlayingSkipPreviousEnabled:
case ContentItem:
case UnprocessedNoTextExpected:
case Zone:
case ZoneUpdated:
case Sources:
    logger.debug("{}: Unexpected text data during {}: '{}'",handler.getDeviceName(),state,new String(ch,start,length));
  break;
case BassMin:
case BassMax:
case BassDefault:
case BassTarget:
case VolumeTarget:
break;
case BassCapabilities:
logger.debug("{}: Unexpected text data during {}: '{}'",handler.getDeviceName(),state,new String(ch,start,length));
break;
case Unprocessed:
break;
case BassActual:
commandExecutor.updateBassLevelGUIState(new DecimalType(new String(ch,start,length)));
break;
case InfoName:
setConfigOption(DEVICE_INFO_NAME,new String(ch,start,length));
break;
case InfoType:
setConfigOption(DEVICE_INFO_TYPE,new String(ch,start,length));
setConfigOption(PROPERTY_MODEL_ID,new String(ch,start,length));
break;
case InfoModuleType:
setConfigOption(PROPERTY_HARDWARE_VERSION,new String(ch,start,length));
break;
case InfoFirmwareVersion:
String[] fwVersion=new String(ch,start,length).split(" ");
setConfigOption(PROPERTY_FIRMWARE_VERSION,fwVersion[0]);
break;
case BassAvailable:
boolean bassAvailable=Boolean.parseBoolean(new String(ch,start,length));
commandExecutor.setBassAvailable(bassAvailable);
break;
case NowPlayingAlbum:
updateNowPlayingAlbum(new StringType(new String(ch,start,length)));
break;
case NowPlayingArt:
String url=new String(ch,start,length);
if (url.startsWith("http")) {
handler.getScheduler().submit(() -> {
RawType image=HttpUtil.downloadImage(url,true,500000);
if (image != null) {
updateNowPlayingArtwork(image);
}
 else {
updateNowPlayingArtwork(UnDefType.UNDEF);
}
}
);
}
 else {
updateNowPlayingArtwork(UnDefType.UNDEF);
}
break;
case NowPlayingArtist:
updateNowPlayingArtist(new StringType(new String(ch,start,length)));
break;
case ContentItemItemName:
contentItem.setItemName(new String(ch,start,length));
break;
case ContentItemContainerArt:
contentItem.setContainerArt(new String(ch,start,length));
break;
case NowPlayingDescription:
updateNowPlayingDescription(new StringType(new String(ch,start,length)));
break;
case NowPlayingGenre:
updateNowPlayingGenre(new StringType(new String(ch,start,length)));
break;
case NowPlayingPlayStatus:
String playPauseState=new String(ch,start,length);
if ("PLAY_STATE".equals(playPauseState) || "BUFFERING_STATE".equals(playPauseState)) {
commandExecutor.updatePlayerControlGUIState(PlayPauseType.PLAY);
}
 else if ("STOP_STATE".equals(playPauseState) || "PAUSE_STATE".equals(playPauseState)) {
commandExecutor.updatePlayerControlGUIState(PlayPauseType.PAUSE);
}
break;
case NowPlayingStationLocation:
updateNowPlayingStationLocation(new StringType(new String(ch,start,length)));
break;
case NowPlayingStationName:
updateNowPlayingStationName(new StringType(new String(ch,start,length)));
break;
case NowPlayingTrack:
updateNowPlayingTrack(new StringType(new String(ch,start,length)));
break;
case VolumeActual:
commandExecutor.updateVolumeGUIState(new PercentType(Integer.parseInt(new String(ch,start,length))));
break;
case VolumeMuteEnabled:
volumeMuteEnabled=Boolean.parseBoolean(new String(ch,start,length));
commandExecutor.setCurrentMuted(volumeMuteEnabled);
break;
case MasterDeviceId:
if (masterDeviceId != null) {
masterDeviceId.macAddress=new String(ch,start,length);
}
break;
case GroupName:
if (masterDeviceId != null) {
masterDeviceId.groupName=new String(ch,start,length);
}
break;
case DeviceId:
deviceId=new String(ch,start,length);
break;
case DeviceIp:
if (masterDeviceId != null && Objects.equals(masterDeviceId.macAddress,deviceId)) {
masterDeviceId.host=new String(ch,start,length);
}
break;
default :
break;
}
}
