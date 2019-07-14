protected void updateTexelsImpl(int x,int y,int w,int h){
  int x2=x + w;
  int y2=y + h;
  if (!modified) {
    mx1=PApplet.max(0,x);
    mx2=PApplet.min(width - 1,x2);
    my1=PApplet.max(0,y);
    my2=PApplet.min(height - 1,y2);
    modified=true;
  }
 else {
    if (x < mx1)     mx1=PApplet.max(0,x);
    if (x > mx2)     mx2=PApplet.min(width - 1,x);
    if (y < my1)     my1=PApplet.max(0,y);
    if (y > my2)     my2=y;
    if (x2 < mx1)     mx1=PApplet.max(0,x2);
    if (x2 > mx2)     mx2=PApplet.min(width - 1,x2);
    if (y2 < my1)     my1=PApplet.max(0,y2);
    if (y2 > my2)     my2=PApplet.min(height - 1,y2);
  }
}
