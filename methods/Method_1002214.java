@Override public void accept(YouTubeInfoVisitor visitor){
  mVisitor=visitor;
  GenericInfo info=mMediaParser.extractGenericInfo();
  mVisitor.onGenericInfo(info);
  List<Subtitle> subs=mSubParser.extractAllSubs();
  if (subs != null) {
    for (    Subtitle sub : subs) {
      mVisitor.onSubItem(sub);
    }
  }
  String spec=mParser.extractStorySpec();
  if (spec != null) {
    mVisitor.onStorySpec(spec);
  }
  mMediaParser.extractMediaItemsAndDecipher(new YouTubeMediaParser.ParserListener(){
    @Override public void onHlsUrl(    Uri url){
      mVisitor.onHlsUrl(url);
    }
    @Override public void onDashUrl(    Uri url){
      mVisitor.onDashUrl(url);
    }
    @Override public void onTrackingUrl(    Uri url){
      mVisitor.onTrackingUrl(url);
    }
    @Override public void onExtractMediaItemsAndDecipher(    List<MediaItem> items){
      for (      MediaItem item : items) {
        mVisitor.onMediaItem(item);
      }
      mVisitor.doneVisiting();
    }
  }
);
}
