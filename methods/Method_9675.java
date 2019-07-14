private void showPatternsView(int num,boolean show){
  boolean showMotion=show && num == 1 && selectedPattern != null;
  if (show) {
    if (num == 0) {
      previousBackgroundColor=backgroundColor;
      colorPicker.setColor(backgroundColor);
    }
 else {
      previousSelectedPattern=selectedPattern;
      previousIntensity=currentIntensity;
      patternsAdapter.notifyDataSetChanged();
      if (patterns != null) {
        int index;
        if (selectedPattern == null) {
          index=0;
        }
 else {
          index=patterns.indexOf(selectedPattern) + 1;
        }
        patternsLayoutManager.scrollToPositionWithOffset(index,(patternsListView.getMeasuredWidth() - AndroidUtilities.dp(100) - AndroidUtilities.dp(12)) / 2);
      }
    }
  }
  checkBoxView[showMotion ? 2 : 0].setVisibility(View.VISIBLE);
  AnimatorSet animatorSet=new AnimatorSet();
  ArrayList<Animator> animators=new ArrayList<>();
  int otherNum=num == 0 ? 1 : 0;
  if (show) {
    patternLayout[num].setVisibility(View.VISIBLE);
    animators.add(ObjectAnimator.ofFloat(listView,View.TRANSLATION_Y,-patternLayout[num].getMeasuredHeight() + AndroidUtilities.dp(48)));
    animators.add(ObjectAnimator.ofFloat(buttonsContainer,View.TRANSLATION_Y,-patternLayout[num].getMeasuredHeight() + AndroidUtilities.dp(48)));
    animators.add(ObjectAnimator.ofFloat(checkBoxView[2],View.ALPHA,showMotion ? 1.0f : 0.0f));
    animators.add(ObjectAnimator.ofFloat(checkBoxView[0],View.ALPHA,showMotion ? 0.0f : 1.0f));
    animators.add(ObjectAnimator.ofFloat(backgroundImage,View.ALPHA,0.0f));
    if (patternLayout[otherNum].getVisibility() == View.VISIBLE) {
      animators.add(ObjectAnimator.ofFloat(patternLayout[otherNum],View.ALPHA,0.0f));
      animators.add(ObjectAnimator.ofFloat(patternLayout[num],View.ALPHA,0.0f,1.0f));
      patternLayout[num].setTranslationY(0);
    }
 else {
      animators.add(ObjectAnimator.ofFloat(patternLayout[num],View.TRANSLATION_Y,patternLayout[num].getMeasuredHeight(),0));
    }
  }
 else {
    animators.add(ObjectAnimator.ofFloat(listView,View.TRANSLATION_Y,0));
    animators.add(ObjectAnimator.ofFloat(buttonsContainer,View.TRANSLATION_Y,0));
    animators.add(ObjectAnimator.ofFloat(patternLayout[num],View.TRANSLATION_Y,patternLayout[num].getMeasuredHeight()));
    animators.add(ObjectAnimator.ofFloat(checkBoxView[0],View.ALPHA,1.0f));
    animators.add(ObjectAnimator.ofFloat(checkBoxView[2],View.ALPHA,0.0f));
    animators.add(ObjectAnimator.ofFloat(backgroundImage,View.ALPHA,1.0f));
  }
  animatorSet.playTogether(animators);
  animatorSet.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      if (show && patternLayout[otherNum].getVisibility() == View.VISIBLE) {
        patternLayout[otherNum].setAlpha(1.0f);
        patternLayout[otherNum].setVisibility(View.INVISIBLE);
      }
 else       if (!show) {
        patternLayout[num].setVisibility(View.INVISIBLE);
      }
      checkBoxView[showMotion ? 0 : 2].setVisibility(View.INVISIBLE);
    }
  }
);
  animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
  animatorSet.setDuration(200);
  animatorSet.start();
}
