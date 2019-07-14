@Override protected MoreListResourceFragment<?,List<RebroadcastItem>> onAttachResource(){
  return BroadcastRebroadcastListResource.attachTo(mBroadcast.id,this);
}
