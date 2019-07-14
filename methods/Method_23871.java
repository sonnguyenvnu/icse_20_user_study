protected void flushPixels(){
  boolean hasPixels=modified && pixels != null;
  if (hasPixels) {
    int mx1=getModifiedX1();
    int mx2=getModifiedX2();
    int my1=getModifiedY1();
    int my2=getModifiedY2();
    int mw=mx2 - mx1;
    int mh=my2 - my1;
    if (pixelDensity == 1) {
      PixelWriter pw=context.getPixelWriter();
      pw.setPixels(mx1,my1,mw,mh,argbFormat,pixels,mx1 + my1 * pixelWidth,pixelWidth);
    }
 else {
      if (snapshotImage == null || snapshotImage.getWidth() != pixelWidth || snapshotImage.getHeight() != pixelHeight) {
        snapshotImage=new WritableImage(pixelWidth,pixelHeight);
      }
      PixelWriter pw=snapshotImage.getPixelWriter();
      pw.setPixels(mx1,my1,mw,mh,argbFormat,pixels,mx1 + my1 * pixelWidth,pixelWidth);
      context.save();
      resetMatrix();
      context.scale(1d / pixelDensity,1d / pixelDensity);
      context.drawImage(snapshotImage,mx1,my1,mw,mh,mx1,my1,mw,mh);
      context.restore();
    }
  }
  modified=false;
}
