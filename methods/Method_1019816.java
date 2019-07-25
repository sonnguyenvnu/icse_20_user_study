private void write(byte[] bytes){
  try {
    bos.write(bytes);
  }
 catch (  IOException e) {
    long now=System.currentTimeMillis();
    if (now > nextIOExceptionPrintTime) {
      nextIOExceptionPrintTime=now + IOEXCEPTION_PRINT_INTERVAL;
      SelfLog.error("Failed to write file " + fileName,e);
    }
  }
}
