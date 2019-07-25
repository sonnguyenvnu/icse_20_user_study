public void clear(){
  ImageLoader.getInstance().displayImage(DEFAULT_PATH,mTargetImageView);
  mWidthEditText.setText("200");
  mHeightEditText.setText("200");
  mPath=DEFAULT_PATH;
}
