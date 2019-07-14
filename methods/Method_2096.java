private void updateRegion(){
  if (mImageInfo == null) {
    return;
  }
  int left=0;
  int top=0;
  int right=mSelectedRegion.getMeasuredWidth() * mImageInfo.getWidth() / mFullDraweeView.getMeasuredWidth();
  int bottom=mSelectedRegion.getMeasuredHeight() * mImageInfo.getHeight() / mFullDraweeView.getMeasuredHeight();
  ImageDecoder regionDecoder=createRegionDecoder(left,top,right,bottom);
  mRegionDraweeView.setController(Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(mUri).setImageDecodeOptions(ImageDecodeOptions.newBuilder().setCustomImageDecoder(regionDecoder).build()).build()).build());
}
