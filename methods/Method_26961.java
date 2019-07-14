@Override public void onAspectRatioSelected(@NonNull AspectRatio ratio){
  if (mCameraView != null) {
    Toast.makeText(this,ratio.toString(),Toast.LENGTH_SHORT).show();
    mCameraView.setAspectRatio(ratio);
  }
}
