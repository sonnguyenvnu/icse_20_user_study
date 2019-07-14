private void calcRadius(){
  Rect bounds=getBounds();
  float x1;
  float y1;
  if (touchX >= 0 && touchY >= 0) {
    x1=touchX;
    y1=touchY;
  }
 else {
    x1=bounds.centerX();
    y1=bounds.centerY();
  }
  finalRadius=0;
  for (int a=0; a < 4; a++) {
    float x2;
    float y2;
switch (a) {
case 0:
      x2=bounds.left;
    y2=bounds.top;
  break;
case 1:
x2=bounds.left;
y2=bounds.bottom;
break;
case 2:
x2=bounds.right;
y2=bounds.top;
break;
case 3:
default :
x2=bounds.right;
y2=bounds.bottom;
break;
}
finalRadius=Math.max(finalRadius,(int)Math.ceil(Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))));
}
}
