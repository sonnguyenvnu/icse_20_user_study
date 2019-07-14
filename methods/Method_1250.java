public void addViewportData(){
  DraweeHierarchy hierarchy=mPipelineDraweeController.getHierarchy();
  if (hierarchy != null && hierarchy.getTopLevelDrawable() != null) {
    Rect bounds=hierarchy.getTopLevelDrawable().getBounds();
    mImagePerfState.setOnScreenWidth(bounds.width());
    mImagePerfState.setOnScreenHeight(bounds.height());
  }
}
