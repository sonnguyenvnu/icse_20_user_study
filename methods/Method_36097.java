private void writeAndTranslateExceptionsWithChunkedDribbleDelay(HttpServletResponse httpServletResponse,InputStream bodyStream,ChunkedDribbleDelay chunkedDribbleDelay){
  try (ServletOutputStream out=httpServletResponse.getOutputStream()){
    byte[] body=ByteStreams.toByteArray(bodyStream);
    if (body.length < 1) {
      notifier.error("Cannot chunk dribble delay when no body set");
      out.flush();
      return;
    }
    byte[][] chunkedBody=BodyChunker.chunkBody(body,chunkedDribbleDelay.getNumberOfChunks());
    int chunkInterval=chunkedDribbleDelay.getTotalDuration() / chunkedBody.length;
    for (    byte[] bodyChunk : chunkedBody) {
      Thread.sleep(chunkInterval);
      out.write(bodyChunk);
      out.flush();
    }
  }
 catch (  IOException e) {
    throwUnchecked(e);
  }
catch (  InterruptedException ignored) {
  }
}
