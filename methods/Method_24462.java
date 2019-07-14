protected void loadPixels(int len){
  if (rgbaPixels == null || rgbaPixels.length < len) {
    rgbaPixels=new int[len];
  }
}
