public Graphics2D checkImage(){
  if (image == null || ((BufferedImage)image).getWidth() != width * pixelDensity || ((BufferedImage)image).getHeight() != height * pixelDensity) {
    int wide=width * pixelDensity;
    int high=height * pixelDensity;
    image=new BufferedImage(wide,high,BufferedImage.TYPE_INT_ARGB);
  }
  return (Graphics2D)image.getGraphics();
}
