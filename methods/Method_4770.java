private static Format parseMediaFormat(String formatId,NalUnitTargetBuffer vps,NalUnitTargetBuffer sps,NalUnitTargetBuffer pps){
  byte[] csd=new byte[vps.nalLength + sps.nalLength + pps.nalLength];
  System.arraycopy(vps.nalData,0,csd,0,vps.nalLength);
  System.arraycopy(sps.nalData,0,csd,vps.nalLength,sps.nalLength);
  System.arraycopy(pps.nalData,0,csd,vps.nalLength + sps.nalLength,pps.nalLength);
  ParsableNalUnitBitArray bitArray=new ParsableNalUnitBitArray(sps.nalData,0,sps.nalLength);
  bitArray.skipBits(40 + 4);
  int maxSubLayersMinus1=bitArray.readBits(3);
  bitArray.skipBit();
  bitArray.skipBits(88);
  bitArray.skipBits(8);
  int toSkip=0;
  for (int i=0; i < maxSubLayersMinus1; i++) {
    if (bitArray.readBit()) {
      toSkip+=89;
    }
    if (bitArray.readBit()) {
      toSkip+=8;
    }
  }
  bitArray.skipBits(toSkip);
  if (maxSubLayersMinus1 > 0) {
    bitArray.skipBits(2 * (8 - maxSubLayersMinus1));
  }
  bitArray.readUnsignedExpGolombCodedInt();
  int chromaFormatIdc=bitArray.readUnsignedExpGolombCodedInt();
  if (chromaFormatIdc == 3) {
    bitArray.skipBit();
  }
  int picWidthInLumaSamples=bitArray.readUnsignedExpGolombCodedInt();
  int picHeightInLumaSamples=bitArray.readUnsignedExpGolombCodedInt();
  if (bitArray.readBit()) {
    int confWinLeftOffset=bitArray.readUnsignedExpGolombCodedInt();
    int confWinRightOffset=bitArray.readUnsignedExpGolombCodedInt();
    int confWinTopOffset=bitArray.readUnsignedExpGolombCodedInt();
    int confWinBottomOffset=bitArray.readUnsignedExpGolombCodedInt();
    int subWidthC=chromaFormatIdc == 1 || chromaFormatIdc == 2 ? 2 : 1;
    int subHeightC=chromaFormatIdc == 1 ? 2 : 1;
    picWidthInLumaSamples-=subWidthC * (confWinLeftOffset + confWinRightOffset);
    picHeightInLumaSamples-=subHeightC * (confWinTopOffset + confWinBottomOffset);
  }
  bitArray.readUnsignedExpGolombCodedInt();
  bitArray.readUnsignedExpGolombCodedInt();
  int log2MaxPicOrderCntLsbMinus4=bitArray.readUnsignedExpGolombCodedInt();
  for (int i=bitArray.readBit() ? 0 : maxSubLayersMinus1; i <= maxSubLayersMinus1; i++) {
    bitArray.readUnsignedExpGolombCodedInt();
    bitArray.readUnsignedExpGolombCodedInt();
    bitArray.readUnsignedExpGolombCodedInt();
  }
  bitArray.readUnsignedExpGolombCodedInt();
  bitArray.readUnsignedExpGolombCodedInt();
  bitArray.readUnsignedExpGolombCodedInt();
  bitArray.readUnsignedExpGolombCodedInt();
  bitArray.readUnsignedExpGolombCodedInt();
  bitArray.readUnsignedExpGolombCodedInt();
  boolean scalingListEnabled=bitArray.readBit();
  if (scalingListEnabled && bitArray.readBit()) {
    skipScalingList(bitArray);
  }
  bitArray.skipBits(2);
  if (bitArray.readBit()) {
    bitArray.skipBits(8);
    bitArray.readUnsignedExpGolombCodedInt();
    bitArray.readUnsignedExpGolombCodedInt();
    bitArray.skipBit();
  }
  skipShortTermRefPicSets(bitArray);
  if (bitArray.readBit()) {
    for (int i=0; i < bitArray.readUnsignedExpGolombCodedInt(); i++) {
      int ltRefPicPocLsbSpsLength=log2MaxPicOrderCntLsbMinus4 + 4;
      bitArray.skipBits(ltRefPicPocLsbSpsLength + 1);
    }
  }
  bitArray.skipBits(2);
  float pixelWidthHeightRatio=1;
  if (bitArray.readBit()) {
    if (bitArray.readBit()) {
      int aspectRatioIdc=bitArray.readBits(8);
      if (aspectRatioIdc == NalUnitUtil.EXTENDED_SAR) {
        int sarWidth=bitArray.readBits(16);
        int sarHeight=bitArray.readBits(16);
        if (sarWidth != 0 && sarHeight != 0) {
          pixelWidthHeightRatio=(float)sarWidth / sarHeight;
        }
      }
 else       if (aspectRatioIdc < NalUnitUtil.ASPECT_RATIO_IDC_VALUES.length) {
        pixelWidthHeightRatio=NalUnitUtil.ASPECT_RATIO_IDC_VALUES[aspectRatioIdc];
      }
 else {
        Log.w(TAG,"Unexpected aspect_ratio_idc value: " + aspectRatioIdc);
      }
    }
  }
  return Format.createVideoSampleFormat(formatId,MimeTypes.VIDEO_H265,null,Format.NO_VALUE,Format.NO_VALUE,picWidthInLumaSamples,picHeightInLumaSamples,Format.NO_VALUE,Collections.singletonList(csd),Format.NO_VALUE,pixelWidthHeightRatio,null);
}
