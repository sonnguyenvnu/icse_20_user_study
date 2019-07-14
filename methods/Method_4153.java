/** 
 * Returns the AC-3 format given  {@code data} containing the AC3SpecificBox according to ETSI TS102 366 Annex F. The reading position of  {@code data} will be modified.
 * @param data The AC3SpecificBox to parse.
 * @param trackId The track identifier to set on the format.
 * @param language The language to set on the format.
 * @param drmInitData {@link DrmInitData} to be included in the format.
 * @return The AC-3 format parsed from data in the header.
 */
public static Format parseAc3AnnexFFormat(ParsableByteArray data,String trackId,String language,DrmInitData drmInitData){
  int fscod=(data.readUnsignedByte() & 0xC0) >> 6;
  int sampleRate=SAMPLE_RATE_BY_FSCOD[fscod];
  int nextByte=data.readUnsignedByte();
  int channelCount=CHANNEL_COUNT_BY_ACMOD[(nextByte & 0x38) >> 3];
  if ((nextByte & 0x04) != 0) {
    channelCount++;
  }
  return Format.createAudioSampleFormat(trackId,MimeTypes.AUDIO_AC3,null,Format.NO_VALUE,Format.NO_VALUE,channelCount,sampleRate,null,drmInitData,0,language);
}
