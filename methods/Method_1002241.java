@Override public WebResourceResponse intercept(String url){
  if (!test(url)) {
    return null;
  }
  Response response=OkHttpHelpers.doOkHttpRequest(url);
  if (response == null)   return null;
  VideoInfoBuilder videoInfoBuilder=new YouTubeVideoInfoBuilder(response.body().byteStream());
  Set<VideoFormat> supportedFormats=videoInfoBuilder.getSupportedFormats();
  Browser.getBus().post(new VideoFormatEvent(supportedFormats,mSelectedFormat));
  videoInfoBuilder.selectFormat(mSelectedFormat);
  InputStream is=videoInfoBuilder.get();
  return createResponse(response.body().contentType(),is);
}
