private void drawTextStroke(Canvas canvas){
  getPaint().setStyle(Paint.Style.STROKE);
  setTextColor(textStrokeColor);
  getPaint().setFakeBoldText(true);
  getPaint().setStrokeWidth(textStrokeWidth);
  sdkOnDraw(canvas);
  getPaint().setStyle(Paint.Style.FILL);
  getPaint().setFakeBoldText(false);
  setTextColor(textFillColor);
}
