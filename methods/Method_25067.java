private void setupProgressBounds(){
  int circleInsetHorizontal=hasShadow() ? getShadowX() : 0;
  int circleInsetVertical=hasShadow() ? getShadowY() : 0;
  mProgressCircleBounds=new RectF(circleInsetHorizontal + mProgressWidth / 2,circleInsetVertical + mProgressWidth / 2,calculateMeasuredWidth() - circleInsetHorizontal - mProgressWidth / 2,calculateMeasuredHeight() - circleInsetVertical - mProgressWidth / 2);
}
