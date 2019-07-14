protected Format buildFormat(String id,String label,String containerMimeType,int width,int height,float frameRate,int audioChannels,int audioSamplingRate,int bitrate,String language,@C.SelectionFlags int selectionFlags,List<Descriptor> accessibilityDescriptors,String codecs,List<Descriptor> supplementalProperties){
  String sampleMimeType=getSampleMimeType(containerMimeType,codecs);
  if (sampleMimeType != null) {
    if (MimeTypes.AUDIO_E_AC3.equals(sampleMimeType)) {
      sampleMimeType=parseEac3SupplementalProperties(supplementalProperties);
    }
    if (MimeTypes.isVideo(sampleMimeType)) {
      return Format.createVideoContainerFormat(id,label,containerMimeType,sampleMimeType,codecs,bitrate,width,height,frameRate,null,selectionFlags);
    }
 else     if (MimeTypes.isAudio(sampleMimeType)) {
      return Format.createAudioContainerFormat(id,label,containerMimeType,sampleMimeType,codecs,bitrate,audioChannels,audioSamplingRate,null,selectionFlags,language);
    }
 else     if (mimeTypeIsRawText(sampleMimeType)) {
      int accessibilityChannel;
      if (MimeTypes.APPLICATION_CEA608.equals(sampleMimeType)) {
        accessibilityChannel=parseCea608AccessibilityChannel(accessibilityDescriptors);
      }
 else       if (MimeTypes.APPLICATION_CEA708.equals(sampleMimeType)) {
        accessibilityChannel=parseCea708AccessibilityChannel(accessibilityDescriptors);
      }
 else {
        accessibilityChannel=Format.NO_VALUE;
      }
      return Format.createTextContainerFormat(id,label,containerMimeType,sampleMimeType,codecs,bitrate,selectionFlags,language,accessibilityChannel);
    }
  }
  return Format.createContainerFormat(id,label,containerMimeType,sampleMimeType,codecs,bitrate,selectionFlags,language);
}
