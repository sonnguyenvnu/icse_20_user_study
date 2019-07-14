public void setColors(int color,int color1,int color2,int color3){
  colors[0].setColor(color);
  colors[1].setColor(color1);
  colors[2].setColor(color2);
  colors[3].setColor(color3);
  for (  Paint p : colors)   p.setFlags(Paint.ANTI_ALIAS_FLAG);
  paintInitialized=true;
}
