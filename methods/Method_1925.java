private void setupResetButton(){
  if (mResetButton == null) {
    return;
  }
  mResetButton.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View view){
      mAnimatedDrawable.stop();
      mAnimatedDrawable.jumpToFrame(0);
    }
  }
);
}
