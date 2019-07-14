/** 
 * If  {@link #FLAG_OVERRIDE_CAPTION_DESCRIPTORS} is set, returns a {@link List<Format>} of {@link #closedCaptionFormats}. If unset, parses the PMT descriptor information and returns a  {@link
   * List<Format>} for the declared formats, or {@link #closedCaptionFormats} if the descriptor isnot present.
 * @param esInfo The {@link EsInfo} passed to {@link #createPayloadReader(int,EsInfo)}.
 * @return A {@link List<Format>} containing list of closed caption formats.
 */
private List<Format> getClosedCaptionFormats(EsInfo esInfo){
  if (isSet(FLAG_OVERRIDE_CAPTION_DESCRIPTORS)) {
    return closedCaptionFormats;
  }
  ParsableByteArray scratchDescriptorData=new ParsableByteArray(esInfo.descriptorBytes);
  List<Format> closedCaptionFormats=this.closedCaptionFormats;
  while (scratchDescriptorData.bytesLeft() > 0) {
    int descriptorTag=scratchDescriptorData.readUnsignedByte();
    int descriptorLength=scratchDescriptorData.readUnsignedByte();
    int nextDescriptorPosition=scratchDescriptorData.getPosition() + descriptorLength;
    if (descriptorTag == DESCRIPTOR_TAG_CAPTION_SERVICE) {
      closedCaptionFormats=new ArrayList<>();
      int numberOfServices=scratchDescriptorData.readUnsignedByte() & 0x1F;
      for (int i=0; i < numberOfServices; i++) {
        String language=scratchDescriptorData.readString(3);
        int captionTypeByte=scratchDescriptorData.readUnsignedByte();
        boolean isDigital=(captionTypeByte & 0x80) != 0;
        String mimeType;
        int accessibilityChannel;
        if (isDigital) {
          mimeType=MimeTypes.APPLICATION_CEA708;
          accessibilityChannel=captionTypeByte & 0x3F;
        }
 else {
          mimeType=MimeTypes.APPLICATION_CEA608;
          accessibilityChannel=1;
        }
        byte flags=(byte)scratchDescriptorData.readUnsignedByte();
        scratchDescriptorData.skipBytes(1);
        List<byte[]> initializationData=null;
        if (isDigital) {
          boolean isWideAspectRatio=(flags & 0x40) != 0;
          initializationData=Cea708InitializationData.buildData(isWideAspectRatio);
        }
        closedCaptionFormats.add(Format.createTextSampleFormat(null,mimeType,null,Format.NO_VALUE,0,language,accessibilityChannel,null,Format.OFFSET_SAMPLE_RELATIVE,initializationData));
      }
    }
 else {
    }
    scratchDescriptorData.setPosition(nextDescriptorPosition);
  }
  return closedCaptionFormats;
}
