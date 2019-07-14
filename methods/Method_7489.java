public int sendRequest(final TLObject object,final RequestDelegate onComplete,final QuickAckDelegate onQuickAck,final WriteToSocketDelegate onWriteToSocket,final int flags,final int datacenterId,final int connetionType,final boolean immediate){
  final int requestToken=lastRequestToken.getAndIncrement();
  Utilities.stageQueue.postRunnable(() -> {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("send request " + object + " with token = " + requestToken);
    }
    try {
      NativeByteBuffer buffer=new NativeByteBuffer(object.getObjectSize());
      object.serializeToStream(buffer);
      object.freeResources();
      native_sendRequest(currentAccount,buffer.address,(response,errorCode,errorText,networkType) -> {
        try {
          TLObject resp=null;
          TLRPC.TL_error error=null;
          if (response != 0) {
            NativeByteBuffer buff=NativeByteBuffer.wrap(response);
            buff.reused=true;
            resp=object.deserializeResponse(buff,buff.readInt32(true),true);
          }
 else           if (errorText != null) {
            error=new TLRPC.TL_error();
            error.code=errorCode;
            error.text=errorText;
            if (BuildVars.LOGS_ENABLED) {
              FileLog.e(object + " got error " + error.code + " " + error.text);
            }
          }
          if (resp != null) {
            resp.networkType=networkType;
          }
          if (BuildVars.LOGS_ENABLED) {
            FileLog.d("java received " + resp + " error = " + error);
          }
          final TLObject finalResponse=resp;
          final TLRPC.TL_error finalError=error;
          Utilities.stageQueue.postRunnable(() -> {
            onComplete.run(finalResponse,finalError);
            if (finalResponse != null) {
              finalResponse.freeResources();
            }
          }
);
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
      }
,onQuickAck,onWriteToSocket,flags,datacenterId,connetionType,immediate,requestToken);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
  return requestToken;
}
