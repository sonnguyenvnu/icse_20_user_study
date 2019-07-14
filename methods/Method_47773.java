private int[] calculatePadding(int entireWidth,int entireHeight,int imageWidth,int imageHeight){
  int w=(int)(((float)entireWidth - imageWidth) / 2);
  int h=(int)(((float)entireHeight - imageHeight) / 2);
  return new int[]{w,h,w,h};
}
