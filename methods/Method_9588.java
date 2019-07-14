@Override protected AnimatorSet onCustomTransitionAnimation(final boolean isOpen,final Runnable callback){
  if (playProfileAnimation && allowProfileAnimation) {
    final AnimatorSet animatorSet=new AnimatorSet();
    animatorSet.setDuration(180);
    listView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
    ActionBarMenu menu=actionBar.createMenu();
    if (menu.getItem(10) == null) {
      if (animatingItem == null) {
        animatingItem=menu.addItem(10,R.drawable.ic_ab_other);
      }
    }
    if (isOpen) {
      FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)onlineTextView[1].getLayoutParams();
      layoutParams.rightMargin=(int)(-21 * AndroidUtilities.density + AndroidUtilities.dp(8));
      onlineTextView[1].setLayoutParams(layoutParams);
      int width=(int)Math.ceil(AndroidUtilities.displaySize.x - AndroidUtilities.dp(118 + 8) + 21 * AndroidUtilities.density);
      float width2=nameTextView[1].getPaint().measureText(nameTextView[1].getText().toString()) * 1.12f + nameTextView[1].getSideDrawablesSize();
      layoutParams=(FrameLayout.LayoutParams)nameTextView[1].getLayoutParams();
      if (width < width2) {
        layoutParams.width=(int)Math.ceil(width / 1.12f);
      }
 else {
        layoutParams.width=LayoutHelper.WRAP_CONTENT;
      }
      nameTextView[1].setLayoutParams(layoutParams);
      initialAnimationExtraHeight=AndroidUtilities.dp(88);
      fragmentView.setBackgroundColor(0);
      setAnimationProgress(0);
      ArrayList<Animator> animators=new ArrayList<>();
      animators.add(ObjectAnimator.ofFloat(this,"animationProgress",0.0f,1.0f));
      if (writeButton != null) {
        writeButton.setScaleX(0.2f);
        writeButton.setScaleY(0.2f);
        writeButton.setAlpha(0.0f);
        animators.add(ObjectAnimator.ofFloat(writeButton,View.SCALE_X,1.0f));
        animators.add(ObjectAnimator.ofFloat(writeButton,View.SCALE_Y,1.0f));
        animators.add(ObjectAnimator.ofFloat(writeButton,View.ALPHA,1.0f));
      }
      for (int a=0; a < 2; a++) {
        onlineTextView[a].setAlpha(a == 0 ? 1.0f : 0.0f);
        nameTextView[a].setAlpha(a == 0 ? 1.0f : 0.0f);
        animators.add(ObjectAnimator.ofFloat(onlineTextView[a],View.ALPHA,a == 0 ? 0.0f : 1.0f));
        animators.add(ObjectAnimator.ofFloat(nameTextView[a],View.ALPHA,a == 0 ? 0.0f : 1.0f));
      }
      if (animatingItem != null) {
        animatingItem.setAlpha(1.0f);
        animators.add(ObjectAnimator.ofFloat(animatingItem,View.ALPHA,0.0f));
      }
      if (callItem != null) {
        callItem.setAlpha(0.0f);
        animators.add(ObjectAnimator.ofFloat(callItem,View.ALPHA,1.0f));
      }
      if (editItem != null) {
        editItem.setAlpha(0.0f);
        animators.add(ObjectAnimator.ofFloat(editItem,View.ALPHA,1.0f));
      }
      animatorSet.playTogether(animators);
    }
 else {
      initialAnimationExtraHeight=extraHeight;
      ArrayList<Animator> animators=new ArrayList<>();
      animators.add(ObjectAnimator.ofFloat(this,"animationProgress",1.0f,0.0f));
      if (writeButton != null) {
        animators.add(ObjectAnimator.ofFloat(writeButton,View.SCALE_X,0.2f));
        animators.add(ObjectAnimator.ofFloat(writeButton,View.SCALE_Y,0.2f));
        animators.add(ObjectAnimator.ofFloat(writeButton,View.ALPHA,0.0f));
      }
      for (int a=0; a < 2; a++) {
        animators.add(ObjectAnimator.ofFloat(onlineTextView[a],View.ALPHA,a == 0 ? 1.0f : 0.0f));
        animators.add(ObjectAnimator.ofFloat(nameTextView[a],View.ALPHA,a == 0 ? 1.0f : 0.0f));
      }
      if (animatingItem != null) {
        animatingItem.setAlpha(0.0f);
        animators.add(ObjectAnimator.ofFloat(animatingItem,View.ALPHA,1.0f));
      }
      if (callItem != null) {
        callItem.setAlpha(1.0f);
        animators.add(ObjectAnimator.ofFloat(callItem,View.ALPHA,0.0f));
      }
      if (editItem != null) {
        editItem.setAlpha(1.0f);
        animators.add(ObjectAnimator.ofFloat(editItem,View.ALPHA,0.0f));
      }
      animatorSet.playTogether(animators);
    }
    animatorSet.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        listView.setLayerType(View.LAYER_TYPE_NONE,null);
        if (animatingItem != null) {
          ActionBarMenu menu=actionBar.createMenu();
          menu.clearItems();
          animatingItem=null;
        }
        callback.run();
      }
    }
);
    animatorSet.setInterpolator(new DecelerateInterpolator());
    AndroidUtilities.runOnUIThread(animatorSet::start,50);
    return animatorSet;
  }
  return null;
}
