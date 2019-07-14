/** 
 * Returns the E-AC-3 format given  {@code data} containing the EC3SpecificBox according to ETSI TS102 366 Annex F. The reading position of  {@code data} will be modified.
 * @param data The EC3SpecificBox to parse.
 * @param trackId The track identifier to set on the format.
 * @param language The language to set on the format.
 * @param drmInitData {@link DrmInitData} to be included in the format.
 * @return The E-AC-3 format parsed from data in the header.
 */
public static Format parseEAc3AnnexFFormat(ParsableByteArray data,String trackId,String language,DrmInitData drmInitData){
  data.skipBytes(2);
  int fscod=(data.readUnsignedByte() & 0xC0) >> 6;
  int sampleRate=SAMPLE_RATE_BY_FSCOD[fscod];
  int nextByte=data.readUnsignedByte();
  int channelCount=CHANNEL_COUNT_BY_ACMOD[(nextByte & 0x0E) >> 1];
  if ((nextByte & 0x01) != 0) {
    channelCount++;
  }
  nextByte=data.readUnsignedByte();
  int numDepSub=((nextByte & 0x1E) >> 1);
  if (numDepSub > 0) {
    int lowByteChanLoc=data.readUnsignedByte();
    if ((lowByteChanLoc & 0x02) != 0) {
      channelCount+=2;
    }
  }
  String mimeType=MimeTypes.AUDIO_E_AC3;
  if (data.bytesLeft() > 0) {
    nextByte=data.readUnsignedByte();
    if ((nextByte & 0x01) != 0) {
      mimeType=MimeTypes.AUDIO_E_AC3_JOC;
    }
  }
  return Format.createAudioSampleFormat(trackId,mimeType,null,Format.NO_VALUE,Format.NO_VALUE,channelCount,sampleRate,null,drmInitData,0,language);
}
