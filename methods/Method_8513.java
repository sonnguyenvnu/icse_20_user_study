@SuppressLint("NewApi") private void startRevealAnimation(final boolean open){
  containerView.setTranslationY(0);
  final AnimatorSet animatorSet=new AnimatorSet();
  View view=delegate.getRevealView();
  if (view.getVisibility() == View.VISIBLE && ((ViewGroup)view.getParent()).getVisibility() == View.VISIBLE) {
    final int[] coords=new int[2];
    view.getLocationInWindow(coords);
    float top;
    if (Build.VERSION.SDK_INT <= 19) {
      top=AndroidUtilities.displaySize.y - containerView.getMeasuredHeight() - AndroidUtilities.statusBarHeight;
    }
 else {
      top=containerView.getY();
    }
    revealX=coords[0] + view.getMeasuredWidth() / 2;
    revealY=(int)(coords[1] + view.getMeasuredHeight() / 2 - top);
    if (Build.VERSION.SDK_INT <= 19) {
      revealY-=AndroidUtilities.statusBarHeight;
    }
  }
 else {
    revealX=AndroidUtilities.displaySize.x / 2 + backgroundPaddingLeft;
    revealY=(int)(AndroidUtilities.displaySize.y - containerView.getY());
  }
  int[][] corners=new int[][]{{0,0},{0,AndroidUtilities.dp(304)},{containerView.getMeasuredWidth(),0},{containerView.getMeasuredWidth(),AndroidUtilities.dp(304)}};
  int finalRevealRadius=0;
  int y=revealY - scrollOffsetY + backgroundPaddingTop;
  for (int a=0; a < 4; a++) {
    finalRevealRadius=Math.max(finalRevealRadius,(int)Math.ceil(Math.sqrt((revealX - corners[a][0]) * (revealX - corners[a][0]) + (y - corners[a][1]) * (y - corners[a][1]))));
  }
  int finalRevealX=revealX <= containerView.getMeasuredWidth() ? revealX : containerView.getMeasuredWidth();
  ArrayList<Animator> animators=new ArrayList<>(3);
  animators.add(ObjectAnimator.ofFloat(this,"revealRadius",open ? 0 : finalRevealRadius,open ? finalRevealRadius : 0));
  animators.add(ObjectAnimator.ofInt(backDrawable,"alpha",open ? 51 : 0));
  if (Build.VERSION.SDK_INT >= 21) {
    try {
      animators.add(ViewAnimationUtils.createCircularReveal(containerView,finalRevealX,revealY,open ? 0 : finalRevealRadius,open ? finalRevealRadius : 0));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    animatorSet.setDuration(320);
  }
 else {
    if (!open) {
      animatorSet.setDuration(200);
      containerView.setPivotX(revealX <= containerView.getMeasuredWidth() ? revealX : containerView.getMeasuredWidth());
      containerView.setPivotY(revealY);
      animators.add(ObjectAnimator.ofFloat(containerView,View.SCALE_X,0.0f));
      animators.add(ObjectAnimator.ofFloat(containerView,View.SCALE_Y,0.0f));
      animators.add(ObjectAnimator.ofFloat(containerView,View.ALPHA,0.0f));
    }
 else {
      animatorSet.setDuration(250);
      containerView.setScaleX(1);
      containerView.setScaleY(1);
      containerView.setAlpha(1);
      if (Build.VERSION.SDK_INT <= 19) {
        animatorSet.setStartDelay(20);
      }
    }
  }
  animatorSet.playTogether(animators);
  animatorSet.addListener(new AnimatorListenerAdapter(){
    public void onAnimationEnd(    Animator animation){
      if (currentSheetAnimation != null && currentSheetAnimation.equals(animation)) {
        currentSheetAnimation=null;
        onRevealAnimationEnd(open);
        containerView.invalidate();
        containerView.setLayerType(View.LAYER_TYPE_NONE,null);
        if (!open) {
          try {
            dismissInternal();
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
      }
    }
    @Override public void onAnimationCancel(    Animator animation){
      if (currentSheetAnimation != null && animatorSet.equals(animation)) {
        currentSheetAnimation=null;
      }
    }
  }
);
  if (open) {
    innerAnimators.clear();
    NotificationCenter.getInstance(currentAccount).setAllowedNotificationsDutingAnimation(new int[]{NotificationCenter.dialogsNeedReload});
    NotificationCenter.getInstance(currentAccount).setAnimationInProgress(true);
    revealAnimationInProgress=true;
    int count=Build.VERSION.SDK_INT <= 19 ? 12 : 8;
    for (int a=0; a < count; a++) {
      if (views[a] == null) {
        continue;
      }
      if (Build.VERSION.SDK_INT <= 19) {
        if (a < 8) {
          views[a].setScaleX(0.1f);
          views[a].setScaleY(0.1f);
        }
        views[a].setAlpha(0.0f);
      }
 else {
        views[a].setScaleX(0.7f);
        views[a].setScaleY(0.7f);
      }
      InnerAnimator innerAnimator=new InnerAnimator();
      int buttonX=views[a].getLeft() + views[a].getMeasuredWidth() / 2;
      int buttonY=views[a].getTop() + attachView.getTop() + views[a].getMeasuredHeight() / 2;
      float dist=(float)Math.sqrt((revealX - buttonX) * (revealX - buttonX) + (revealY - buttonY) * (revealY - buttonY));
      float vecX=(revealX - buttonX) / dist;
      float vecY=(revealY - buttonY) / dist;
      views[a].setPivotX(views[a].getMeasuredWidth() / 2 + vecX * AndroidUtilities.dp(20));
      views[a].setPivotY(views[a].getMeasuredHeight() / 2 + vecY * AndroidUtilities.dp(20));
      innerAnimator.startRadius=dist - AndroidUtilities.dp(27 * 3);
      views[a].setTag(R.string.AppName,1);
      animators=new ArrayList<>();
      final AnimatorSet animatorSetInner;
      if (a < 8) {
        animators.add(ObjectAnimator.ofFloat(views[a],View.SCALE_X,0.7f,1.05f));
        animators.add(ObjectAnimator.ofFloat(views[a],View.SCALE_Y,0.7f,1.05f));
        animatorSetInner=new AnimatorSet();
        animatorSetInner.playTogether(ObjectAnimator.ofFloat(views[a],View.SCALE_X,1.0f),ObjectAnimator.ofFloat(views[a],View.SCALE_Y,1.0f));
        animatorSetInner.setDuration(100);
        animatorSetInner.setInterpolator(CubicBezierInterpolator.EASE_OUT);
      }
 else {
        animatorSetInner=null;
      }
      if (Build.VERSION.SDK_INT <= 19) {
        animators.add(ObjectAnimator.ofFloat(views[a],View.ALPHA,1.0f));
      }
      innerAnimator.animatorSet=new AnimatorSet();
      innerAnimator.animatorSet.playTogether(animators);
      innerAnimator.animatorSet.setDuration(150);
      innerAnimator.animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT);
      innerAnimator.animatorSet.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (animatorSetInner != null) {
            animatorSetInner.start();
          }
        }
      }
);
      innerAnimators.add(innerAnimator);
    }
  }
  currentSheetAnimation=animatorSet;
  animatorSet.start();
}
