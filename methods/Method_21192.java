private MediaSource getMediaSource(final @NonNull String videoUrl){
  final DefaultHttpDataSourceFactory dataSourceFactory=new DefaultHttpDataSourceFactory(WebRequestInterceptor.userAgent(this.build));
  final Uri videoUri=Uri.parse(videoUrl);
  final int fileType=Util.inferContentType(videoUri);
  if (fileType == C.TYPE_HLS) {
    return new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
  }
 else {
    return new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri);
  }
}
