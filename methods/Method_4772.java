/** 
 * Reads the number of short term reference picture sets in a SPS as ue(v), then skips all of them. See H.265/HEVC (2014) 7.3.7.
 */
private static void skipShortTermRefPicSets(ParsableNalUnitBitArray bitArray){
  int numShortTermRefPicSets=bitArray.readUnsignedExpGolombCodedInt();
  boolean interRefPicSetPredictionFlag=false;
  int numNegativePics;
  int numPositivePics;
  int previousNumDeltaPocs=0;
  for (int stRpsIdx=0; stRpsIdx < numShortTermRefPicSets; stRpsIdx++) {
    if (stRpsIdx != 0) {
      interRefPicSetPredictionFlag=bitArray.readBit();
    }
    if (interRefPicSetPredictionFlag) {
      bitArray.skipBit();
      bitArray.readUnsignedExpGolombCodedInt();
      for (int j=0; j <= previousNumDeltaPocs; j++) {
        if (bitArray.readBit()) {
          bitArray.skipBit();
        }
      }
    }
 else {
      numNegativePics=bitArray.readUnsignedExpGolombCodedInt();
      numPositivePics=bitArray.readUnsignedExpGolombCodedInt();
      previousNumDeltaPocs=numNegativePics + numPositivePics;
      for (int i=0; i < numNegativePics; i++) {
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.skipBit();
      }
      for (int i=0; i < numPositivePics; i++) {
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.skipBit();
      }
    }
  }
}
