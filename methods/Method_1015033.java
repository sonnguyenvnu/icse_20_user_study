@Override public List<ChannelInfo> search(String keyword){
  CountDownLatch latch=new CountDownLatch(1);
  List<ChannelInfo> result=new ArrayList<>();
  ChatManager.Instance().searchChannel(keyword,new SearchChannelCallback(){
    @Override public void onSuccess(    List<ChannelInfo> channelInfos){
      if (channelInfos != null) {
        result.addAll(channelInfos);
      }
      latch.countDown();
    }
    @Override public void onFail(    int errorCode){
      Log.e(ChannelSearchModule.class.getSimpleName(),"search failure: " + errorCode);
      latch.countDown();
    }
  }
);
  try {
    latch.await();
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
  return result;
}
