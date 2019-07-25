public MutableLiveData<OperateResult<Integer>> forward(Conversation targetConversation,Message... messages){
  MutableLiveData<OperateResult<Integer>> resultMutableLiveData=new MutableLiveData<>();
  AtomicInteger count=new AtomicInteger(0);
  for (  Message message : messages) {
    if (message != null) {
      count.addAndGet(1);
    }
  }
  for (  Message message : messages) {
    if (message == null) {
      continue;
    }
    message.conversation=targetConversation;
    ChatManager.Instance().sendMessage(message,new SendMessageCallback(){
      @Override public void onSuccess(      long messageUid,      long timestamp){
        if (count.decrementAndGet() == 0) {
          resultMutableLiveData.postValue(new OperateResult<>(0));
        }
      }
      @Override public void onFail(      int errorCode){
        if (count.decrementAndGet() == 0) {
          resultMutableLiveData.postValue(new OperateResult<>(errorCode));
        }
      }
      @Override public void onPrepare(      long messageId,      long savedTime){
      }
    }
);
  }
  return resultMutableLiveData;
}
