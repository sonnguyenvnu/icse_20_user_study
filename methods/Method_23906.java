@Override public void loadPixels(){
  if ((pixels == null) || (pixels.length != pixelWidth * pixelHeight)) {
    pixels=new int[pixelWidth * pixelHeight];
    loaded=false;
  }
  if (!loaded) {
    if (snapshotImage == null || snapshotImage.getWidth() != pixelWidth || snapshotImage.getHeight() != pixelHeight) {
      snapshotImage=new WritableImage(pixelWidth,pixelHeight);
    }
    SnapshotParameters sp=null;
    if (pixelDensity != 1) {
      sp=new SnapshotParameters();
      sp.setTransform(Transform.scale(pixelDensity,pixelDensity));
    }
    snapshotImage=((PSurfaceFX)surface).canvas.snapshot(sp,snapshotImage);
    PixelReader pr=snapshotImage.getPixelReader();
    pr.getPixels(0,0,pixelWidth,pixelHeight,argbFormat,pixels,0,pixelWidth);
    loaded=true;
    modified=false;
  }
}
