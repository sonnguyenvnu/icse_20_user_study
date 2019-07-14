private void openStickersView(){
  if (stickersView != null && stickersView.getVisibility() == VISIBLE) {
    return;
  }
  pickingSticker=true;
  if (stickersView == null) {
    stickersView=new StickerMasksView(getContext());
    stickersView.setListener(new StickerMasksView.Listener(){
      @Override public void onStickerSelected(      Object parentObject,      TLRPC.Document sticker){
        closeStickersView();
        createSticker(parentObject,sticker);
      }
      @Override public void onTypeChanged(){
      }
    }
);
    addView(stickersView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.LEFT | Gravity.TOP));
  }
  stickersView.setVisibility(VISIBLE);
  Animator a=ObjectAnimator.ofFloat(stickersView,"alpha",0.0f,1.0f);
  a.setDuration(200);
  a.start();
}
