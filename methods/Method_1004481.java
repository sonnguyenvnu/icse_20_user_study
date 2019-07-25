private CompletableFuture<Boolean> query(RemoteMessageQuery query,ServletResponse response){
  final CompletableFuture<Boolean> future=new CompletableFuture<>();
  try {
    threadPoolExecutor.execute(() -> {
      try {
        final String subject=query.getSubject();
        final List<RemoteMessageQuery.MessageKey> keys=query.getKeys();
        final ServletOutputStream os=response.getOutputStream();
        for (        RemoteMessageQuery.MessageKey key : keys) {
          long sequence=key.getSequence();
          GetMessageResult result=store.getMessage(subject,sequence);
          if (result.getStatus() != GetMessageStatus.SUCCESS)           continue;
          try {
            final byte[] sequenceBytes=Bytes.long2bytes(sequence);
            final List<Buffer> buffers=result.getBuffers();
            for (            Buffer buffer : buffers) {
              os.write(sequenceBytes);
              ByteBuffer byteBuffer=buffer.getBuffer();
              byte[] arr=new byte[byteBuffer.remaining()];
              byteBuffer.get(arr);
              os.write(arr);
            }
          }
  finally {
            result.release();
          }
        }
        os.flush();
        os.close();
        future.complete(true);
      }
 catch (      IOException e) {
        future.completeExceptionally(e);
      }
    }
);
  }
 catch (  RejectedExecutionException e) {
    future.completeExceptionally(e);
  }
  return future;
}
