private Image getHuesCircle(int width,int height){
  WritableImage raster=new WritableImage(width,height);
  PixelWriter pixelWriter=raster.getPixelWriter();
  Point2D center=new Point2D((double)width / 2,(double)height / 2);
  double rsmall=0.8 * width / 2;
  double rbig=(double)width / 2;
  for (int y=0; y < height; y++) {
    for (int x=0; x < width; x++) {
      double dx=x - center.getX();
      double dy=y - center.getY();
      double distance=Math.sqrt((dx * dx) + (dy * dy));
      double o=Math.atan2(dy,dx);
      if (distance > rsmall && distance < rbig) {
        double H=map(o,-Math.PI,Math.PI,0,255);
        double S=255;
        double L=152;
        pixelWriter.setColor(x,y,HSL2RGB(H,S,L));
      }
    }
  }
  return raster;
}
