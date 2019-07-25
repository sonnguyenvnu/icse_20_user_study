public void rectangle(double x,double y,double width,double height){
  hline(y,x,x + width);
  hline(y + height,x,x + width);
  vline(x,y,y + height);
  vline(x + width,y,y + height);
}
