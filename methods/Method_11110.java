public void showAnimation(RxShineButton rxShineButton){
  btnWidth=rxShineButton.getWidth();
  btnHeight=rxShineButton.getHeight();
  thirdLength=getThirdLength(btnHeight,btnWidth);
  int[] location=new int[2];
  rxShineButton.getLocationInWindow(location);
  centerAnimX=location[0] + btnWidth / 2;
  centerAnimY=getMeasuredHeight() - rxShineButton.getBottomHeight() + btnHeight / 2;
  mRxShineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator valueAnimator){
      value=(float)valueAnimator.getAnimatedValue();
      if (shineSize != 0 && shineSize > 0) {
        paint.setStrokeWidth((shineSize) * (shineDistanceMultiple - value));
        paintSmall.setStrokeWidth(((float)shineSize / 3 * 2) * (shineDistanceMultiple - value));
      }
 else {
        paint.setStrokeWidth((btnWidth / 2) * (shineDistanceMultiple - value));
        paintSmall.setStrokeWidth((btnWidth / 3) * (shineDistanceMultiple - value));
      }
      rectF.set(centerAnimX - (btnWidth / (3 - shineDistanceMultiple) * value),centerAnimY - (btnHeight / (3 - shineDistanceMultiple) * value),centerAnimX + (btnWidth / (3 - shineDistanceMultiple) * value),centerAnimY + (btnHeight / (3 - shineDistanceMultiple) * value));
      rectFSmall.set(centerAnimX - (btnWidth / ((3 - shineDistanceMultiple) + distanceOffset) * value),centerAnimY - (btnHeight / ((3 - shineDistanceMultiple) + distanceOffset) * value),centerAnimX + (btnWidth / ((3 - shineDistanceMultiple) + distanceOffset) * value),centerAnimY + (btnHeight / ((3 - shineDistanceMultiple) + distanceOffset) * value));
      invalidate();
    }
  }
);
  mRxShineAnimator.startAnim(this,centerAnimX,centerAnimY);
  clickAnimator.start();
}
