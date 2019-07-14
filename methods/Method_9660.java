private void setStateTextAnimated(String _newText,boolean ellipsis){
  if (_newText.equals(lastStateText))   return;
  lastStateText=_newText;
  if (textChangingAnim != null)   textChangingAnim.cancel();
  CharSequence newText;
  if (ellipsis) {
    if (!ellAnimator.isRunning())     ellAnimator.start();
    SpannableStringBuilder ssb=new SpannableStringBuilder(_newText.toUpperCase());
    for (    TextAlphaSpan s : ellSpans)     s.setAlpha(0);
    SpannableString ell=new SpannableString("...");
    ell.setSpan(ellSpans[0],0,1,0);
    ell.setSpan(ellSpans[1],1,2,0);
    ell.setSpan(ellSpans[2],2,3,0);
    ssb.append(ell);
    newText=ssb;
  }
 else {
    if (ellAnimator.isRunning())     ellAnimator.cancel();
    newText=_newText.toUpperCase();
  }
  stateText2.setText(newText);
  stateText2.setVisibility(View.VISIBLE);
  stateText.setPivotX(LocaleController.isRTL ? stateText.getWidth() : 0);
  stateText.setPivotY(stateText.getHeight() / 2);
  stateText2.setPivotX(LocaleController.isRTL ? stateText.getWidth() : 0);
  stateText2.setPivotY(stateText.getHeight() / 2);
  durationText=stateText2;
  AnimatorSet set=new AnimatorSet();
  set.playTogether(ObjectAnimator.ofFloat(stateText2,"alpha",0,1),ObjectAnimator.ofFloat(stateText2,"translationY",stateText.getHeight() / 2,0),ObjectAnimator.ofFloat(stateText2,"scaleX",0.7f,1),ObjectAnimator.ofFloat(stateText2,"scaleY",0.7f,1),ObjectAnimator.ofFloat(stateText,"alpha",1,0),ObjectAnimator.ofFloat(stateText,"translationY",0,-stateText.getHeight() / 2),ObjectAnimator.ofFloat(stateText,"scaleX",1,0.7f),ObjectAnimator.ofFloat(stateText,"scaleY",1,0.7f));
  set.setDuration(200);
  set.setInterpolator(CubicBezierInterpolator.DEFAULT);
  set.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      textChangingAnim=null;
      stateText2.setVisibility(View.GONE);
      durationText=stateText;
      stateText.setTranslationY(0);
      stateText.setScaleX(1);
      stateText.setScaleY(1);
      stateText.setAlpha(1);
      stateText.setText(stateText2.getText());
    }
  }
);
  textChangingAnim=set;
  set.start();
}
