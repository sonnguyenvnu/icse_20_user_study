/** 
 * Returns FFmpeg-compatible codec-specific initialization data ("extra data"), or  {@code null} ifnot required.
 */
private static @Nullable byte[] getExtraData(String mimeType,List<byte[]> initializationData){
switch (mimeType) {
case MimeTypes.AUDIO_AAC:
case MimeTypes.AUDIO_ALAC:
case MimeTypes.AUDIO_OPUS:
    return initializationData.get(0);
case MimeTypes.AUDIO_VORBIS:
  byte[] header0=initializationData.get(0);
byte[] header1=initializationData.get(1);
byte[] extraData=new byte[header0.length + header1.length + 6];
extraData[0]=(byte)(header0.length >> 8);
extraData[1]=(byte)(header0.length & 0xFF);
System.arraycopy(header0,0,extraData,2,header0.length);
extraData[header0.length + 2]=0;
extraData[header0.length + 3]=0;
extraData[header0.length + 4]=(byte)(header1.length >> 8);
extraData[header0.length + 5]=(byte)(header1.length & 0xFF);
System.arraycopy(header1,0,extraData,header0.length + 6,header1.length);
return extraData;
default :
return null;
}
}
