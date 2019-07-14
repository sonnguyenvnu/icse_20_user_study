private void start(final @AnimRes int inAnimResId,final @AnimRes int outAnimResID){
  removeAllViews();
  clearAnimation();
  if (messages == null || messages.isEmpty()) {
    throw new RuntimeException("The messages cannot be empty!");
  }
  position=0;
  addView(createTextView(messages.get(position)));
  if (messages.size() > 1) {
    setInAndOutAnimation(inAnimResId,outAnimResID);
    startFlipping();
  }
  if (getInAnimation() != null) {
    getInAnimation().setAnimationListener(new Animation.AnimationListener(){
      @Override public void onAnimationStart(      Animation animation){
        if (isAnimStart) {
          animation.cancel();
        }
        isAnimStart=true;
      }
      @Override public void onAnimationEnd(      Animation animation){
        position++;
        if (position >= messages.size()) {
          position=0;
        }
        View view=createTextView(messages.get(position));
        if (view.getParent() == null) {
          addView(view);
        }
        isAnimStart=false;
      }
      @Override public void onAnimationRepeat(      Animation animation){
      }
    }
);
  }
}
