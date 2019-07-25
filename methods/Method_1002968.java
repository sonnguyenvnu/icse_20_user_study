private void invoke(ServiceRequestContext ctx,SerializationFormat serializationFormat,int seqId,ThriftFunction func,RpcRequest call,CompletableFuture<HttpResponse> res){
  final RpcResponse reply;
  try (SafeCloseable ignored=ctx.push()){
    reply=delegate.serve(ctx,call);
  }
 catch (  Throwable cause) {
    handleException(ctx,new DefaultRpcResponse(cause),res,serializationFormat,seqId,func,cause);
    return;
  }
  reply.handle((result,cause) -> {
    if (func.isOneWay()) {
      handleOneWaySuccess(ctx,reply,res,serializationFormat);
      return null;
    }
    if (cause != null) {
      handleException(ctx,reply,res,serializationFormat,seqId,func,cause);
      return null;
    }
    try {
      handleSuccess(ctx,reply,res,serializationFormat,seqId,func,result);
    }
 catch (    Throwable t) {
      handleException(ctx,new DefaultRpcResponse(t),res,serializationFormat,seqId,func,t);
    }
    return null;
  }
).exceptionally(CompletionActions::log);
}
