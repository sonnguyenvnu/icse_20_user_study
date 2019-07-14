private void drawValue(Canvas canvas,RectF rect,double value){
  if (value == 0)   return;
  int activeColor=textColor;
  if (value / 1000 >= target)   activeColor=primaryColor;
  String label=NumberButtonViewKt.toShortString(value / 1000);
  Rect rText=new Rect();
  pText.getTextBounds(label,0,label.length(),rText);
  float offset=0.5f * em;
  float x=rect.centerX();
  float y=rect.top - offset;
  int cap=(int)(-0.1f * em);
  rText.offset((int)x,(int)y);
  rText.offset(-rText.width() / 2,0);
  rText.inset(3 * cap,cap);
  setModeOrColor(pText,XFERMODE_CLEAR,backgroundColor);
  canvas.drawRect(rText,pText);
  setModeOrColor(pText,XFERMODE_SRC,activeColor);
  canvas.drawText(label,x,y,pText);
}
