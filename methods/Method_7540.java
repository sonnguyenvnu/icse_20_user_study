public double readDouble(boolean exception){
  try {
    return Double.longBitsToDouble(readInt64(exception));
  }
 catch (  Exception e) {
    if (exception) {
      throw new RuntimeException("read double error",e);
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("read double error");
      }
    }
  }
  return 0;
}
