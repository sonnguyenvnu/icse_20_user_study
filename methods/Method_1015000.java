@Override public List<UserInfo> search(String keyword){
  CountDownLatch countDownLatch=new CountDownLatch(1);
  List<UserInfo> userInfos=new ArrayList<>();
  ChatManager.Instance().searchUser(keyword,new SearchUserCallback(){
    @Override public void onSuccess(    List<UserInfo> infos){
      userInfos.addAll(infos);
      countDownLatch.countDown();
    }
    @Override public void onFail(    int errorCode){
      countDownLatch.countDown();
    }
  }
);
  try {
    countDownLatch.await();
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
  return userInfos;
}
