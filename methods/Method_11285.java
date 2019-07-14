Bitmap drawOverview(){
  isDrawOverviewBitmap=false;
  int bac=Color.parseColor("#7e000000");
  overviewPaint.setColor(bac);
  overviewPaint.setAntiAlias(true);
  overviewPaint.setStyle(Paint.Style.FILL);
  overviewBitmap.eraseColor(Color.TRANSPARENT);
  Canvas canvas=new Canvas(overviewBitmap);
  canvas.drawRect(0,0,rectW,rectH,overviewPaint);
  overviewPaint.setColor(Color.WHITE);
  for (int i=0; i < row; i++) {
    float top=i * rectHeight + i * overviewVerSpacing + overviewVerSpacing;
    for (int j=0; j < column; j++) {
      int seatType=getSeatType(i,j);
switch (seatType) {
case SEAT_TYPE_AVAILABLE:
        overviewPaint.setColor(Color.WHITE);
      break;
case SEAT_TYPE_NOT_AVAILABLE:
    continue;
case SEAT_TYPE_SELECTED:
  overviewPaint.setColor(overviewChecked);
break;
case SEAT_TYPE_SOLD:
overviewPaint.setColor(overviewSold);
break;
default :
break;
}
float left;
left=j * rectWidth + j * overviewSpacing + overviewSpacing;
canvas.drawRect(left,top,left + rectWidth,top + rectHeight,overviewPaint);
}
}
return overviewBitmap;
}
