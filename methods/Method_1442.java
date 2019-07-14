private static void setWebpBitmapFactory(final WebpBitmapFactory webpBitmapFactory,final ImagePipelineExperiments imagePipelineExperiments,final BitmapCreator bitmapCreator){
  WebpSupportStatus.sWebpBitmapFactory=webpBitmapFactory;
  final WebpBitmapFactory.WebpErrorLogger webpErrorLogger=imagePipelineExperiments.getWebpErrorLogger();
  if (webpErrorLogger != null) {
    webpBitmapFactory.setWebpErrorLogger(webpErrorLogger);
  }
  if (bitmapCreator != null) {
    webpBitmapFactory.setBitmapCreator(bitmapCreator);
  }
}
