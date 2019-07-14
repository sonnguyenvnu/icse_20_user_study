private void init(Context context,AttributeSet attrs,int defStyleAttr){
  this.mContext=context;
  setFlipInterval(interval);
  Animation animIn=AnimationUtils.loadAnimation(mContext,R.anim.anim_marquee_in);
  if (isSetAnimDuration) {
    animIn.setDuration(animDuration);
  }
  setInAnimation(animIn);
  Animation animOut=AnimationUtils.loadAnimation(mContext,R.anim.anim_marquee_out);
  if (isSetAnimDuration) {
    animOut.setDuration(animDuration);
  }
  setOutAnimation(animOut);
}
