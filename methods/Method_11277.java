private Bitmap getSeat(float width,SeatState type){
  if (type == SeatState.Normal) {
    if (mBitmapSeatNormal == null) {
      mBitmapSeatNormal=setBitmapSize(R.drawable.seat_gray,width);
    }
 else     if (Math.abs(mBitmapSeatNormal.getWidth() - width) > 1) {
      mBitmapSeatNormal=setBitmapSize(R.drawable.seat_gray,width);
    }
    return mBitmapSeatNormal;
  }
  if (type == SeatState.Selected) {
    if (mBitmapSeatSelected == null) {
      mBitmapSeatSelected=setBitmapSize(R.drawable.seat_sold,width);
    }
 else     if (Math.abs(mBitmapSeatSelected.getWidth() - width) > 1) {
      mBitmapSeatSelected=setBitmapSize(R.drawable.seat_sold,width);
    }
    return mBitmapSeatSelected;
  }
  if (type == SeatState.Selecting) {
    if (mBitmapSeatSelecting == null) {
      mBitmapSeatSelecting=setBitmapSize(R.drawable.seat_green,width);
    }
 else     if (Math.abs(mBitmapSeatSelecting.getWidth() - width) > 1) {
      mBitmapSeatSelecting=setBitmapSize(R.drawable.seat_green,width);
    }
    return mBitmapSeatSelecting;
  }
  return null;
}
