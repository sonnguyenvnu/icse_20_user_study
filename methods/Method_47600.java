/** 
 * Render the animations for appearing and disappearing.
 */
private void renderAnimations(){
  Keyframe kf0, kf1, kf2, kf3;
  float midwayPoint=0.2f;
  int duration=500;
  kf0=Keyframe.ofFloat(0f,1);
  kf1=Keyframe.ofFloat(midwayPoint,mTransitionMidRadiusMultiplier);
  kf2=Keyframe.ofFloat(1f,mTransitionEndRadiusMultiplier);
  PropertyValuesHolder radiusDisappear=PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier",kf0,kf1,kf2);
  kf0=Keyframe.ofFloat(0f,1f);
  kf1=Keyframe.ofFloat(1f,0f);
  PropertyValuesHolder fadeOut=PropertyValuesHolder.ofKeyframe("alpha",kf0,kf1);
  mDisappearAnimator=ObjectAnimator.ofPropertyValuesHolder(this,radiusDisappear,fadeOut).setDuration(duration);
  mDisappearAnimator.addUpdateListener(mInvalidateUpdateListener);
  float delayMultiplier=0.25f;
  float transitionDurationMultiplier=1f;
  float totalDurationMultiplier=transitionDurationMultiplier + delayMultiplier;
  int totalDuration=(int)(duration * totalDurationMultiplier);
  float delayPoint=(delayMultiplier * duration) / totalDuration;
  midwayPoint=1 - (midwayPoint * (1 - delayPoint));
  kf0=Keyframe.ofFloat(0f,mTransitionEndRadiusMultiplier);
  kf1=Keyframe.ofFloat(delayPoint,mTransitionEndRadiusMultiplier);
  kf2=Keyframe.ofFloat(midwayPoint,mTransitionMidRadiusMultiplier);
  kf3=Keyframe.ofFloat(1f,1);
  PropertyValuesHolder radiusReappear=PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier",kf0,kf1,kf2,kf3);
  kf0=Keyframe.ofFloat(0f,0f);
  kf1=Keyframe.ofFloat(delayPoint,0f);
  kf2=Keyframe.ofFloat(1f,1f);
  PropertyValuesHolder fadeIn=PropertyValuesHolder.ofKeyframe("alpha",kf0,kf1,kf2);
  mReappearAnimator=ObjectAnimator.ofPropertyValuesHolder(this,radiusReappear,fadeIn).setDuration(totalDuration);
  mReappearAnimator.addUpdateListener(mInvalidateUpdateListener);
}
