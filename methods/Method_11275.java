private void drawSeatFirst(Canvas canvas){
  int row=7;
  int column=7;
  mSeats.clear();
  float seatWH=(float)(rectFCabin.width() / 9.0f);
  for (int i=0; i < row; i++) {
    for (int j=0; j < column; j++) {
      if (i >= 0 && j < 2) {
        setSeat(i,j,canvas,seatWH,SeatType.Left,CabinType.Frist);
      }
 else       if (j >= 2 && j < 5 && i < row - 1) {
        setSeat(i,j,canvas,seatWH,SeatType.Middle,CabinType.Frist);
      }
 else       if (j >= 5) {
        setSeat(i,j,canvas,seatWH,SeatType.Right,CabinType.Frist);
      }
    }
  }
  rectFWC.top=rectFCabin.top + rectFCabin.width() / 2 + seatWH + (row + 2.5f) * (seatWH) + seatWH / 2;
  rectFWC.bottom=rectFCabin.top + rectFCabin.width() / 2 + seatWH + (row + 4.5f) * (seatWH) + seatWH / 2;
  rectFWC.left=rectFCabin.left + seatWH / 3;
  rectFWC.right=rectFCabin.left + seatWH / 3 + seatWH * 2;
  mPaintOther.setStyle(Paint.Style.STROKE);
  canvas.drawRect(rectFWC,mPaintOther);
  drawWcText(rectFWC,canvas);
  RectF rectFWifi=new RectF();
  rectFWifi.top=rectFCabin.top + rectFCabin.width() / 2 + seatWH + (row + 1f) * (seatWH) + seatWH / 2;
  rectFWifi.bottom=rectFCabin.top + rectFCabin.width() / 2 + seatWH + (row + 4.5f) * (seatWH) + seatWH / 2;
  rectFWifi.left=rectFWC.right + seatWH / 2f;
  rectFWifi.right=rectFCabin.left + column * (seatWH) + seatWH * 2f - seatWH / 3f - seatWH * 2 - seatWH / 2f;
  canvas.drawRect(rectFWifi,mPaintOther);
  drawWifiLogo(rectFWifi,canvas);
  rectFWC.top=rectFCabin.top + rectFCabin.width() / 2 + seatWH + (row + 2.5f) * (seatWH) + seatWH / 2;
  rectFWC.bottom=rectFCabin.top + rectFCabin.width() / 2 + seatWH + (row + 4.5f) * (seatWH) + seatWH / 2;
  rectFWC.right=rectFCabin.left + column * (seatWH) + seatWH * 2f - seatWH / 3f;
  rectFWC.left=rectFCabin.left + column * (seatWH) + seatWH * 2f - seatWH / 3f - seatWH * 2;
  mPaintOther.setStyle(Paint.Style.STROKE);
  canvas.drawRect(rectFWC,mPaintOther);
  drawWcText(rectFWC,canvas);
  drawSeatSecond(canvas,seatWH);
}
