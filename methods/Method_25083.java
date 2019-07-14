private void createDefaultIconAnimation(){
  float collapseAngle;
  float expandAngle;
  if (mOpenDirection == OPEN_UP) {
    collapseAngle=mLabelsPosition == LABELS_POSITION_LEFT ? OPENED_PLUS_ROTATION_LEFT : OPENED_PLUS_ROTATION_RIGHT;
    expandAngle=mLabelsPosition == LABELS_POSITION_LEFT ? OPENED_PLUS_ROTATION_LEFT : OPENED_PLUS_ROTATION_RIGHT;
  }
 else {
    collapseAngle=mLabelsPosition == LABELS_POSITION_LEFT ? OPENED_PLUS_ROTATION_RIGHT : OPENED_PLUS_ROTATION_LEFT;
    expandAngle=mLabelsPosition == LABELS_POSITION_LEFT ? OPENED_PLUS_ROTATION_RIGHT : OPENED_PLUS_ROTATION_LEFT;
  }
  ObjectAnimator collapseAnimator=ObjectAnimator.ofFloat(mImageToggle,"rotation",collapseAngle,CLOSED_PLUS_ROTATION);
  ObjectAnimator expandAnimator=ObjectAnimator.ofFloat(mImageToggle,"rotation",CLOSED_PLUS_ROTATION,expandAngle);
  mOpenAnimatorSet.play(expandAnimator);
  mCloseAnimatorSet.play(collapseAnimator);
  mOpenAnimatorSet.setInterpolator(mOpenInterpolator);
  mCloseAnimatorSet.setInterpolator(mCloseInterpolator);
  mOpenAnimatorSet.setDuration(ANIMATION_DURATION);
  mCloseAnimatorSet.setDuration(ANIMATION_DURATION);
}
