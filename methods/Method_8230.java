private void showForwardHint(ChatMessageCell cell){
  if (scrollingChatListView || chatListView == null || getParentActivity() == null || fragmentView == null) {
    return;
  }
  if (forwardHintView == null) {
    SizeNotifierFrameLayout frameLayout=(SizeNotifierFrameLayout)fragmentView;
    int index=frameLayout.indexOfChild(chatActivityEnterView);
    if (index == -1) {
      return;
    }
    forwardHintView=new HintView(getParentActivity(),1);
    frameLayout.addView(forwardHintView,index + 1,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.WRAP_CONTENT,Gravity.LEFT | Gravity.TOP,19,0,19,0));
    forwardHintView.setAlpha(0.0f);
    forwardHintView.setVisibility(View.INVISIBLE);
  }
  forwardHintView.showForMessageCell(cell,true);
}
