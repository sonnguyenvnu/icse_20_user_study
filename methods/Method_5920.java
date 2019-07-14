private static void skipScalingList(ParsableNalUnitBitArray bitArray,int size){
  int lastScale=8;
  int nextScale=8;
  for (int i=0; i < size; i++) {
    if (nextScale != 0) {
      int deltaScale=bitArray.readSignedExpGolombCodedInt();
      nextScale=(lastScale + deltaScale + 256) % 256;
    }
    lastScale=(nextScale == 0) ? lastScale : nextScale;
  }
}
