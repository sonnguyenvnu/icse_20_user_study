/** 
 * Parses an SPS NAL unit using the syntax defined in ITU-T Recommendation H.264 (2013) subsection 7.3.2.1.1.
 * @param nalData A buffer containing escaped SPS data.
 * @param nalOffset The offset of the NAL unit header in {@code nalData}.
 * @param nalLimit The limit of the NAL unit in {@code nalData}.
 * @return A parsed representation of the SPS data.
 */
public static SpsData parseSpsNalUnit(byte[] nalData,int nalOffset,int nalLimit){
  ParsableNalUnitBitArray data=new ParsableNalUnitBitArray(nalData,nalOffset,nalLimit);
  data.skipBits(8);
  int profileIdc=data.readBits(8);
  int constraintsFlagsAndReservedZero2Bits=data.readBits(8);
  int levelIdc=data.readBits(8);
  int seqParameterSetId=data.readUnsignedExpGolombCodedInt();
  int chromaFormatIdc=1;
  boolean separateColorPlaneFlag=false;
  if (profileIdc == 100 || profileIdc == 110 || profileIdc == 122 || profileIdc == 244 || profileIdc == 44 || profileIdc == 83 || profileIdc == 86 || profileIdc == 118 || profileIdc == 128 || profileIdc == 138) {
    chromaFormatIdc=data.readUnsignedExpGolombCodedInt();
    if (chromaFormatIdc == 3) {
      separateColorPlaneFlag=data.readBit();
    }
    data.readUnsignedExpGolombCodedInt();
    data.readUnsignedExpGolombCodedInt();
    data.skipBit();
    boolean seqScalingMatrixPresentFlag=data.readBit();
    if (seqScalingMatrixPresentFlag) {
      int limit=(chromaFormatIdc != 3) ? 8 : 12;
      for (int i=0; i < limit; i++) {
        boolean seqScalingListPresentFlag=data.readBit();
        if (seqScalingListPresentFlag) {
          skipScalingList(data,i < 6 ? 16 : 64);
        }
      }
    }
  }
  int frameNumLength=data.readUnsignedExpGolombCodedInt() + 4;
  int picOrderCntType=data.readUnsignedExpGolombCodedInt();
  int picOrderCntLsbLength=0;
  boolean deltaPicOrderAlwaysZeroFlag=false;
  if (picOrderCntType == 0) {
    picOrderCntLsbLength=data.readUnsignedExpGolombCodedInt() + 4;
  }
 else   if (picOrderCntType == 1) {
    deltaPicOrderAlwaysZeroFlag=data.readBit();
    data.readSignedExpGolombCodedInt();
    data.readSignedExpGolombCodedInt();
    long numRefFramesInPicOrderCntCycle=data.readUnsignedExpGolombCodedInt();
    for (int i=0; i < numRefFramesInPicOrderCntCycle; i++) {
      data.readUnsignedExpGolombCodedInt();
    }
  }
  data.readUnsignedExpGolombCodedInt();
  data.skipBit();
  int picWidthInMbs=data.readUnsignedExpGolombCodedInt() + 1;
  int picHeightInMapUnits=data.readUnsignedExpGolombCodedInt() + 1;
  boolean frameMbsOnlyFlag=data.readBit();
  int frameHeightInMbs=(2 - (frameMbsOnlyFlag ? 1 : 0)) * picHeightInMapUnits;
  if (!frameMbsOnlyFlag) {
    data.skipBit();
  }
  data.skipBit();
  int frameWidth=picWidthInMbs * 16;
  int frameHeight=frameHeightInMbs * 16;
  boolean frameCroppingFlag=data.readBit();
  if (frameCroppingFlag) {
    int frameCropLeftOffset=data.readUnsignedExpGolombCodedInt();
    int frameCropRightOffset=data.readUnsignedExpGolombCodedInt();
    int frameCropTopOffset=data.readUnsignedExpGolombCodedInt();
    int frameCropBottomOffset=data.readUnsignedExpGolombCodedInt();
    int cropUnitX;
    int cropUnitY;
    if (chromaFormatIdc == 0) {
      cropUnitX=1;
      cropUnitY=2 - (frameMbsOnlyFlag ? 1 : 0);
    }
 else {
      int subWidthC=(chromaFormatIdc == 3) ? 1 : 2;
      int subHeightC=(chromaFormatIdc == 1) ? 2 : 1;
      cropUnitX=subWidthC;
      cropUnitY=subHeightC * (2 - (frameMbsOnlyFlag ? 1 : 0));
    }
    frameWidth-=(frameCropLeftOffset + frameCropRightOffset) * cropUnitX;
    frameHeight-=(frameCropTopOffset + frameCropBottomOffset) * cropUnitY;
  }
  float pixelWidthHeightRatio=1;
  boolean vuiParametersPresentFlag=data.readBit();
  if (vuiParametersPresentFlag) {
    boolean aspectRatioInfoPresentFlag=data.readBit();
    if (aspectRatioInfoPresentFlag) {
      int aspectRatioIdc=data.readBits(8);
      if (aspectRatioIdc == NalUnitUtil.EXTENDED_SAR) {
        int sarWidth=data.readBits(16);
        int sarHeight=data.readBits(16);
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
  return new SpsData(profileIdc,constraintsFlagsAndReservedZero2Bits,levelIdc,seqParameterSetId,frameWidth,frameHeight,pixelWidthHeightRatio,separateColorPlaneFlag,frameMbsOnlyFlag,frameNumLength,picOrderCntType,picOrderCntLsbLength,deltaPicOrderAlwaysZeroFlag);
}
