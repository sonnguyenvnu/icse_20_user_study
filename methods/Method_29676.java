private void layoutChild(int width,int height){
  final View child=getChildAt(0);
  int previewWidth=width;
  int previewHeight=height;
  if (mPreviewSize != null) {
    previewWidth=mPreviewSize.getWidth();
    previewHeight=mPreviewSize.getHeight();
  }
  if (width * previewHeight > height * previewWidth) {
    final int scaledChildHeight=previewHeight * width / previewWidth;
    child.layout(0,(height - scaledChildHeight) / 2,width,(height + scaledChildHeight) / 2);
  }
 else {
    final int scaledChildWidth=previewWidth * height / previewHeight;
    child.layout((width - scaledChildWidth) / 2,0,(width + scaledChildWidth) / 2,height);
  }
}
