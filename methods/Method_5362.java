private static Format deriveVideoFormat(Format variantFormat){
  String codecs=Util.getCodecsOfType(variantFormat.codecs,C.TRACK_TYPE_VIDEO);
  String sampleMimeType=MimeTypes.getMediaMimeType(codecs);
  return Format.createVideoContainerFormat(variantFormat.id,variantFormat.label,variantFormat.containerMimeType,sampleMimeType,codecs,variantFormat.bitrate,variantFormat.width,variantFormat.height,variantFormat.frameRate,null,variantFormat.selectionFlags);
}
