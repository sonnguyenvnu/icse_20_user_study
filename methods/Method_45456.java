public synchronized void stop(){
  if (this.running) {
    try {
      this.running=false;
      this.service.close();
      this.result.get();
    }
 catch (    Exception e) {
      throw new MocoException(e);
    }
  }
}
