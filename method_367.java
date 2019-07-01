@Override public void _XXXXX_(){
  if (client != null) {
    LOG.info("closing service client...");
    try {
      client.close();
    }
 catch (    IOException e) {
      LOG.error("close service client failed due to {}",e.getMessage(),e);
    }
  }
}