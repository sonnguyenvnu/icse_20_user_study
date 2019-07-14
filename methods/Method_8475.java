private void setEmojiButtonImage(boolean byOpen,boolean animated){
  if (animated && currentEmojiIcon == -1) {
    animated=false;
  }
  int nextIcon;
  if (byOpen && currentPopupContentType == 0) {
    nextIcon=0;
  }
 else {
    int currentPage;
    if (emojiView == null) {
      currentPage=MessagesController.getGlobalEmojiSettings().getInt("selected_page",0);
    }
 else {
      currentPage=emojiView.getCurrentPage();
    }
    if (currentPage == 0 || !allowStickers && !allowGifs) {
      nextIcon=1;
    }
 else     if (currentPage == 1) {
      nextIcon=2;
    }
 else {
      nextIcon=3;
    }
  }
  if (currentEmojiIcon == nextIcon) {
    return;
  }
  if (emojiButtonAnimation != null) {
    emojiButtonAnimation.cancel();
    emojiButtonAnimation=null;
  }
  if (nextIcon == 0) {
    emojiButton[animated ? 1 : 0].setImageResource(R.drawable.input_keyboard);
  }
 else   if (nextIcon == 1) {
    emojiButton[animated ? 1 : 0].setImageResource(R.drawable.input_smile);
  }
 else   if (nextIcon == 2) {
    emojiButton[animated ? 1 : 0].setImageResource(R.drawable.input_sticker);
  }
 else   if (nextIcon == 3) {
    emojiButton[animated ? 1 : 0].setImageResource(R.drawable.input_gif);
  }
  emojiButton[animated ? 1 : 0].setTag(nextIcon == 2 ? 1 : null);
  currentEmojiIcon=nextIcon;
  if (animated) {
    emojiButton[1].setVisibility(VISIBLE);
    emojiButtonAnimation=new AnimatorSet();
    emojiButtonAnimation.playTogether(ObjectAnimator.ofFloat(emojiButton[0],View.SCALE_X,0.1f),ObjectAnimator.ofFloat(emojiButton[0],View.SCALE_Y,0.1f),ObjectAnimator.ofFloat(emojiButton[0],View.ALPHA,0.0f),ObjectAnimator.ofFloat(emojiButton[1],View.SCALE_X,1.0f),ObjectAnimator.ofFloat(emojiButton[1],View.SCALE_Y,1.0f),ObjectAnimator.ofFloat(emojiButton[1],View.ALPHA,1.0f));
    emojiButtonAnimation.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        if (animation.equals(emojiButtonAnimation)) {
          emojiButtonAnimation=null;
          ImageView temp=emojiButton[1];
          emojiButton[1]=emojiButton[0];
          emojiButton[0]=temp;
          emojiButton[1].setVisibility(INVISIBLE);
          emojiButton[1].setAlpha(0.0f);
          emojiButton[1].setScaleX(0.1f);
          emojiButton[1].setScaleY(0.1f);
        }
      }
    }
);
    emojiButtonAnimation.setDuration(150);
    emojiButtonAnimation.start();
  }
}
