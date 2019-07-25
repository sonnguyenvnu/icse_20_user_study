public MutableLiveData<Boolean> invite(String targetUid,String message){
  MutableLiveData<Boolean> result=new MutableLiveData<>();
  ChatManager.Instance().sendFriendRequest(targetUid,message,new GeneralCallback(){
    @Override public void onSuccess(){
      result.setValue(true);
    }
    @Override public void onFail(    int errorCode){
      result.setValue(false);
    }
  }
);
  return result;
}
