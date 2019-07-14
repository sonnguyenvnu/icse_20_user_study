private void updateTabTitle(){
  mTabAdapter.setPageTitle(mTabLayout,0,getTabTitle(mBroadcast.likeCount,R.string.broadcast_likers_title_format,R.string.broadcast_likers_title_empty));
  mTabAdapter.setPageTitle(mTabLayout,1,getTabTitle(mBroadcast.rebroadcastCount,R.string.broadcast_rebroadcasts_title_format,R.string.broadcast_rebroadcasts_title_empty));
}
