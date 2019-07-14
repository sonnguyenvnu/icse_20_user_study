public void hideCamera(boolean async){
  if (!deviceHasGoodCamera || cameraView == null) {
    return;
  }
  cameraView.destroy(async,null);
  container.removeView(cameraView);
  container.removeView(cameraIcon);
  cameraView=null;
  cameraIcon=null;
  int count=attachPhotoRecyclerView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=attachPhotoRecyclerView.getChildAt(a);
    if (child instanceof PhotoAttachCameraCell) {
      child.setVisibility(View.VISIBLE);
      return;
    }
  }
}
