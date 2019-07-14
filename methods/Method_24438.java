public void setCursor(PImage image,int hotspotX,int hotspotY){
  Display disp=window.getScreen().getDisplay();
  BufferedImage bimg=(BufferedImage)image.getNative();
  DataBufferInt dbuf=(DataBufferInt)bimg.getData().getDataBuffer();
  int[] ipix=dbuf.getData();
  ByteBuffer pixels=ByteBuffer.allocate(ipix.length * 4);
  pixels.asIntBuffer().put(ipix);
  PixelFormat format=PixelFormat.ARGB8888;
  final Dimension size=new Dimension(bimg.getWidth(),bimg.getHeight());
  PixelRectangle pixelrect=new PixelRectangle.GenericPixelRect(format,size,0,false,pixels);
  final PointerIcon pi=disp.createPointerIcon(pixelrect,hotspotX,hotspotY);
  display.getEDTUtil().invoke(false,new Runnable(){
    @Override public void run(){
      window.setPointerVisible(true);
      window.setPointerIcon(pi);
    }
  }
);
}
