/** 
 * Consumes the unescaped content of an SEI NAL unit, writing the content of any CEA-608 messages as samples to all of the provided outputs.
 * @param presentationTimeUs The presentation time in microseconds for any samples.
 * @param seiBuffer The unescaped SEI NAL unit data, excluding the NAL unit start code and type.
 * @param outputs The outputs to which any samples should be written.
 */
public static void consume(long presentationTimeUs,ParsableByteArray seiBuffer,TrackOutput[] outputs){
  while (seiBuffer.bytesLeft() > 1) {
    int payloadType=readNon255TerminatedValue(seiBuffer);
    int payloadSize=readNon255TerminatedValue(seiBuffer);
    int nextPayloadPosition=seiBuffer.getPosition() + payloadSize;
    if (payloadSize == -1 || payloadSize > seiBuffer.bytesLeft()) {
      Log.w(TAG,"Skipping remainder of malformed SEI NAL unit.");
      nextPayloadPosition=seiBuffer.limit();
    }
 else     if (payloadType == PAYLOAD_TYPE_CC && payloadSize >= 8) {
      int countryCode=seiBuffer.readUnsignedByte();
      int providerCode=seiBuffer.readUnsignedShort();
      int userIdentifier=0;
      if (providerCode == PROVIDER_CODE_ATSC) {
        userIdentifier=seiBuffer.readInt();
      }
      int userDataTypeCode=seiBuffer.readUnsignedByte();
      if (providerCode == PROVIDER_CODE_DIRECTV) {
        seiBuffer.skipBytes(1);
      }
      boolean messageIsSupportedCeaCaption=countryCode == COUNTRY_CODE && (providerCode == PROVIDER_CODE_ATSC || providerCode == PROVIDER_CODE_DIRECTV) && userDataTypeCode == USER_DATA_TYPE_CODE_MPEG_CC;
      if (providerCode == PROVIDER_CODE_ATSC) {
        messageIsSupportedCeaCaption&=userIdentifier == USER_DATA_IDENTIFIER_GA94;
      }
      if (messageIsSupportedCeaCaption) {
        consumeCcData(presentationTimeUs,seiBuffer,outputs);
      }
    }
    seiBuffer.setPosition(nextPayloadPosition);
  }
}
