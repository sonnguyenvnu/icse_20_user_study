/** 
 * Skips scaling_list_data(). See H.265/HEVC (2014) 7.3.4.
 */
private static void skipScalingList(ParsableNalUnitBitArray bitArray){
  for (int sizeId=0; sizeId < 4; sizeId++) {
    for (int matrixId=0; matrixId < 6; matrixId+=sizeId == 3 ? 3 : 1) {
      if (!bitArray.readBit()) {
        bitArray.readUnsignedExpGolombCodedInt();
      }
 else {
        int coefNum=Math.min(64,1 << (4 + (sizeId << 1)));
        if (sizeId > 1) {
          bitArray.readSignedExpGolombCodedInt();
        }
        for (int i=0; i < coefNum; i++) {
          bitArray.readSignedExpGolombCodedInt();
        }
      }
    }
  }
}
