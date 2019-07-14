@Override public void setPrimary(boolean primary){
  super.setPrimary(primary);
  pgl.setPrimary(primary);
  format=ARGB;
  if (primary) {
    fbStack=new FrameBuffer[FB_STACK_DEPTH];
    fontMap=new WeakHashMap<>();
    tessellator=new Tessellator();
  }
 else {
    tessellator=getPrimaryPG().tessellator;
  }
}
