/** 
 * Parses the AVC header buffer passed to  {@link #AVCHeader(byte[])}.
 */
public void parse(){
  profile=getValue(8);
  skipBit(8);
  level=getValue(8);
  if (profile == 100 || profile == 110 || profile == 122 || profile == -112) {
    if (getExpGolombCode() == 3) {
      getExpGolombCode();
    }
    getExpGolombCode();
    getExpGolombCode();
    getBit();
    if (getBit() == 1) {
      for (int i=0; i < 8; i++) {
        int seqScalingListPresentFlag=getBit();
        if (seqScalingListPresentFlag == 1) {
          int lastScale=8, nextScale=8;
          int scalingListSize=i < 6 ? 16 : 64;
          for (int pos=0; pos < scalingListSize; pos++) {
            if (nextScale != 0) {
              int deltaScale=getExpGolombCode();
              nextScale=(lastScale + deltaScale + 256) % 256;
            }
            lastScale=nextScale;
          }
        }
      }
    }
    getExpGolombCode();
    int picOrderCntType=getExpGolombCode();
    if (picOrderCntType == 0) {
      getExpGolombCode();
    }
 else     if (picOrderCntType == 1) {
      getBit();
      getExpGolombCode();
      getExpGolombCode();
      int n=getExpGolombCode();
      for (int i=0; i < n; i++) {
        getExpGolombCode();
      }
    }
    if (!parseFailed) {
      ref_frames=getExpGolombCode();
    }
 else {
      ref_frames=-1;
    }
  }
}
