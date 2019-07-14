private void updateHSLCircleColor(int x,int y){
  Color color=huesCircleView.getImage().getPixelReader().getColor(x,y);
  double max=Math.max(color.getRed(),Math.max(color.getGreen(),color.getBlue()));
  double min=Math.min(color.getRed(),Math.min(color.getGreen(),color.getBlue()));
  double hue=0;
  if (max != min) {
    double d=max - min;
    if (max == color.getRed()) {
      hue=(color.getGreen() - color.getBlue()) / d + (color.getGreen() < color.getBlue() ? 6 : 0);
    }
 else     if (max == color.getGreen()) {
      hue=(color.getBlue() - color.getRed()) / d + 2;
    }
 else     if (max == color.getBlue()) {
      hue=(color.getRed() - color.getGreen()) / d + 4;
    }
    hue/=6;
  }
  currentHue=map(hue,0,1,0,255);
  refreshHSLCircle();
}
