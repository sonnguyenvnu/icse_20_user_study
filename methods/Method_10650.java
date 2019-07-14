public static ObjectAnimator popup(final View view,final long duration){
  view.setAlpha(0);
  view.setVisibility(View.VISIBLE);
  ObjectAnimator popup=ObjectAnimator.ofPropertyValuesHolder(view,PropertyValuesHolder.ofFloat("alpha",0f,1f),PropertyValuesHolder.ofFloat("scaleX",0f,1f),PropertyValuesHolder.ofFloat("scaleY",0f,1f));
  popup.setDuration(duration);
  popup.setInterpolator(new OvershootInterpolator());
  return popup;
}
