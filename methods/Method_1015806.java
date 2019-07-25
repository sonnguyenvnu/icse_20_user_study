public void commit(){
  try {
    log.debug("commit");
    jerseyLatch.countDown();
    servletResponse.flushBuffer();
  }
 catch (  IOException e) {
    log.error("Could not commit response",e);
    throw new InternalServerErrorException(e);
  }
}
