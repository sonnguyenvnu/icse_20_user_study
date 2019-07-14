private void beginAvatarTransition(ImageView moveAvatarOneImage,ImageView moveAvatarTwoImage,ImageView moveAvatarThreeImage){
  ImageView appearAvatarImage=moveAvatarOneImage;
  ImageView disappearAvatarImage=moveAvatarThreeImage != null ? moveAvatarThreeImage : moveAvatarTwoImage;
  ImageView fadeOutDisappearAvatarImage=disappearAvatarImage == mAvatarImage ? mFadeOutAvatarImage : disappearAvatarImage == mRecentOneAvatarImage ? mFadeOutRecentOneAvatarImage : mFadeOutRecentTwoAvatarImage;
  TransitionSet transitionSet=new TransitionSet();
  int duration=ViewUtils.getLongAnimTime(getContext());
  transitionSet.setDuration(duration);
  transitionSet.setInterpolator(new FastOutSlowInInterpolator());
  Fade fadeOutAvatar=new Fade(Fade.OUT);
  setAvatarImageFrom(fadeOutDisappearAvatarImage,disappearAvatarImage);
  fadeOutDisappearAvatarImage.setVisibility(VISIBLE);
  fadeOutAvatar.addTarget(fadeOutDisappearAvatarImage);
  transitionSet.addTransition(fadeOutAvatar);
  fadeOutAvatar.setDuration(duration / 2);
  Fade fadeInAvatar=new Fade(Fade.IN);
  appearAvatarImage.setVisibility(INVISIBLE);
  fadeInAvatar.addTarget(appearAvatarImage);
  transitionSet.addTransition(fadeInAvatar);
  ChangeTransform changeAppearAvatarTransform=new ChangeTransform();
  appearAvatarImage.setScaleX(0.8f);
  appearAvatarImage.setScaleY(0.8f);
  changeAppearAvatarTransform.addTarget(appearAvatarImage);
  transitionSet.addTransition(changeAppearAvatarTransform);
  addChangeMoveToAvatarTransformToTransitionSet(moveAvatarOneImage,moveAvatarTwoImage,transitionSet);
  if (moveAvatarThreeImage != null) {
    addChangeMoveToAvatarTransformToTransitionSet(moveAvatarTwoImage,moveAvatarThreeImage,transitionSet);
  }
  CrossfadeText crossfadeText=new CrossfadeText();
  crossfadeText.addTarget(mNameText);
  crossfadeText.addTarget(mDescriptionText);
  transitionSet.addTransition(crossfadeText);
  transitionSet.addListener(new Transition.TransitionListenerAdapter(){
    @Override public void onTransitionEnd(    Transition transition){
      mAccountTransitionRunning=false;
      mInfoLayout.setEnabled(true);
      if (mListener != null) {
        mListener.onAccountTransitionEnd();
      }
    }
  }
);
  mInfoLayout.setEnabled(false);
  TransitionManager.beginDelayedTransition(this,transitionSet);
  mAccountTransitionRunning=true;
  if (mListener != null) {
    mListener.onAccountTransitionStart();
  }
  fadeOutDisappearAvatarImage.setVisibility(INVISIBLE);
  appearAvatarImage.setVisibility(VISIBLE);
  appearAvatarImage.setScaleX(1);
  appearAvatarImage.setScaleY(1);
  resetMoveToAvatarTransform(moveAvatarTwoImage);
  if (moveAvatarThreeImage != null) {
    resetMoveToAvatarTransform(moveAvatarThreeImage);
  }
}
