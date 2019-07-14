/** 
 * Conversion values taken from ISO 14496-10 Table A-1.
 * @param avcLevel one of CodecProfileLevel.AVCLevel* constants.
 * @return maximum frame size that can be decoded by a decoder with the specified avc level(or  {@code -1} if the level is not recognized)
 */
private static int avcLevelToMaxFrameSize(int avcLevel){
switch (avcLevel) {
case CodecProfileLevel.AVCLevel1:
case CodecProfileLevel.AVCLevel1b:
    return 99 * 16 * 16;
case CodecProfileLevel.AVCLevel12:
case CodecProfileLevel.AVCLevel13:
case CodecProfileLevel.AVCLevel2:
  return 396 * 16 * 16;
case CodecProfileLevel.AVCLevel21:
return 792 * 16 * 16;
case CodecProfileLevel.AVCLevel22:
case CodecProfileLevel.AVCLevel3:
return 1620 * 16 * 16;
case CodecProfileLevel.AVCLevel31:
return 3600 * 16 * 16;
case CodecProfileLevel.AVCLevel32:
return 5120 * 16 * 16;
case CodecProfileLevel.AVCLevel4:
case CodecProfileLevel.AVCLevel41:
return 8192 * 16 * 16;
case CodecProfileLevel.AVCLevel42:
return 8704 * 16 * 16;
case CodecProfileLevel.AVCLevel5:
return 22080 * 16 * 16;
case CodecProfileLevel.AVCLevel51:
case CodecProfileLevel.AVCLevel52:
return 36864 * 16 * 16;
default :
return -1;
}
}
