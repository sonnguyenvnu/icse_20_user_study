@Override protected void releaseIfNeed(Object result){
  if (result instanceof SofaResponse) {
    AbstractByteBuf byteBuffer=response.getData();
    if (byteBuffer != null) {
      byteBuffer.release();
      response.setData(null);
    }
  }
}
