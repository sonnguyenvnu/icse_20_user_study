/** 
 * @param uri The Uri to show into the DraweeView for this Holder
 */
public void bind(Uri uri){
  mDraweeView.initInstrumentation(uri.toString(),mPerfListener);
  ImageRequestBuilder imageRequestBuilder=ImageRequestBuilder.newBuilderWithSource(uri).setResizeOptions(new ResizeOptions(mDraweeView.getLayoutParams().width,mDraweeView.getLayoutParams().height));
  PipelineUtil.addOptionalFeatures(imageRequestBuilder,mConfig);
  PipelineDraweeControllerBuilder builder=Fresco.newDraweeControllerBuilder().setImageRequest(imageRequestBuilder.build());
  if (mConfig.reuseOldController) {
    builder.setOldController(mDraweeView.getController());
  }
  mDraweeView.setListener(builder);
  mDraweeView.setController(builder.build());
}
