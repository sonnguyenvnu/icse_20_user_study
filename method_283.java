@Override public void _XXXXX_(){
  if (this.client != null) {
    try {
      this.client.close();
    }
 catch (    IOException e) {
      LOG.error(e.getMessage(),e);
    }
  }
}