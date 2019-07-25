static SubscriberFactory streaming(Executor callbackExecutor){
  return (  ArmeriaCall armeriaCall,  Callback callback,  Request request) -> new StreamingCallSubscriber(armeriaCall,callback,request,callbackExecutor);
}
