@Override public void drawBackground(Canvas c,Paint p,int left,int right,int top,int baseline,int bottom,CharSequence text,int start,int end,int lnum){
  Paint.Style style=p.getStyle();
  int color=p.getColor();
  p.setStyle(Paint.Style.FILL);
  p.setColor(this.color);
  rect.set(left,top,right,bottom);
  c.drawRect(rect,p);
  p.setColor(color);
  p.setStyle(style);
}
