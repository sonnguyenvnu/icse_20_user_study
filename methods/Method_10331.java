private HttpEntity createJsonStreamerEntity(ResponseHandlerInterface progressHandler) throws IOException {
  JsonStreamerEntity entity=new JsonStreamerEntity(progressHandler,!fileParams.isEmpty() || !streamParams.isEmpty(),elapsedFieldInJsonStreamer);
  for (  ConcurrentSkipList.Entry<String,String> entry : urlParams.entrySet()) {
    entity.addPart(entry.getKey(),entry.getValue());
  }
  for (  ConcurrentSkipList.Entry<String,Object> entry : urlParamsWithObjects.entrySet()) {
    entity.addPart(entry.getKey(),entry.getValue());
  }
  for (  ConcurrentSkipList.Entry<String,FileWrapper> entry : fileParams.entrySet()) {
    entity.addPart(entry.getKey(),entry.getValue());
  }
  for (  ConcurrentSkipList.Entry<String,StreamWrapper> entry : streamParams.entrySet()) {
    StreamWrapper stream=entry.getValue();
    if (stream.inputStream != null) {
      entity.addPart(entry.getKey(),StreamWrapper.newInstance(stream.inputStream,stream.name,stream.contentType,stream.autoClose));
    }
  }
  return entity;
}
