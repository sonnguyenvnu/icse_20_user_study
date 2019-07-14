public ResponseDefinitionBuilder withChunkedDribbleDelay(int numberOfChunks,int totalDuration){
  this.chunkedDribbleDelay=new ChunkedDribbleDelay(numberOfChunks,totalDuration);
  return this;
}
