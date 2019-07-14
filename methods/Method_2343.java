private void shearY(Graphics g,int w1,int h1,Color color){
  int period=random.nextInt(40) + 10;
  boolean borderGap=true;
  int frames=20;
  int phase=7;
  for (int i=0; i < w1; i++) {
    double d=(double)(period >> 1) * Math.sin((double)i / (double)period + (6.2831853071795862D * (double)phase) / (double)frames);
    g.copyArea(i,0,1,h1,0,(int)d);
    if (borderGap) {
      g.setColor(color);
      g.drawLine(i,(int)d,i,0);
      g.drawLine(i,(int)d + h1,i,h1);
    }
  }
}
