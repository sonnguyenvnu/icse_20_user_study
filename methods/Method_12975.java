/** 
 * ??????
 */
void flushMessageQueue(){
  if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
    loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA,new CallBackFunction(){
      @Override public void onCallBack(      String data){
        List<Message> list=null;
        try {
          list=Message.toArrayList(data);
        }
 catch (        Exception e) {
          e.printStackTrace();
          return;
        }
        if (list == null || list.size() == 0) {
          return;
        }
        for (int i=0; i < list.size(); i++) {
          Message m=list.get(i);
          String responseId=m.getResponseId();
          if (!TextUtils.isEmpty(responseId)) {
            CallBackFunction function=responseCallbacks.get(responseId);
            String responseData=m.getResponseData();
            function.onCallBack(responseData);
            responseCallbacks.remove(responseId);
          }
 else {
            CallBackFunction responseFunction=null;
            final String callbackId=m.getCallbackId();
            if (!TextUtils.isEmpty(callbackId)) {
              responseFunction=new CallBackFunction(){
                @Override public void onCallBack(                String data){
                  Message responseMsg=new Message();
                  responseMsg.setResponseId(callbackId);
                  responseMsg.setResponseData(data);
                  queueMessage(responseMsg);
                }
              }
;
            }
 else {
              responseFunction=new CallBackFunction(){
                @Override public void onCallBack(                String data){
                }
              }
;
            }
            BridgeHandler handler;
            if (!TextUtils.isEmpty(m.getHandlerName())) {
              handler=messageHandlers.get(m.getHandlerName());
            }
 else {
              handler=defaultHandler;
            }
            if (handler != null) {
              handler.handler(m.getData(),responseFunction);
            }
          }
        }
      }
    }
);
  }
}
