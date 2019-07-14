private void setupFrameInformationContainer(BitmapAnimationBackend bitmapAnimationBackend){
  mFrameInformationContainer.removeAllViews();
  LayoutInflater layoutInflater=LayoutInflater.from(getContext());
  for (int i=0; i < bitmapAnimationBackend.getFrameCount(); i++) {
    FrameInformationHolder frameInformation=createFrameInformation(layoutInflater,i);
    mFrameInfoMap.put(i,frameInformation);
    mFrameInformationContainer.addView(frameInformation.getView());
  }
  bitmapAnimationBackend.setFrameListener(mFrameListener);
}
