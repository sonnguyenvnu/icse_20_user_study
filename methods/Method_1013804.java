@Override public void exception(Exception e){
  try {
    logger.error("[exception][close client]" + redisSlave,e);
    redisSlave.close();
  }
 catch (  IOException e1) {
    logger.error("[exception]" + redisSlave,e1);
  }
}
