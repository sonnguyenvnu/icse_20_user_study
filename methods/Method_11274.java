private void setSeat(int i,int j,Canvas canvas,float seatWH,SeatType type,CabinType cabinType){
  float top=0f;
  float left=0f;
  if (cabinType == CabinType.Frist) {
    if (type == SeatType.Left) {
      top=rectFCabin.top + rectFCabin.width() / 2 + seatWH + i * (seatWH) + seatWH / 2;
      left=rectFCabin.left + j * (seatWH) + seatWH / 3;
    }
 else     if (type == SeatType.Middle) {
      top=rectFCabin.top + rectFCabin.width() / 2 + seatWH + i * (seatWH) + seatWH / 2 + seatWH / 2;
      left=rectFCabin.left + j * (seatWH) + seatWH * 1f;
    }
 else     if (type == SeatType.Right) {
      top=rectFCabin.top + rectFCabin.width() / 2 + seatWH + i * (seatWH) + seatWH / 2;
      left=rectFCabin.left + j * (seatWH) + seatWH * 2f - seatWH / 3f;
    }
  }
 else   if (cabinType == CabinType.Second) {
    if (type == SeatType.Left) {
      left=rectFCabin.left + j * (seatWH) + seatWH / 3;
      top=rectFCabin.top + seatWH * 14f + rectFCabin.width() / 2 + seatWH + i * (seatWH) + seatWH / 2;
    }
 else     if (type == SeatType.Middle) {
      left=rectFCabin.left + j * (seatWH) + seatWH / 1f;
      top=rectFCabin.top + rectFCabin.width() / 2 + seatWH * 14f + seatWH + i * (seatWH) + seatWH / 2 + seatWH / 2;
    }
 else     if (type == SeatType.Right) {
      left=rectFCabin.left + j * (seatWH) + seatWH * 2f - seatWH / 3f;
      top=rectFCabin.top + seatWH * 14f + rectFCabin.width() / 2 + seatWH + i * (seatWH) + seatWH / 2;
    }
  }
 else   if (cabinType == CabinType.Tourist) {
    if (type == SeatType.Left) {
      left=rectFCabin.left + j * (seatWH) + seatWH / 3;
      top=rectFWall.bottom + seatWH * 1.5f + i * (seatWH);
    }
 else     if (type == SeatType.Middle) {
      left=rectFCabin.left + j * (seatWH) + seatWH * 1f;
      top=rectFWall.bottom + seatWH * 1.5f + i * (seatWH);
    }
 else     if (type == SeatType.Right) {
      left=rectFCabin.left + j * (seatWH) + seatWH * 2.0f - seatWH / 3f;
      top=rectFWall.bottom + seatWH * 1.5f + i * (seatWH);
    }
  }
 else   if (cabinType == CabinType.Last) {
    if (type == SeatType.Left) {
      left=rectFCabin.left + j * (seatWH) + seatWH / 3;
      top=rectFWC.bottom + seatWH * 1.5f + i * (seatWH);
    }
 else     if (type == SeatType.Middle) {
      left=rectFCabin.left + j * (seatWH) + seatWH * 1f;
      top=rectFWC.bottom + seatWH * 1.5f + i * (seatWH);
    }
 else     if (type == SeatType.Right) {
      left=rectFCabin.left + j * (seatWH) + seatWH * 2.0f - seatWH / 3f;
      top=rectFWC.bottom + seatWH * 1.5f + i * (seatWH);
    }
  }
  RectF sRectF=new RectF();
  sRectF=new RectF(left,top,left + seatWH,top + seatWH);
  PointSeat point=null;
  if (cabinType == CabinType.Frist) {
    point=new PointSeat(i,j);
  }
 else   if (cabinType == CabinType.Second) {
    point=new PointSeat(i + 7,j);
  }
 else   if (cabinType == CabinType.Tourist) {
    point=new PointSeat(i + 10,j);
  }
 else   if (cabinType == CabinType.Last) {
    point=new PointSeat(i + 35,j);
  }
  if (mAnimatedValue == 1) {
    if (cabinType == CabinType.Frist) {
      if (type == SeatType.Left || type == SeatType.Right) {
        sRectF.top=sRectF.top - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.51f) - moveY;
        sRectF.bottom=sRectF.bottom - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.51f) - moveY;
      }
      if (type == SeatType.Middle) {
        sRectF.top=sRectF.top - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.8f) - seatWH / 2f - moveY;
        sRectF.bottom=sRectF.bottom - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.8f) - seatWH / 2f - moveY;
      }
    }
 else     if (cabinType == CabinType.Second) {
      if (type == SeatType.Left || type == SeatType.Right) {
        sRectF.top=sRectF.top - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.25f) - moveY;
        sRectF.bottom=sRectF.bottom - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.25f) - moveY;
      }
      if (type == SeatType.Middle) {
        sRectF.top=sRectF.top - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.75f) - seatWH / 2f - moveY;
        sRectF.bottom=sRectF.bottom - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1.75f) - seatWH / 2f - moveY;
      }
    }
 else     if (cabinType == CabinType.Tourist) {
      sRectF.top=sRectF.top - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1) - moveY;
      sRectF.bottom=sRectF.bottom - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1) - moveY;
    }
 else     if (cabinType == CabinType.Last) {
      sRectF.top=sRectF.top - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1) - moveY;
      sRectF.bottom=sRectF.bottom - rectFCabin.top - rectFCabin.width() / 2 - seatWH * (scaleMaxValue - 1) - moveY;
    }
    if (sRectF.top > 0 && sRectF.bottom < getMeasuredHeight()) {
      mSeats.put(getSeatKeyName(point.row,point.column),sRectF);
    }
  }
  if (mSeatSelected.containsKey(getSeatKeyName(point.row,point.column))) {
    canvas.drawBitmap(getSeat(seatWH,mSeatSelected.get(getSeatKeyName(point.row,point.column))),left,top,mPaint);
  }
 else   if (mSeatSelecting.containsKey(getSeatKeyName(point.row,point.column))) {
    canvas.drawBitmap(getSeat(seatWH,mSeatSelecting.get(getSeatKeyName(point.row,point.column))),left,top,mPaint);
    if (mAnimatedValue == 1) {
      if (mSeatSelecting.get(getSeatKeyName(point.row,point.column)) == SeatState.Selecting) {
        String text=(point.row + 1) + "," + (point.column + 1);
        mPaintOther.setColor(Color.WHITE);
        mPaintOther.setTextSize(seatWH / 4f);
        canvas.drawText(text,left + seatWH / 2f - getFontlength(mPaintOther,text) / 2,top + seatWH / 2f + getFontHeight(mPaintOther,text) / 3,mPaintOther);
        mPaintOther.setColor(Color.rgb(138,138,138));
      }
    }
  }
 else {
    canvas.drawBitmap(getSeat(seatWH,SeatState.Normal),left,top,mPaint);
  }
}
