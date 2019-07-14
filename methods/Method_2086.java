private void setImageUri(Uri uri){
  mDebugOutput.setText("");
  ImageRequest request=ImageRequestBuilder.newBuilderWithSource(uri).setProgressiveRenderingEnabled(mProgressiveRenderingEnabled).build();
  DraweeController controller=Fresco.newDraweeControllerBuilder().setImageRequest(request).setRetainImageOnFailure(true).setPerfDataListener(mImagePerfDataListener).setControllerListener(new BaseControllerListener<ImageInfo>(){
    @Override public void onFinalImageSet(    String id,    @javax.annotation.Nullable ImageInfo imageInfo,    @javax.annotation.Nullable Animatable animatable){
      if (imageInfo != null) {
        QualityInfo qualityInfo=imageInfo.getQualityInfo();
        logScan(qualityInfo,true);
      }
    }
    @Override public void onIntermediateImageSet(    String id,    @javax.annotation.Nullable ImageInfo imageInfo){
      if (imageInfo != null) {
        QualityInfo qualityInfo=imageInfo.getQualityInfo();
        logScan(qualityInfo,false);
      }
    }
    @Override public void onIntermediateImageFailed(    String id,    Throwable throwable){
      mDebugOutput.append(String.format(Locale.getDefault(),"onIntermediateImageFailed, %s\n",throwable.getMessage()));
    }
  }
).build();
  mSimpleDraweeView.setController(controller);
}
