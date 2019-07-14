private void showNoSoundHint(){
  if (scrollingChatListView || SharedConfig.noSoundHintShowed || chatListView == null || getParentActivity() == null || fragmentView == null || noSoundHintView != null && noSoundHintView.getTag() != null) {
    return;
  }
  if (noSoundHintView == null) {
    SizeNotifierFrameLayout frameLayout=(SizeNotifierFrameLayout)fragmentView;
    int index=frameLayout.indexOfChild(chatActivityEnterView);
    if (index == -1) {
      return;
    }
    noSoundHintView=new HintView(getParentActivity(),0);
    frameLayout.addView(noSoundHintView,index + 1,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.TOP,19,0,19,0));
    noSoundHintView.setAlpha(0.0f);
    noSoundHintView.setVisibility(View.INVISIBLE);
  }
  int count=chatListView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=chatListView.getChildAt(a);
    if (!(child instanceof ChatMessageCell)) {
      continue;
    }
    ChatMessageCell messageCell=(ChatMessageCell)child;
    MessageObject messageObject=messageCell.getMessageObject();
    if (messageObject == null || !messageObject.isVideo()) {
      continue;
    }
    ImageReceiver imageReceiver=messageCell.getPhotoImage();
    AnimatedFileDrawable animation=imageReceiver.getAnimation();
    if (animation == null || animation.getCurrentProgressMs() < 3000) {
      continue;
    }
    if (noSoundHintView.showForMessageCell(messageCell,true)) {
      SharedConfig.setNoSoundHintShowed(true);
      break;
    }
  }
}
