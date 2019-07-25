public void clear(byte color){
  int c=(color == 0) ? 0x00FFFFFF : 0x0;
  Graphics g=image.getGraphics();
  g.setColor(c);
  g.fillRect(0,0,image.getWidth(),image.getHeight());
}
