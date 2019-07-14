public void setColorAndDrawables(int color,int heartResId,int heartBorderResId){
  if (heartResId != mHeartResId) {
    sHeart=null;
  }
  if (heartBorderResId != mHeartBorderResId) {
    sHeartBorder=null;
  }
  mHeartResId=heartResId;
  mHeartBorderResId=heartBorderResId;
  setColor(color);
}
