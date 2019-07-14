@Override public void animate(){
  final ViewGroup parentView=(ViewGroup)view.getParent();
  final FrameLayout slideOutFrame=new FrameLayout(view.getContext());
  final int positionView=parentView.indexOfChild(view);
  slideOutFrame.setLayoutParams(view.getLayoutParams());
  slideOutFrame.setClipChildren(true);
  parentView.removeView(view);
  slideOutFrame.addView(view);
  parentView.addView(slideOutFrame,positionView);
switch (direction) {
case DIRECTION_LEFT:
    slideAnim=ObjectAnimator.ofFloat(view,View.TRANSLATION_X,view.getTranslationX() - view.getWidth());
  break;
case DIRECTION_RIGHT:
slideAnim=ObjectAnimator.ofFloat(view,View.TRANSLATION_X,view.getTranslationX() + view.getWidth());
break;
case DIRECTION_UP:
slideAnim=ObjectAnimator.ofFloat(view,View.TRANSLATION_Y,view.getTranslationY() - view.getHeight());
break;
case DIRECTION_DOWN:
slideAnim=ObjectAnimator.ofFloat(view,View.TRANSLATION_Y,view.getTranslationY() + view.getHeight());
break;
default :
break;
}
AnimatorSet slideSet=new AnimatorSet();
slideSet.play(slideAnim);
slideSet.setInterpolator(interpolator);
slideSet.setDuration(duration);
slideSet.addListener(new AnimatorListenerAdapter(){
@Override public void onAnimationEnd(Animator animation){
view.setVisibility(View.INVISIBLE);
slideAnim.reverse();
slideOutFrame.removeAllViews();
parentView.removeView(slideOutFrame);
parentView.addView(view,positionView);
if (getListener() != null) {
getListener().onAnimationEnd(SlideOutUnderneathAnimation.this);
}
}
}
);
slideSet.start();
}
