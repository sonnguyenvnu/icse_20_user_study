@SuppressLint("ObjectAnimatorBinding") private ObjectAnimator createAlphaAnimator(Object target,int startVal,int endVal,int startDelay,int duration){
  ObjectAnimator a=ObjectAnimator.ofInt(target,"alpha",startVal,endVal);
  a.setDuration(duration);
  a.setStartDelay(startDelay);
  a.setInterpolator(CubicBezierInterpolator.DEFAULT);
  return a;
}
