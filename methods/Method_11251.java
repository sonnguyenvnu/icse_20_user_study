private void initOval(){
  mRadius=mCenterX > mCenterY ? mCenterY : mCenterX;
  textWidth=mRadius / 10;
  ratingBarWidth=mRadius / 10;
  shadowWidth=ratingBarWidth / 3;
  outlineWidth=shadowWidth / 3;
  int outlineRadius=mRadius - (textWidth / 2);
  int paddingRadius=outlineRadius - (textWidth / 2);
  int ratingBarRadius=paddingRadius - (textWidth / 2) - (ratingBarWidth / 2);
  int shadowRadius=ratingBarRadius - (ratingBarWidth / 2) - (shadowWidth / 2);
  outlineOval=new RectF();
  ratingOval=new RectF();
  shadowOval=new RectF();
  outlineOval.left=mCenterX - outlineRadius;
  outlineOval.top=mCenterY - outlineRadius;
  outlineOval.right=mCenterX + outlineRadius;
  outlineOval.bottom=mCenterY + outlineRadius;
  ratingOval.left=mCenterX - ratingBarRadius;
  ratingOval.top=mCenterY - ratingBarRadius;
  ratingOval.right=mCenterX + ratingBarRadius;
  ratingOval.bottom=mCenterY + ratingBarRadius;
  shadowOval.left=mCenterX - shadowRadius;
  shadowOval.top=mCenterY - shadowRadius;
  shadowOval.right=mCenterX + shadowRadius;
  shadowOval.bottom=mCenterY + shadowRadius;
}
