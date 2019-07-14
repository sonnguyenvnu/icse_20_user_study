@Override protected int[] calculateItemBorders(int[] cachedBorders,int spanCount,int totalSpace){
  if (cachedBorders == null || cachedBorders.length != spanCount + 1 || cachedBorders[cachedBorders.length - 1] != totalSpace) {
    cachedBorders=new int[spanCount + 1];
  }
  cachedBorders[0]=0;
  for (int i=1; i <= spanCount; i++) {
    cachedBorders[i]=(int)Math.ceil(i / (float)spanCount * totalSpace);
  }
  return cachedBorders;
}
