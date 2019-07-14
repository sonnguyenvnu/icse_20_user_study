/** 
 * Creates a blurred version of the given  {@code row} of {@code pixel}. It uses a moving average algorithm such that it reads every pixel of the row just once. The edge pixels are repeated to avoid artifacts. <p>Requires a pre-computed  {@code div} table of size (255 * diameter) that maps x -> (x /diameter) (can be rounded)
 */
private static void internalHorizontalBlur(int[] pixels,int[] outRow,int w,int row,int diameter,int[] div){
  final int firstInByte=w * row;
  final int lastInByte=w * (row + 1) - 1;
  final int radius=diameter >> 1;
  int a=0, r=0, g=0, b=0;
  int pixel;
  for (int i=-radius; i < w + radius; i++) {
    final int ii=bound(firstInByte + i,firstInByte,lastInByte);
    pixel=pixels[ii];
    r+=(pixel >> 16) & 0xFF;
    g+=(pixel >> 8) & 0xFF;
    b+=pixel & 0xFF;
    a+=pixel >>> 24;
    if (i >= radius) {
      final int outOffset=i - radius;
      outRow[outOffset]=(div[a] << 24) | (div[r] << 16) | (div[g] << 8) | div[b];
      final int j=i - (diameter - 1);
      final int jj=bound(firstInByte + j,firstInByte,lastInByte);
      pixel=pixels[jj];
      r-=(pixel >> 16) & 0xFF;
      g-=(pixel >> 8) & 0xFF;
      b-=pixel & 0xFF;
      a-=pixel >>> 24;
    }
  }
}
