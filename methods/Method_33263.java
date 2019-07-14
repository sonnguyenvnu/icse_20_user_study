private Image getSLCricle(int width,int height){
  WritableImage raster=new WritableImage(width,height);
  PixelWriter pixelWriter=raster.getPixelWriter();
  Point2D center=new Point2D((double)width / 2,(double)height / 2);
  for (int y=0; y < height; y++) {
    for (int x=0; x < width; x++) {
      double dy=x - center.getX();
      double dx=y - center.getY();
      pixelWriter.setColor(x,y,getColor(dx,dy));
    }
  }
  return raster;
}
