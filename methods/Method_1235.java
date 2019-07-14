/** 
 * Initializes Drawee with the specified config. 
 */
private static void initializeDrawee(Context context,@Nullable DraweeConfig draweeConfig){
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.beginSection("Fresco.initializeDrawee");
  }
  sDraweeControllerBuilderSupplier=new PipelineDraweeControllerBuilderSupplier(context,draweeConfig);
  SimpleDraweeView.initialize(sDraweeControllerBuilderSupplier);
  if (FrescoSystrace.isTracing()) {
    FrescoSystrace.endSection();
  }
}
