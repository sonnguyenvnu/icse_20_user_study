@Override public void parse(OnMediaFoundCallback mpdFoundCallback){
  YouTubeInfoVisitor visitor=new MergeMediaVisitor(mpdFoundCallback);
  for (  String content : mContent) {
    if (content == null)     continue;
    YouTubeInfoVisitable visitable=new SimpleYouTubeInfoManager(content);
    visitable.accept(visitor);
  }
}
