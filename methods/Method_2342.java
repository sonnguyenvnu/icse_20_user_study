private void shearX(Graphics g,int w1,int h1,Color color){
  int period=random.nextInt(2);
  boolean borderGap=true;
  int frames=1;
  int phase=random.nextInt(2);
  for (int i=0; i < h1; i++) {
    double d=(double)(period >> 1) * Math.sin((double)i / (double)period + (6.2831853071795862D * (double)phase) / (double)frames);
    g.copyArea(0,i,w1,1,(int)d,0);
    if (borderGap) {
      g.setColor(color);
      g.drawLine((int)d,i,0,i);
      g.drawLine((int)d + w1,i,w1,i);
    }
  }
}
