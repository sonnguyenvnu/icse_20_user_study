private static Format deriveAudioFormat(Format variantFormat,Format mediaTagFormat,boolean isPrimaryTrackInVariant){
  String codecs;
  int channelCount=Format.NO_VALUE;
  int selectionFlags=0;
  String language=null;
  String label=null;
  if (mediaTagFormat != null) {
    codecs=mediaTagFormat.codecs;
    channelCount=mediaTagFormat.channelCount;
    selectionFlags=mediaTagFormat.selectionFlags;
    language=mediaTagFormat.language;
    label=mediaTagFormat.label;
  }
 else {
    codecs=Util.getCodecsOfType(variantFormat.codecs,C.TRACK_TYPE_AUDIO);
    if (isPrimaryTrackInVariant) {
      channelCount=variantFormat.channelCount;
      selectionFlags=variantFormat.selectionFlags;
      language=variantFormat.label;
      label=variantFormat.label;
    }
  }
  String sampleMimeType=MimeTypes.getMediaMimeType(codecs);
  int bitrate=isPrimaryTrackInVariant ? variantFormat.bitrate : Format.NO_VALUE;
  return Format.createAudioContainerFormat(variantFormat.id,label,variantFormat.containerMimeType,sampleMimeType,codecs,bitrate,channelCount,Format.NO_VALUE,null,selectionFlags,language);
}
