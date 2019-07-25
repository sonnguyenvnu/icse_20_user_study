public void failure(Throwable throwable){
  try {
    log.error("failure",throwable);
    jerseyLatch.countDown();
    servletResponse.flushBuffer();
  }
 catch (  IOException e) {
    log.error("Could not fail response",e);
    throw new InternalServerErrorException(e);
  }
}
