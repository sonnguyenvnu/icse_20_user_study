/** 
 * Parses the  {@link Format} and frame duration from a csd buffer.
 * @param csdBuffer The csd buffer.
 * @param formatId The id for the generated format. May be null.
 * @return A pair consisting of the {@link Format} and the frame duration in microseconds, or0 if the duration could not be determined.
 */
private static Pair<Format,Long> parseCsdBuffer(CsdBuffer csdBuffer,String formatId){
  byte[] csdData=Arrays.copyOf(csdBuffer.data,csdBuffer.length);
  int firstByte=csdData[4] & 0xFF;
  int secondByte=csdData[5] & 0xFF;
  int thirdByte=csdData[6] & 0xFF;
  int width=(firstByte << 4) | (secondByte >> 4);
  int height=(secondByte & 0x0F) << 8 | thirdByte;
  float pixelWidthHeightRatio=1f;
  int aspectRatioCode=(csdData[7] & 0xF0) >> 4;
switch (aspectRatioCode) {
case 2:
    pixelWidthHeightRatio=(4 * height) / (float)(3 * width);
  break;
case 3:
pixelWidthHeightRatio=(16 * height) / (float)(9 * width);
break;
case 4:
pixelWidthHeightRatio=(121 * height) / (float)(100 * width);
break;
default :
break;
}
Format format=Format.createVideoSampleFormat(formatId,MimeTypes.VIDEO_MPEG2,null,Format.NO_VALUE,Format.NO_VALUE,width,height,Format.NO_VALUE,Collections.singletonList(csdData),Format.NO_VALUE,pixelWidthHeightRatio,null);
long frameDurationUs=0;
int frameRateCodeMinusOne=(csdData[7] & 0x0F) - 1;
if (0 <= frameRateCodeMinusOne && frameRateCodeMinusOne < FRAME_RATE_VALUES.length) {
double frameRate=FRAME_RATE_VALUES[frameRateCodeMinusOne];
int sequenceExtensionPosition=csdBuffer.sequenceExtensionPosition;
int frameRateExtensionN=(csdData[sequenceExtensionPosition + 9] & 0x60) >> 5;
int frameRateExtensionD=(csdData[sequenceExtensionPosition + 9] & 0x1F);
if (frameRateExtensionN != frameRateExtensionD) {
frameRate*=(frameRateExtensionN + 1d) / (frameRateExtensionD + 1);
}
frameDurationUs=(long)(C.MICROS_PER_SECOND / frameRate);
}
return Pair.create(format,frameDurationUs);
}
