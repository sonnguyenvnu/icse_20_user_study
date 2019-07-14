private static void fastBoxBlur(final Bitmap bitmap,final int iterations,final int radius){
  final int w=bitmap.getWidth();
  final int h=bitmap.getHeight();
  final int[] pixels=new int[w * h];
  bitmap.getPixels(pixels,0,w,0,0,w,h);
  final int diameter=radius + 1 + radius;
  final int[] div=new int[256 * diameter];
  int ptr=radius + 1;
  for (int b=1; b <= 255; b++) {
    for (int d=0; d < diameter; d++) {
      div[ptr]=b;
      ptr++;
    }
  }
  final int[] tempRowOrColumn=new int[Math.max(w,h)];
  for (int i=0; i < iterations; i++) {
    for (int row=0; row < h; row++) {
      internalHorizontalBlur(pixels,tempRowOrColumn,w,row,diameter,div);
      System.arraycopy(tempRowOrColumn,0,pixels,row * w,w);
    }
    for (int col=0; col < w; col++) {
      internalVerticalBlur(pixels,tempRowOrColumn,w,h,col,diameter,div);
      int pos=col;
      for (int row=0; row < h; row++) {
        pixels[pos]=tempRowOrColumn[row];
        pos+=w;
      }
    }
  }
  bitmap.setPixels(pixels,0,w,0,0,w,h);
}
