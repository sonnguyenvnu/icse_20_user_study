@Override protected void clipImpl(float x1,float y1,float x2,float y2){
  g2.setClip(new Rectangle2D.Float(x1,y1,x2 - x1,y2 - y1));
}
