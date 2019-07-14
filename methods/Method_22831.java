static private void paintSquiggle(Graphics g,int y,int x1,int x2){
  int xx=x1;
  while (xx < x2) {
    g.drawLine(xx,y,xx + 2,y + 1);
    xx+=2;
    g.drawLine(xx,y + 1,xx + 2,y);
    xx+=2;
  }
}
