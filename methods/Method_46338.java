@Override public void onEvent(Event originEvent){
  if (!Tracers.isEnable()) {
    return;
  }
  Class eventClass=originEvent.getClass();
  if (eventClass == ClientStartInvokeEvent.class) {
    ClientStartInvokeEvent event=(ClientStartInvokeEvent)originEvent;
    Tracers.startRpc(event.getRequest());
  }
 else   if (eventClass == ClientBeforeSendEvent.class) {
    ClientBeforeSendEvent event=(ClientBeforeSendEvent)originEvent;
    Tracers.clientBeforeSend(event.getRequest());
  }
 else   if (eventClass == ClientAsyncReceiveEvent.class) {
    ClientAsyncReceiveEvent event=(ClientAsyncReceiveEvent)originEvent;
    Tracers.clientAsyncReceivedPrepare();
    Tracers.clientReceived(event.getRequest(),event.getResponse(),event.getThrowable());
  }
 else   if (eventClass == ClientEndInvokeEvent.class) {
    ClientEndInvokeEvent event=(ClientEndInvokeEvent)originEvent;
    if (!event.getRequest().isAsync()) {
      Tracers.clientReceived(event.getRequest(),event.getResponse(),event.getThrowable());
    }
    Tracers.checkState();
  }
 else   if (eventClass == ServerReceiveEvent.class) {
    ServerReceiveEvent event=(ServerReceiveEvent)originEvent;
    Tracers.serverReceived(event.getRequest());
  }
 else   if (eventClass == ServerSendEvent.class) {
    ServerSendEvent event=(ServerSendEvent)originEvent;
    Tracers.serverSend(event.getRequest(),event.getResponse(),event.getThrowable());
  }
 else   if (eventClass == ServerEndHandleEvent.class) {
    Tracers.checkState();
  }
}
